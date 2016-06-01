package bz1273147;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

/**
 * @author <a href="mailto:msimka@redhat.com">Martin Simka</a>
 */

@NamedStoredProcedureQuery(name = "TestEntity.findNameById",
        resultClasses = TestEntity.class,
        procedureName = "TEST_PROC"
        ,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_PARAM", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "TEXT_PARAM", type = String.class)
        }
)
@Entity
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
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
