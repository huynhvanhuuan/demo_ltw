package vn.edu.hcmuaf.fit.infrastructure;

import vn.edu.hcmuaf.fit.database.DbConnection;
import vn.edu.hcmuaf.fit.database.IConnectionPool;
import vn.edu.hcmuaf.fit.service.impl.AppMailServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Properties;

public class DbManager implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
	public static IConnectionPool connectionPool = null;
	
	public DbManager() {
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		/* This method is called when the servlet context is initialized(when the Web application is deployed). */
		try {
			// String uid = sce.getServletContext().getInitParameter("uid");
			// String pwd = sce.getServletContext().getInitParameter("pwd");
			// String database = sce.getServletContext().getInitParameter("database");

			Properties properties = new Properties();
			properties.load(AppMailServiceImpl.class.getClassLoader().getResourceAsStream("application.properties"));
			String uid = properties.getProperty("datasource.uid");
			String pwd = properties.getProperty("datasource.pwd");
			String database = properties.getProperty("datasource.database");

			connectionPool = DbConnection.init(uid, pwd, database);
			sce.getServletContext().log("Connection pool is created");
		} catch (IOException e) {
			sce.getServletContext().log("Error: " + e.getMessage());
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		/* This method is called when the servlet Context is undeployed or Application Server shuts down. */
		if (connectionPool != null) {
			connectionPool.shutdown();
			connectionPool = null;
		}
		sce.getServletContext().log("ServletContextListener destroyed");
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		/* Session is created. */
		se.getSession().getServletContext().log("Session created: " + se.getSession().getId());
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		/* Session is destroyed. */
		se.getSession().getServletContext().log("Session destroyed: " + se.getSession().getId());
	}
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent sbe) {
		/* This method is called when an attribute is added to a session. */
		sbe.getSession().getServletContext().log("Attribute added to session: " + sbe.getName());
	}
	
	@Override
	public void attributeRemoved(HttpSessionBindingEvent sbe) {
		/* This method is called when an attribute is removed from a session. */
		sbe.getSession().getServletContext().log("Attribute removed from session: " + sbe.getName());
	}
	
	@Override
	public void attributeReplaced(HttpSessionBindingEvent sbe) {
		/* This method is called when an attribute is replaced in a session. */
		sbe.getSession().getServletContext().log("Attribute replaced in session: " + sbe.getName());
	}
}
