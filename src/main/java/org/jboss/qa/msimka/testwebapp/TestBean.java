package org.jboss.qa.msimka.testwebapp;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author <a href="mailto:msimka@redhat.com">Martin Simka</a>
 */
@Stateless
public class TestBean {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public void persist() {
        TestEntity entity = new TestEntity();
        em.persist(entity);
    }

    public void select() {
        TypedQuery<TestEntity> query = em.createQuery("select en from TestEntity en", TestEntity.class);
        List<TestEntity> resultList = query.getResultList();
        for(TestEntity t: resultList) {
            System.out.println(t.toString());
        }
    }

}
