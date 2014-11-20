package org.jboss.qa.msimka.testwebapp;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Martin Simka
 */
@Stateless
public class TestBean {

    @PersistenceContext
    private EntityManager em;


    private void test() {
        // nothing
    }

}
