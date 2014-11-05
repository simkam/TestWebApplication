package org.jboss.qa.msimka.testwebapp;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Martin Simka
 */
@Stateless
public class TestBean {
    @Resource(lookup = "java:jboss/datasources/TestDatasource")
    private DataSource ds;

    @Resource(lookup = "java:jboss/datasources/ExampleXADS")
    private DataSource XAds;

    public void test() {
        Connection XAconn = null;
        Connection nonXAconn = null;
        Statement XAstmt = null;
        Statement stmt = null;

        try {
            XAconn = XAds.getConnection();
            nonXAconn = ds.getConnection();

            XAstmt = XAconn.createStatement();
            stmt = nonXAconn.createStatement();

            XAstmt.executeUpdate("CREATE TABLE PUBLIC.TEST_TABLE(COL1 INTEGER NOT NULL)");
            XAstmt.executeUpdate("INSERT INTO PUBLIC.TEST_TABLE(COL1) VALUES (1)");


            try {
                stmt.executeUpdate("DROP TABLE JBOSS_EJB_TIMER");
            } catch (SQLException aaaaaa) {
                ///
            }

            stmt.executeUpdate("CREATE TABLE JBOSS_EJB_TIMER(ID VARCHAR(255) PRIMARY KEY NOT NULL)");

            stmt.executeUpdate("INSERT INTO JBOSS_EJB_TIMER(ID) VALUES ('2')");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (XAstmt != null) {
                    XAstmt.close();
                }
                if (XAconn != null) {
                    XAconn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (nonXAconn != null) {
                    nonXAconn.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

    }
}
