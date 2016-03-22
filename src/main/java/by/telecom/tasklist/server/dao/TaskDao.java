package by.telecom.tasklist.server.dao;

import java.util.Date;
import java.util.List;

import by.telecom.tasklist.shared.domain.Employee;
import by.telecom.tasklist.shared.domain.Task;

public interface TaskDao extends GenericDao<Task, Long> {

	Task getByName(String name, String sortField, String orderType);

	List<Task> getByEmployee(Employee employee);

	List<Task> getByEmployeeMonth(Employee employee, Date monthBegin, Date monthEnd);

}
