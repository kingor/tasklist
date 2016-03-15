/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.telecom.tasklist.server.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
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
	private static final Logger logger = Logger.getLogger(EmployeeDao.class);

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

}
