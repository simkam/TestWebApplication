package bz1273147;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author <a href="mailto:msimka@redhat.com">Martin Simka</a>
 */
@Stateless
public class TestBean {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void createProcedure() {
        Session session = em.unwrap(Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                connection.setAutoCommit(false);
                try (Statement stmt = connection.createStatement()) {
                    stmt.execute(
                            "CREATE OR REPLACE PROCEDURE TEST_PROC ( " +
                                    "  ID_PARAM IN NUMBER, TEXT_PARAM OUT VARCHAR2 ) " +
                                    "AS " +
                                    "BEGIN " +
                                    "  SELECT text INTO TEXT_PARAM FROM TestEntity WHERE id = ID_PARAM; " +
                                    "END TEST_PROC; "
                    );
                }
                connection.commit();
            }
        });
    }

    public String test(int id) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery( "TestEntity.findNameById" );
        query.setParameter("ID_PARAM", id);
        return (String) query.getOutputParameterValue("TEXT_PARAM");
    }

    public int createEntity() {
        TestEntity te = new TestEntity(1, "textaa");
        em.persist(te);
        em.flush();
        return te.getId();
    }
}
