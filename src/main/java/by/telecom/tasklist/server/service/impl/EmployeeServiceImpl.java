package by.telecom.tasklist.server.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.telecom.tasklist.client.service.EmployeeService;
import by.telecom.tasklist.server.dao.EmployeeDao;
import by.telecom.tasklist.shared.model.Employee;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	private static final Logger logger = Logger.getLogger(EmployeeService.class);

	@Override
	@Transactional
	public List<Employee> getAll() {
		logger.info("EmployeeService Get all subscriber!");
		return employeeDao.getAll(Employee.class);// return employeeDao.getAll(Employee.class);
	}
	//
	// @Override
	// @Transactional
	// public Long create(Employee employee) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// @Transactional
	// public Employee read(Class<Employee> classT, Long id) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// @Transactional
	// public void update(Employee employee) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// @Transactional
	// public void delete(Employee employee) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// @Transactional
	// public List<Employee> getByParameter(String name, String sortField, String orderType) {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
