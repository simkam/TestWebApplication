package org.jboss.qa.msimka.testwebapp;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Queue;

/**
 * @author Martin Simka
 */
@Stateless
public class TestBean {
    private DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSSS");

    @PersistenceContext
    private EntityManager em;

    public int save(Date d) {
        TestEntity testEntity = new TestEntity();
        System.out.println(df.format(d));
        testEntity.setSomeDate(d);
        em.persist(testEntity);
        em.flush();
        return testEntity.getId();
    }

    public void load(int id) {
        TestEntity testEntity = em.find(TestEntity.class, id);
        Date d  = testEntity.getSomeDate();
        System.out.println(df.format(d));
    }

    public void find(Date d) {
        TypedQuery<TestEntity> q = em.createQuery("SELECT e FROM TestEntity e WHERE e.someDate=:date", TestEntity.class);
        q.setParameter("date", d);
        try {
            TestEntity t = q.getSingleResult();
            System.out.println(df.format(t.getSomeDate()));
        }catch(NoResultException nre) {
            System.out.println("nothing found");
        } catch (NonUniqueResultException nure) {
            System.out.println("multiple results");
        }
    }
}
