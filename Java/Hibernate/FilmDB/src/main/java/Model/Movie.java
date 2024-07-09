package Model;

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
    @JoinTable(
            name = "movies_directors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    //@Column(name = "directors_id")
    private Set<Director> directors;

    /*public static Integer staticGetYear(){
        Integer res = getYear();
        return year;
    }*/
}
