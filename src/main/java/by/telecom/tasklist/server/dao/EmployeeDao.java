/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.telecom.tasklist.server.dao;

import java.util.List;

import by.telecom.tasklist.shared.domain.Employee;

/**
 *
 * @author ASUP8
 */
public interface EmployeeDao extends GenericDao<Employee, Long> {

	List<Employee> getByParameter(String name, String sort, String orderType);

	List<Employee> getById(Long id);
}
