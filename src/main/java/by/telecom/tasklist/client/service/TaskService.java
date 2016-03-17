package by.telecom.tasklist.client.service;

import java.util.List;

import by.telecom.tasklist.shared.model.Employee;
import by.telecom.tasklist.shared.model.Task;

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
}
