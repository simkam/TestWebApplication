package org.jboss.qa.msimka.testwebapp;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.SynchronizationType;

/**
 * @author <a href="mailto:msimka@redhat.com">Martin Simka</a>
 */
@Stateful
public class SFSB2 {
    @PersistenceContext(unitName = "primary", synchronization = SynchronizationType.SYNCHRONIZED)
    private EntityManager em;

    public TestEntity find(int id) {
        return em.find(TestEntity.class, id);
    }
}
