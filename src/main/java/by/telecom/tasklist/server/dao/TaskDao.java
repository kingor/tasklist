package by.telecom.tasklist.server.dao;

import by.telecom.tasklist.shared.model.Task;

public interface TaskDao extends GenericDao<Task, Long> {
	public Task getByName(String name, String sortField, String orderType);
}
