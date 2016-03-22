package by.telecom.tasklist.client.service;

import java.util.List;

import by.telecom.tasklist.shared.domain.Employee;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("springGwtServices/employeeService")
public interface EmployeeService extends RemoteService {

	public List<Employee> getAll();

	// public List<Employee> getById(Long id);

	// Long create(Employee employee);
	//
	// Employee read(Long id);
	//
	// void update(Employee employee);
	//
	// void delete(Employee employee);

}
