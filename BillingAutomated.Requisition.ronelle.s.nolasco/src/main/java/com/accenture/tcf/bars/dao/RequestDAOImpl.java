package com.accenture.tcf.bars.dao;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.accenture.tcf.bars.datasource.MySqlDatasource;
import com.accenture.tcf.bars.domain.Request;

@Service
public class RequestDAOImpl implements IRequestDAO {
	final static Logger log = Logger.getLogger(RequestDAOImpl.class);
	
	@Override
	public void insertRequest(Request request) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Request.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			log.info("Creating request ..");
			
			session.beginTransaction();
			log.info("Starting Transaction");
			session.save(request);
			log.info("Saving");
			session.getTransaction().commit();
			log.info("Saving Done!! The Request Successfully Inserted");
			
		} finally {
			factory.close();
		}
		
	}

	@Override
	public void delete() {


		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Request.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		List<Request> list = new ArrayList<Request>();
		try {
			session = factory.openSession();
	        session.beginTransaction();
			list = session.createCriteria(Request.class).list();
	        session.getTransaction().commit();
	    }
	    catch (HibernateException e) {
	       e.printStackTrace();
	       session.getTransaction().rollback();
	    }
		finally {
			session.close();
		}
		for(Request request : list) {
			session = factory.openSession();
			try {
				session.beginTransaction();
				log.info("Delete transaction start");
	        	session.delete(request);
	        	log.info("Deleting Requests");
		        session.getTransaction().commit();
		        log.info("Request Succesfully Deleted");
		        
			}
			catch (HibernateException e) {
		       e.printStackTrace();
		       session.getTransaction().rollback();
		    }
			finally {
				session.close();
			}
        }
		
	}




}
