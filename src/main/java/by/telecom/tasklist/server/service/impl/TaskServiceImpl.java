package by.telecom.tasklist.server.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.telecom.tasklist.client.service.TaskService;
import by.telecom.tasklist.shared.model.Task;

@Service
public class TaskServiceImpl implements TaskService {

	@Override
	@Transactional
	public Long create(Task newInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Task read(Class<Task> classT, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void update(Task transientObject) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void delete(Task persistentObject) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public List<Task> getAll(Class<Task> classT, String sortField, String orderType) {
		// TODO Auto-generated method stub
		return null;
	}

}
