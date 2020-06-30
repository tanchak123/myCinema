package mycinema.dao.daoimpl;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mycinema.dao.CinemaHallDao;
import mycinema.exceptions.DataProcessingException;
import mycinema.lib.Dao;
import mycinema.model.CinemaHall;
import mycinema.utill.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class CinemaHallDaoImpl implements CinemaHallDao {

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            cinemaHall.setId((Long) session.save(cinemaHall));
            transaction.commit();
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert CinemaHall entity", e);
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<CinemaHall> criteriaQuery = session
                    .getCriteriaBuilder().createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            List<CinemaHall> resultList = session.createQuery(criteriaQuery).getResultList();
            return resultList;
        } catch (Exception e) {
            throw new DataProcessingException("Can't get list of CinemaHall's", e);
        }
    }
}
