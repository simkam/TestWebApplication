package bz1273147;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author <a href="mailto:msimka@redhat.com">Martin Simka</a>
 */
@Stateless
public class TestBean {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public int saveNewEntity(String text) {
        TestEntity t = new TestEntity();
        t.setText(text);
        em.persist(t);
        return t.getId();
    }

    public List<TestEntity> findAll() {
        TypedQuery<TestEntity> q = em.createQuery("SELECT t FROM TestEntity t", TestEntity.class);
        q.setHint("org.hibernate.cacheable", Boolean.TRUE);
        return q.getResultList();
    }

    public TestEntity findById(int id) {
        return em.find(TestEntity.class, id);
    }

    public void changeText(int id, String text) {
        TestEntity e = em.find(TestEntity.class, id);
        e.setText(text);
        em.merge(e);
    }
}
