package by.telecom.tasklist.server.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.telecom.tasklist.server.dao.TaskDao;
import by.telecom.tasklist.shared.model.Employee;
import by.telecom.tasklist.shared.model.Task;

@Repository
public class TaskDaoImpl extends GenericDaoImpl<Task, Long> implements TaskDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = Logger.getLogger(TaskDao.class.getName());

	@Override
	public Task getByName(String name, String sortField, String orderType) {
		logger.info("DAO - Get by name sort = " + name + " order = " + orderType);

		Session session = sessionFactory.getCurrentSession();
		Order order = Order.asc(sortField);
		if (orderType.equals("desc"))
			order = Order.desc(sortField);
		List<Task> all = session.createCriteria(Task.class).add(Restrictions.like("name", "%" + name + "%")).addOrder(order).list();
		return all.get(0); // если несколько человек с одинаковыми именами выбирать первого
	}

	@Override
	public List<Task> getByEmployee(Employee employee) {
		logger.info("DAO - Get task by employee");

		Session session;
		logger.info("DAO - after session decloration");
		session = sessionFactory.getCurrentSession();
		logger.info("DAO - after session");
		List<Task> taskList = new ArrayList<Task>();
		session.createCriteria(Task.class)/* .add(Restrictions.eq("employee", employee)) */.list();
		return taskList;
	}

}
