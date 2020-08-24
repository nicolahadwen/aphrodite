package system.user.store;

import libraries.func.validatable.ValidatableFunc;
import libraries.hibernate.Hibernate;
import libraries.id.IdUtils;
import libraries.validation.ValidationResult;
import libraries.validation.Validator;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

@AllArgsConstructor
public class CreateUserFunc extends ValidatableFunc<CreateUserParam, UserEntity> {
    private final Hibernate hibernate;
    private final IdUtils idUtils;

    @Override
    protected UserEntity doApply(CreateUserParam param) {
        String id = idUtils.generateId();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(id);
        userEntity.setEmail(param.getEmail());
        userEntity.setPassword(param.getPassword());
        userEntity.setUsername(param.getUsername());

        Session session = hibernate.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(userEntity);
        try {
            UserEntity createdUser = session.load(UserEntity.class, id);
            transaction.commit();
            return createdUser;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Validator<CreateUserParam> createValidator(CreateUserParam param) {
        return param1 -> ValidationResult.SUCCESS;
    }
}
