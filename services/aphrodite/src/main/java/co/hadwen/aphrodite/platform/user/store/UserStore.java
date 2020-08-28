package co.hadwen.aphrodite.platform.user.store;

import co.hadwen.aphrodite.platform.entity.PlatformEntity;
import co.hadwen.aphrodite.platform.user.entity.UserEntity;
import co.hadwen.aphrodite.platform.user.entity.UserEntity.UserEntityId;
import libraries.hibernate.Hibernate;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

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