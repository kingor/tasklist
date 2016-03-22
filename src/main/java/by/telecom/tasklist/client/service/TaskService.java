package by.telecom.tasklist.client.service;

import java.util.Date;
import java.util.List;

import by.telecom.tasklist.shared.domain.Employee;
import by.telecom.tasklist.shared.domain.Task;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("springGwtServices/taskService")
public interface TaskService extends RemoteService {
	// Long create(Task newInstance);
	//
	// Task read(Class<Task> classT, Long id);
	//
	// void update(Task transientObject);
	//
	// void delete(Task persistentObject);

	public List<Task> getAll();

	public List<Task> getByEmployee(Employee employee);

	public List<Task> getByEmployeeMonth(Employee employee, Date dateBegin, Date dateEnd);
}
