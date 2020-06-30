package mycinema.service.serviceimpl;

import java.util.List;
import mycinema.dao.EmployeeDao;
import mycinema.lib.Inject;
import mycinema.lib.Service;
import mycinema.model.Employee;
import mycinema.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Inject
    EmployeeDao employeeDao;

    @Override
    public Employee add(Employee employee) {
        return employeeDao.add(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }
}
