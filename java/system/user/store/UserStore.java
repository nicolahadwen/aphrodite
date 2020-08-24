package system.user.store;

import libraries.hibernate.Hibernate;
import libraries.id.IdUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.hibernate.Session;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class UserStore {
    private final IdUtils idUtils;
    private final Hibernate hibernate;

    @NonNull
    public UserEntity create(@NonNull CreateUserParam param) {
        return new CreateUserFunc(hibernate, idUtils).apply(param);
    }

    @NonNull
    public Optional<UserEntity> get(@NonNull String id) {
        Session session = hibernate.openSession();
        UserEntity user = session.get(UserEntity.class, id);
        session.close();
        return Optional.ofNullable(user);
    }
}
