package by.telecom.tasklist.server.dao.impl;

import java.util.Date;
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
		// // logger.info("DAO - Get task by employee");
		// Session session = null;
		// // logger.info("DAO - after session decloration");
		// try {
		// logger.info("Session factory" + sessionFactory.toString());
		// // session = sessionFactory.getCurrentSession();
		// } catch (Exception e) {
		// logger.info("EXCEPTION" + e.getStackTrace());
		// }
		//
		// logger.info("DAO - after session");
		// List<Task> taskList = session.createCriteria(Task.class)/* .add(Restrictions.eq("employee", employee)) */.list();
		// return taskList;
		Session session = null;
		List<Task> all = null;
		session = sessionFactory.getCurrentSession();

		all = session.createCriteria(Task.class).add(Restrictions.eq("employee", employee)).list();
		return all;
	}

	@Override
	public List<Task> getByEmployeeMonth(Employee employee, Date monthBegin, Date monthEnd) {
		Session session = null;
		List<Task> all = null;
		session = sessionFactory.getCurrentSession();

		all = session.createCriteria(Task.class).add(Restrictions.eq("employee", employee)).add(Restrictions.le("dateBegin", monthEnd)).add(Restrictions.ge("dateEnd", monthBegin)).list();
		return all;
	}
}
