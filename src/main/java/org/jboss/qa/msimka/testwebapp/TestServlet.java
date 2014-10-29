package org.jboss.qa.msimka.testwebapp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.naming.ImprovedNamingStrategyDelegator;
import org.hibernate.cfg.naming.LegacyNamingStrategyDelegator;
import org.hibernate.cfg.naming.NamingStrategyDelegator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Martin Simka
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String param = request.getParameter("delegator");
        NamingStrategyDelegator delegator = LegacyNamingStrategyDelegator.DEFAULT_INSTANCE;
        if(param != null && param.equals("new")) {
            delegator = ImprovedNamingStrategyDelegator.DEFAULT_INSTANCE;
        }
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Part.class)
                .setNamingStrategyDelegator(delegator)
                .setProperty("hibernate.connection.datasource", "java:jboss/datasources/ExampleDS")
                .configure().buildSessionFactory();

        Session session = sessionFactory.openSession();
//        Query q = session.createQuery("SELECT p.parts.id FROM prod p");
//        q.list();
        session.close();

    }
}
