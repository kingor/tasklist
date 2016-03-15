package by.telecom.tasklist.client.service;

import java.util.List;

import by.telecom.tasklist.shared.model.Task;

public interface TaskService {
	Long create(Task newInstance);

	Task read(Class<Task> classT, Long id);

	void update(Task transientObject);

	void delete(Task persistentObject);

	List<Task> getAll(Class<Task> classT, String sortField, String orderType);
}
