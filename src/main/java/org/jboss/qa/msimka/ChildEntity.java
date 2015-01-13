package org.jboss.qa.msimka;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Martin Simka
 */
@Entity
@Table(name = "child_table")
public class ChildEntity {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TestEntity parent;

    @Column
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TestEntity getParent() {
        return parent;
    }

    public void setParent(TestEntity parent) {
        this.parent = parent;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ChildEntity{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
