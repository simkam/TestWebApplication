package org.jboss.qa.msimka.testwebapp;

import javax.persistence.*;

/**
 * @author <a href="mailto:msimka@redhat.com">Martin Simka</a>
 */
@Entity
public class TestEntity {

    @Id
    private Integer id;

    public TestEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
