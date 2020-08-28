package co.hadwen.aphrodite.platform.store;

import co.hadwen.aphrodite.platform.entity.PlatformEntity;
import libraries.hibernate.Hibernate;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.hibernate.Session;

import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class PlatformStore {
    private final Hibernate hibernate;

    @NonNull
    public Optional<PlatformEntity> get(@NonNull String platformId) {
        try {
            Session session = hibernate.openSession();
            PlatformEntity user = session.get(PlatformEntity.class, platformId);
            session.close();
            return Optional.ofNullable(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @NonNull
    public Optional<PlatformEntity> getByHost(@NonNull String host) {
        Session session = hibernate.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<PlatformEntity> criteriaQuery = criteriaBuilder.createQuery(PlatformEntity.class);
        Root<PlatformEntity> root = criteriaQuery.from(PlatformEntity.class);
        criteriaQuery.select(root);

        ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("host"), params));

        TypedQuery<PlatformEntity> query = session.createQuery(criteriaQuery);
        query.setParameter(params, host);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}

