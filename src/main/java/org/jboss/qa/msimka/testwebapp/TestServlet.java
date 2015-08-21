package org.jboss.qa.msimka.testwebapp;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Martin Simka
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    @Resource(lookup = "java:jboss/datasources/TestDatasource")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String qry = "SELECT 1";
        PrintWriter out = httpServletResponse.getWriter();
        Connection conn1 = null;
        Connection conn2 = null;
        Connection conn3 = null;
        Connection conn4 = null;
        Connection conn5 = null;
        try {
            conn1 = ds.getConnection();
            Statement stmt1 = conn1.createStatement();
            stmt1.executeQuery(qry);
            out.println("conn1 ok");

            conn2 = ds.getConnection();
            Statement stmt2 = conn2.createStatement();
            stmt2.executeQuery(qry);
            out.println("conn2 ok");

            conn3 = ds.getConnection();
            Statement stmt3 = conn3.createStatement();
            stmt3.executeQuery(qry);
            out.println("conn3 ok");

            conn4 = ds.getConnection();
            Statement stmt4 = conn4.createStatement();
            stmt4.executeQuery(qry);
            out.println("conn4 ok");

            conn5 = ds.getConnection();
            Statement stmt5 = conn5.createStatement();
            stmt5.executeQuery(qry);
            out.println("conn4 ok");
        } catch (SQLException e) {
            out.println("fail");
            e.printStackTrace();
        } finally {
            try {
                if (conn1 != null)
                    conn1.close();
                if (conn2 != null)
                    conn2.close();
                if (conn3 != null)
                    conn3.close();
                if (conn4 != null)
                    conn4.close();
                if (conn5 != null)
                    conn5.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }
}
