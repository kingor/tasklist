package by.telecom.tasklist.server.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.telecom.tasklist.client.service.TaskService;
import by.telecom.tasklist.server.dao.TaskDao;
import by.telecom.tasklist.shared.model.Task;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

	private static final Logger logger = Logger.getLogger(TaskService.class.getName());

	@Autowired
	TaskDao taskDao;

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
	public List<Task> getAll() {
		logger.info("SERVICE - TaskService Get all subscriber!");
		return taskDao.getAll(Task.class);
	}

}
