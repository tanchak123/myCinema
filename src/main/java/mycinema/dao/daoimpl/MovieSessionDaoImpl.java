package mycinema.dao.daoimpl;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mycinema.dao.MovieSessionDao;
import mycinema.exceptions.DataProcessingException;
import mycinema.lib.Dao;
import mycinema.model.MovieSession;
import mycinema.utill.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            movieSession.setId((Long) session.save(movieSession));
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert MovieSession entity", e);
        }
    }

    @Override
    public List<MovieSession> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<MovieSession> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(MovieSession.class);
            criteriaQuery.from(MovieSession.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get list of MovieSession's", e);
        }
    }
}
