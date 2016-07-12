package org.jboss.qa.msimka.testwebapp;

/**
 * @author <a href="mailto:msimka@redhat.com">Martin Simka</a>
 */
public class EmployeeDTO {
    public static final String QUERY = "SELECT NEW org.jboss.qa.msimka.testwebapp.EmployeeDTO(e.name, CAST(NULL as long), e.title) FROM Employee e WHERE e.name = :name";

    private final String m_data;

    public EmployeeDTO(String name, Long value, String title) {
        m_data = name + "," + value + "," + title;
    }

    public String toString() {
        return m_data;
    }
}
