package haircutter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "haircut_types")
@Getter
@Setter
@NoArgsConstructor
public class HaircutType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer price;

    @Override
    public String toString() {
        return this.name + " - " + price;
    }
}
