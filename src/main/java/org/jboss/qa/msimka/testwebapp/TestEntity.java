package org.jboss.qa.msimka.testwebapp;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Martin Simka
 */
//@Entity
public class TestEntity {
//    @Id
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
