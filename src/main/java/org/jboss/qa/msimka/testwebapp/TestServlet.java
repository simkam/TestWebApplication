package org.jboss.qa.msimka.testwebapp;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * @author Martin Simka
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    @Resource(lookup = "java:jboss/datasources/CHAT_DS")
    private DataSource chatDS;

    @Resource(lookup = "java:jboss/datasources/ADT_DS")
    private DataSource adtDS;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            DatabaseMetaData metaChatDS = chatDS.getConnection().getMetaData();
            System.out.println("ChatDS version should be 12 and it's: " + metaChatDS.getDriverVersion());

            DatabaseMetaData metaAdtDS = adtDS.getConnection().getMetaData();
            System.out.println("ADTDS version should be 11 and it's: " + metaAdtDS.getDriverVersion());

        } catch (SQLException sqle) {
            throw new ServletException(sqle);
        }
    }
}
