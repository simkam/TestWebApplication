package org.jboss.qa.msimka;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Martin Simka
 */
@WebServlet(name = "testServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {
//    @Resource(lookup = "java:jboss/datasources/TestDatasource")
//    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:jboss/datasources/TestDatasource");

            int max = Integer.parseInt(request.getParameter("count"));
            for (int i = 0; i < max; i++) {
                try {
                    Connection conn = ds.getConnection();
                    PreparedStatement ps = conn.prepareStatement("select user from dual");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next())
                        System.out.println(rs.getString(1));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch(NamingException ne) {
            ne.printStackTrace();
        }
    }
}
