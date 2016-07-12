package org.jboss.qa.msimka.testwebapp;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author <a href="mailto:msimka@redhat.com">Martin Simka</a>
 */
@Entity
public class Employee {
    @Id
    private String name;

    private Long number;

    private String title;

    public Employee(String name, String title) {
        this();
        setName(name);
        setTitle(title);
    }

    public String getName() {
        return name;
    }

    public long getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    protected Employee() {
        // this form used by Hibernate
    }

    protected void setName(String name) {
        this.name = name;
    }
}
