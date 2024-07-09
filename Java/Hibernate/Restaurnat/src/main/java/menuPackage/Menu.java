package menuPackage;


import jdk.jfr.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="menu_Id")
    private Long id;

    private String title;

    private String LocalDate;

    //@Column(name="items")
    @OneToMany//(mappedBy = "menus_id")
    @JoinColumn(name = "Menu_id")
    private List<MenuItem> items;

    public void addItem(MenuItem item) {
        if (items == null) {
            items = new ArrayList<MenuItem>();
        }
        items.add(item);
        System.out.println("Item " + item.getName() + " added to " + this.title + " menu.");
    }

}
