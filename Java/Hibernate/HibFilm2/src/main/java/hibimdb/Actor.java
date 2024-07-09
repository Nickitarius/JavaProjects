package hibimdb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "actors")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Actor {

    @Id
    private Integer id;

    private String firstName;

    private String lastName;

    private String gender;


}
