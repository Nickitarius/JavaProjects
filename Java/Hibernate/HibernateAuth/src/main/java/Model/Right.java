package Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "Rights")
public class Right implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String Rights;


    @Override
    public int compareTo(Object o) {
//        if (this==o){
//            return 0;
//        }

        return this.id - ((Right) o).getId();
    }
}
