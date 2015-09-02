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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Martin Simka
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    @Resource(lookup = "java:jboss/datasources/OracleUCPDS")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String qry = "SELECT user FROM dual";
        PrintWriter out = httpServletResponse.getWriter();
        Connection conn1 = null;
        try {
            System.out.println("getting connection");
            conn1 = ds.getConnection();
            Statement stmt1 = conn1.createStatement();
            ResultSet rs = stmt1.executeQuery(qry);
            while(rs.next())
                out.println(rs.getString(1));
            out.println("conn1 ok");
        } catch (SQLException e) {
            out.println("fail");
            e.printStackTrace();
        } finally {
            try {
                if (conn1 != null) {
                    System.out.println("closing connection");
                    conn1.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }
}
