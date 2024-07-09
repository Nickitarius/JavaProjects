package haircutter.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "haircut_case")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HaircutCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private HaircutType type;

    @ManyToMany
    private Set<Option> options;

    @ManyToOne
    private Master master;

    private Integer totalPrice;

    public String writeOptions() {
        String res = "";
        for (Option o : options) {
            res += o.toString() + " ";
        }
        return res;
    }
}
