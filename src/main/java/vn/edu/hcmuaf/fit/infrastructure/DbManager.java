package vn.edu.hcmuaf.fit.infrastructure;

import vn.edu.hcmuaf.fit.database.DbConnection;
import vn.edu.hcmuaf.fit.service.BaseService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.*;

public class DbManager implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
	public DbManager() {
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		/* This method is called when the servlet context is initialized(when the Web application is deployed). */
		String uid = sce.getServletContext().getInitParameter("uid");
		String pwd = sce.getServletContext().getInitParameter("pwd");
		String database = sce.getServletContext().getInitParameter("database");
		BaseService.connectionPool = DbConnection.init(uid, pwd, database);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		/* This method is called when the servlet Context is undeployed or Application Server shuts down. */
		if (BaseService.connectionPool != null) BaseService.connectionPool = null;
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		/* Session is created. */
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		/* Session is destroyed. */
	}
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent sbe) {
		/* This method is called when an attribute is added to a session. */
	}
	
	@Override
	public void attributeRemoved(HttpSessionBindingEvent sbe) {
		/* This method is called when an attribute is removed from a session. */
	}
	
	@Override
	public void attributeReplaced(HttpSessionBindingEvent sbe) {
		/* This method is called when an attribute is replaced in a session. */
	}
}
