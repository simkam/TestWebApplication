package org.jboss.qa.msimka;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Martin Simka
 */
@Stateless
public class TestBean {

    @PersistenceContext
    private EntityManager em;

    public void test() {
        TypedQuery<TestEntity> q = em.createNamedQuery("TestEntity.findAll", TestEntity.class);
        TypedQuery<TestEntity> query2 = em.createQuery("SELECT t FROM TestEntity t WHERE t.value='string5'", TestEntity.class);
        List<TestEntity> list =  q.getResultList();
        System.out.println("<<<LIST - size: " + list.size() + ">>>");
        for(TestEntity e : list) {
            System.out.println(e);
            for(ChildEntity ce : e.getChildEntities()) {
                System.out.println(ce);
            }
        }

        System.out.println("<<<SINGLE>>>");
        System.out.println(query2.getSingleResult());

        System.out.println("<<<ENTITY>>>");
        for(int i=1; i<13; i++) {
            TestEntity e = em.find(TestEntity.class, i);
            System.out.println(e);
            for(ChildEntity ce : e.getChildEntities()) {
                System.out.println(ce);
            }
        }

        list =  q.getResultList();
        System.out.println("<<<LIST>>>");
        for(TestEntity e : list) {
            System.out.println(e);
            for(ChildEntity ce : e.getChildEntities()) {
                System.out.println(ce);
            }
        }
    }
}
