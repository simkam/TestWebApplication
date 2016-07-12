package org.jboss.qa.msimka.testwebapp;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author <a href="mailto:msimka@redhat.com">Martin Simka</a>
 */
@Stateless
public class TestBean {

    @PersistenceContext
    private EntityManager em;

    public List<EmployeeDTO> test() {
        Employee e = new Employee("John Smith", "Engineer");
        em.persist(e);

        TypedQuery<EmployeeDTO> q = em.createQuery(EmployeeDTO.QUERY, EmployeeDTO.class).setParameter("name", "John Smith");
        List<EmployeeDTO> result = q.getResultList();
        return result;
    }
}
