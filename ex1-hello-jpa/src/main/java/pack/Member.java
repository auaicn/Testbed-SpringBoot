package pack;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "second_my_sequence")
//@TableGenerator(
//    name = "MEMBER_SEQ_GENERATOR",
//    table= "MY_SEQUENCES",
//    pkColumnValue = "MEMBER_SEQ", allocationSize = 1
//)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SECOND_MY_SEQUENCE")
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MY_SEQUENCES")
    private Long id;

    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member() {

    }

    public Long getId() {
        return id;
    }
}
