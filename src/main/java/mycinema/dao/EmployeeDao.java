package mycinema.dao;

import java.util.List;
import mycinema.model.Employee;

public interface EmployeeDao {
    Employee add(Employee employee);

    List<Employee> getAll();
}
