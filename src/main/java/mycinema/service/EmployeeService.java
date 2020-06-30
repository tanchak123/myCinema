package mycinema.service;

import java.util.List;
import mycinema.model.Employee;

public interface EmployeeService {
    Employee add(Employee employee);

    List<Employee> getAll();
}
