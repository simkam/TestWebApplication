package org.jboss.qa.msimka.testwebapp;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.SynchronizationType;

/**
 * @author <a href="mailto:msimka@redhat.com">Martin Simka</a>
 */
@Stateful
public class SFSB1 {
    @PersistenceContext(unitName = "primary", synchronization = SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;

    @EJB
    private SFSB2 sfsb2;

    public void test() {
        TestEntity e = new TestEntity(5);
        em.persist(e);
        sfsb2.find(5);
    }


}
