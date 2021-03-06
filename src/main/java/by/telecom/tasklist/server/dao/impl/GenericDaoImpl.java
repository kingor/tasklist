/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.telecom.tasklist.server.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.telecom.tasklist.server.dao.GenericDao;

/**
 *
 * @author kingor
 * @param <T>
 *            Entity class
 * @param <PK>
 *            primary key of entity
 */
@Repository
public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = Logger.getLogger(GenericDao.class.getName());

	@Override
	public List<T> getAll(Class classT) {
		logger.log(Level.INFO, "GENERIC DAO - getAll entities ");
		Session session = null;
		List<T> all = null;
		session = sessionFactory.getCurrentSession();

		all = session.createCriteria(classT).list();
		return all;
	}

	@Override
	public PK create(T newInstance) {
		logger.info("GENERIC DAO - create entity");
		Session session = sessionFactory.getCurrentSession();
		return (PK) session.save(newInstance);
	}

	@Override
	public T read(Class<T> classT, PK id) {
		logger.info("GENERIC DAO - read entity with id = " + id);
		Session session = sessionFactory.getCurrentSession();
		T objectT = (T) session.createCriteria(classT).add(Restrictions.eq("id", id)).uniqueResult();
		return objectT;
	}

	@Override
	public void update(T transientObject) {
		logger.info("GENERIC DAO - update entity");
		Session session = sessionFactory.getCurrentSession();
		session.update(transientObject);
	}

	@Override
	public void delete(T persistentObject) {
		logger.info("GENERIC DAO - delete entity" + persistentObject);
		Session session = sessionFactory.getCurrentSession();
		session.delete(persistentObject);
	}

}
