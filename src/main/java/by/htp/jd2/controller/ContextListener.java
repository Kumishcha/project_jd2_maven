package by.htp.jd2.controller;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import by.htp.jd2.dao.pool.PoolConnection;

public class ContextListener implements ServletContextListener {

	private static final Logger log = Logger.getLogger(ContextListener.class);
	private static final PoolConnection pc = PoolConnection.getInstance();
	
	public void contextInitialized(ServletContextEvent contextEvent) {

		BasicConfigurator.configure();

		pc.makePoolConnection();
		log.info("the PoolConnection is initialized");
	}

	public void contextDestroyed(ServletContextEvent contextEvent) {

		pc.closeConnectionInPool();
		log.info("the PoolConnection is closed");

	}

}
