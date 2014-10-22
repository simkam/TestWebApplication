package org.jboss.qa.msimka.testwebapp;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Martin Simka
 */
@WebServlet(name = "testServlet", urlPatterns = { "/TestServlet" })
public class TestServlet extends HttpServlet {
    @EJB
    private TestBean testBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Calendar c = new GregorianCalendar(2014, 06, 06, 22, 11, 33);
        c.add(Calendar.MILLISECOND, 9999);
        int id = testBean.save(new Date(c.getTimeInMillis()));
        testBean.load(id);
        testBean.find(new Date(c.getTimeInMillis()));
    }
}
