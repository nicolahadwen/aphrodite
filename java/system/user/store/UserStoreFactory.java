package system.user.store;

import libraries.hibernate.Hibernate;
import libraries.id.IdUtils;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class UserStoreFactory {
    @NonNull
    private final Hibernate hibernate;
    @NonNull
    private final IdUtils idUtils;

    public UserStoreFactory(@NonNull HibernateConfig hibernateConfig) {
        this(new Hibernate(hibernateConfig));
    }

    public UserStoreFactory(Hibernate hibernate) {
        this.hibernate = hibernate;
        this.idUtils = new IdUtils();
    }

    @NonNull
    public UserStore getClient() {
        return new UserStore(idUtils, hibernate);
    }
}
