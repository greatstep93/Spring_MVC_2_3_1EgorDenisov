package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }


    @Override
    @Transactional
    public void updateUser(User user, int id) {
        Query query = entityManager.createQuery("update User set name = :name, surname = :surname" +
                ", email = :email, age = :age" +
                " where id = :idCode");
        query.setParameter("idCode", id);
        query.setParameter("name", user.getName());
        query.setParameter("surname", user.getSurname());
        query.setParameter("email", user.getEmail());
        query.setParameter("age",user.getAge());
        query.executeUpdate();
//        entityManager.merge(getUserById(id));
//        entityManager.flush();
    }

    @Override
    public void removeUser(int id) {

        entityManager.remove(getUserById(id));
    }


}
