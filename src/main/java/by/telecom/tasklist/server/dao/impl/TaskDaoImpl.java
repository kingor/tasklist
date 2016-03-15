package by.telecom.tasklist.server.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
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

	private static final Logger logger = Logger.getLogger(TaskDao.class);

	@Override
	public Task getByName(String name, String sortField, String orderType) {
		logger.info("Get by name sort = " + name + " order = " + orderType);

		Session session = sessionFactory.getCurrentSession();
		Order order = Order.asc(sortField);
		if (orderType.equals("desc"))
			order = Order.desc(sortField);
		List<Task> all = session.createCriteria(Employee.class).add(Restrictions.like("name", "%" + name + "%")).addOrder(order).list();
		return all.get(0);
	}

}
