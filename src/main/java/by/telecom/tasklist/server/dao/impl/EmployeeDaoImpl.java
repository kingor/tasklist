/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.telecom.tasklist.server.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.telecom.tasklist.server.dao.EmployeeDao;
import by.telecom.tasklist.shared.model.Employee;

/**
 *
 * @author kingor
 */
@Repository
public class EmployeeDaoImpl extends GenericDaoImpl<Employee, Long> implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = Logger.getLogger(EmployeeDao.class.getName());

	@Override
	public List<Employee> getByParameter(String name, String sortField, String orderType) {
		logger.info("Get by parameter sort = " + name + " order = " + orderType);
		Session session = null;
		List<Employee> all = null;

		session = sessionFactory.getCurrentSession();
		Order order = Order.asc(sortField);
		if (orderType.equals("desc"))
			order = Order.desc(sortField);
		System.out.println("!!!!!!!!DEBAG name" + name);
		all = session.createCriteria(Employee.class).add(Restrictions.like("name", "%" + name + "%")).addOrder(order).list();
		return all;
	}

	@Override
	public List<Employee> getById(Long id) {
		Session session = null;
		List<Employee> all = null;
		session = sessionFactory.getCurrentSession();

		all = session.createCriteria(Employee.class).add(Restrictions.eq("id", id)).list();
		return all;
	}
}
