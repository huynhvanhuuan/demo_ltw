package vn.edu.hcmuaf.fit.infrastructure;

import javax.servlet.*;
import javax.servlet.http.*;

public class DbManager implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    // public IConnectionPool connectionPool;

    public DbManager() {}

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        String uid = sce.getServletContext().getInitParameter("uid");
        String pwd = sce.getServletContext().getInitParameter("pwd");
        String database = sce.getServletContext().getInitParameter("database");
        // connectionPool = DbConnection.init(uid, pwd, database);
        // sce.getServletContext().setAttribute("connectionPool", connectionPool);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        // if (connectionPool != null) connectionPool = null;
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
