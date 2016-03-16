package by.telecom.tasklist.client.service;

import java.util.List;

import by.telecom.tasklist.shared.model.Task;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("springGwtServices/taskService")
public interface TaskService {
	Long create(Task newInstance);

	Task read(Class<Task> classT, Long id);

	void update(Task transientObject);

	void delete(Task persistentObject);

	public List<Task> getAll();
}
