package org.jboss.qa.msimka.testwebapp;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author <a href="mailto:msimka@redhat.com">Martin Simka</a>
 */
@Stateless
public class TestBean {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public void test() {
        TestEntity entity = new TestEntity();
        em.persist(entity);
    }

}
