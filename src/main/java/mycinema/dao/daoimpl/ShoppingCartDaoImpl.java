package mycinema.dao.daoimpl;

import mycinema.dao.ShoppingCartDao;
import mycinema.exceptions.DataProcessingException;
import mycinema.lib.Dao;
import mycinema.model.ShoppingCart;
import mycinema.model.User;
import mycinema.utill.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            shoppingCart.setId((Long) session.save(shoppingCart));
            transaction.commit();
            return shoppingCart;
        } catch (DataProcessingException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert ShoppingCart entity", e);
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<ShoppingCart> query = session.createQuery("FROM ShoppingCart s "
                    + "WHERE s.id= :code ");
            query.setParameter("code", user.getId());
            ShoppingCart shoppingCart = query.getSingleResult();
            Hibernate.initialize(shoppingCart.getTickets());
            return shoppingCart;
        } catch (DataProcessingException e) {
            throw new DataProcessingException("Can'nt get User entity!", e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (DataProcessingException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update ShoppingCart entity", e);
        }
    }
}
