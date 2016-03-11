package bz1273147;

import javax.persistence.*;

/**
 * @author <a href="mailto:msimka@redhat.com">Martin Simka</a>
 */
@Entity
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @Version
    private int version;

    @Column
    private String text;

    public TestEntity() {
    }

    public TestEntity(int version, String text) {
        this.version = version;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
