package co.hadwen.aphrodite.platform.user.store;

import co.hadwen.aphrodite.platform.entity.PlatformEntity;
import co.hadwen.aphrodite.platform.user.entity.UserEntity;
import co.hadwen.aphrodite.platform.user.entity.UserEntity.UserEntityId;
import libraries.hibernate.Hibernate;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class UserStore {
    private final Hibernate hibernate;

    @NonNull
    public Optional<UserEntity> get(
            @NonNull String userId, @NonNull PlatformEntity platformEntity) {
        Session session = hibernate.openSession();
        UserEntity user = session.get(UserEntity.class, new UserEntityId(platformEntity, userId));
        session.close();
        return Optional.ofNullable(user);
    }

    @NonNull
    public Map<String, PlatformEntity> getPlatformsForUser(@NonNull String username) {
        Session session = hibernate.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
        criteriaQuery.select(root);

        ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("username"), params));

        TypedQuery<UserEntity> query = session.createQuery(criteriaQuery);
        query.setParameter(params, username);
        try {
            return query.getResultList().stream()
                    .map(UserEntity::getPlatform)
                    .collect(Collectors.toMap(PlatformEntity::getPlatformId, Function.identity()));
        } catch (NoResultException e) {
            return Collections.emptyMap();
        }
    }

    @NonNull
    public UserEntity create(
            @NonNull PlatformEntity platform,
            @NonNull String userId) {
        Session session = hibernate.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(new UserEntity(
                platform,
                userId
        ));
        try {
            UserEntity createdUser = session.load(
                    UserEntity.class, new UserEntityId(
                            platform,
                            userId
                    ));
            transaction.commit();
            return createdUser;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }
}