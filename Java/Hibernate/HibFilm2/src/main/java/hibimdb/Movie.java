package hibimdb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "movies")
public class Movie {

    @Id
    private Integer id;

    private String name;

    private Integer year;

    private Float rank;

    @ManyToMany
    private Set<Director> directors;

    @OneToMany
    private Set<Role> roles;


}
