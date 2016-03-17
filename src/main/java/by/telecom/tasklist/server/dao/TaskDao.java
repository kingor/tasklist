package by.telecom.tasklist.server.dao;

import java.util.List;

import by.telecom.tasklist.shared.model.Employee;
import by.telecom.tasklist.shared.model.Task;

public interface TaskDao extends GenericDao<Task, Long> {
	public Task getByName(String name, String sortField, String orderType);

	public List<Task> getByEmployee(Employee employee);
}
