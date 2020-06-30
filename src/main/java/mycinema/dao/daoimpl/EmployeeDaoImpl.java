package mycinema.dao.daoimpl;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mycinema.dao.EmployeeDao;
import mycinema.exceptions.DataProcessingException;
import mycinema.lib.Dao;
import mycinema.model.Employee;
import mycinema.utill.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public Employee add(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            employee.setId((Long) session.save(employee));
            transaction.commit();
            return employee;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Employee entity", e);
        }
    }

    @Override
    public List<Employee> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Employee> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Employee.class);
            criteriaQuery.from(Employee.class);
            List<Employee> employees = session.createQuery(criteriaQuery).getResultList();
            return employees;
        } catch (Exception e) {
            throw new DataProcessingException("Cant get list of Employye's", e);
        }
    }
}
