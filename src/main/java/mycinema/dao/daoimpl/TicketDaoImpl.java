package mycinema.dao.daoimpl;

import mycinema.dao.TicketDao;
import mycinema.exceptions.DataProcessingException;
import mycinema.lib.Dao;
import mycinema.model.Ticket;
import mycinema.utill.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            ticket.setId((Long) session.save(ticket));
            transaction.commit();
            return ticket;
        } catch (DataProcessingException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Ticket entity!", e);
        }
    }
}
