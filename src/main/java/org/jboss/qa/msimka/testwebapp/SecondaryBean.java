package org.jboss.qa.msimka.testwebapp;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author <a href="mailto:msimka@redhat.com">Martin Simka</a>
 */
@Stateless
public class SecondaryBean {

    @PersistenceContext(unitName = "secondary")
    private EntityManager em;
    
    public void test() {
        Query query = em.createQuery("select en from TestEntity en");
        query.getResultList();
    }

}
