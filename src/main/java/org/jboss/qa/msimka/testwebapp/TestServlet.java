package org.jboss.qa.msimka.testwebapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
/*
 * /extension=org.wildfly.extension.datasources-agroal:add
 * /subsystem=agroal:add
 * module add --name=sybase-jdbc --resources=<path_to_jconn4.jar> --dependencies=javax.api,javax.transaction.api
 * /subsystem=datasources-agroal/driver=sybase:add(module=sybase-jdbc, class="com.sybase.jdbc4.jdbc.SybXADataSource"
 * /subsystem=datasources-agroal/xa-datasource=sybaseDS:add(jndi-name="java:jboss/datasource/sybaseDS",
 *     connection-factory={driver=sybase,connection-properties={ServerName=sybase-160.rhev-ci-vms.eng.rdu2.redhat.com,PortNumber=5000,
 *     DatabaseName=dballo18,NetworkProtocol=Tds},username="dballo18", password="dballo18"},connection-pool={min-size=2, max-size=10}
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    @Resource(lookup = "java:jboss/datasource/sybaseDS")
    private DataSource ds;

    @Resource(lookup = "java:comp/UserTransaction")
    private UserTransaction transaction;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            transaction.begin();
            List<String> usernames = new ArrayList<>(10);
            List<Connection> connections = new ArrayList<>(10);
            try {
                for (int i = 0; i < 10; i++) {
                    Connection c = ds.getConnection();
                    connections.add(c);
                    usernames.add(getSybaseUsername(c));
                }
                transaction.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            } finally {
                for (Connection c : connections) {
                    if (c != null) {
                        c.close();
                    }
                }
            }
            try (PrintWriter writer = httpServletResponse.getWriter()) {
                writer.println(usernames);
            }
        } catch (SystemException|SQLException|NotSupportedException e) {
            throw new ServletException(e);
        }
    }

    private String getSybaseUsername(Connection conn) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select suser_name()");
            String user = null;
            while (rs.next())
                user = rs.getString(1);
            return user;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null)
                stmt.close();
        }
    }


}
