package mycinema.dao.daoimpl;

import java.util.List;
import mycinema.dao.OrderDao;
import mycinema.exceptions.DataProcessingException;
import mycinema.lib.Dao;
import mycinema.model.Order;
import mycinema.utill.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            order.setId((Long) session.save(order));
            transaction.commit();
            return order;
        } catch (DataProcessingException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Order entity!", e);
        }
    }

    public List<Order> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Order> query = session.createQuery("FROM Order ");
            List<Order> orders = query.getResultList();
            orders.forEach(order -> Hibernate.initialize(order.getTicket()));
            return orders;
        } catch (DataProcessingException e) {
            throw new DataProcessingException("Can't get list of all Orders", e);
        }
    }
}
