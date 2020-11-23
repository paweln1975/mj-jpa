package pl.paweln.jpa.daos;

import pl.paweln.jpa.entities.User;

import javax.persistence.TypedQuery;
import java.util.List;

public class JPAUserDao extends AbstractDao<User> implements UserDao {

    @Override
    public List<User> findByLastName(String lastName) {

        TypedQuery<User> query = this.getEntityManager().createQuery("from User u where u.lastName = ?0", this.getPersistentClass());
        query.setParameter(0, lastName);

        return query.getResultList();
    }
}
