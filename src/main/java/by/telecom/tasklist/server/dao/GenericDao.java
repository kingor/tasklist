/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.telecom.tasklist.server.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ASUP8
 */
public interface GenericDao<T, PK extends Serializable> {

	PK create(T newInstance);

	T read(Class<T> classT, PK id);

	void update(T transientObject);

	void delete(T persistentObject);

	List<T> getAll(Class<T> classT);

}
