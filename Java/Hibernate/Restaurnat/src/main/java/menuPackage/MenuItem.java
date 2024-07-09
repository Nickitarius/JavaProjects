package menuPackage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "MenuItems")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer fat;

    private Integer calories;

    private Integer carbohydrates;

    private Integer protein;


    //@JoinColumn(name = "Menu id")
    //private Menu menu;

    //Конструктор без параметров автоматически генерируется благодаря @NoArgsConstructor
}
