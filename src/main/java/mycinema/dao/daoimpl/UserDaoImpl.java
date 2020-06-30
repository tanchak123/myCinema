package mycinema.dao.daoimpl;

import mycinema.dao.UserDao;
import mycinema.exceptions.DataProcessingException;
import mycinema.lib.Dao;
import mycinema.model.User;
import mycinema.utill.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User add(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user.setId((Long) session.save(user));
            transaction.commit();
            return user;
        } catch (DataProcessingException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert User entity", e);
        }
    }

    @Override
    public User findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :code");
            query.setParameter("code", email);
            return query.uniqueResult();
        } catch (DataProcessingException e) {
            throw new DataProcessingException("Can't get User entity's list", e);
        }
    }
}
