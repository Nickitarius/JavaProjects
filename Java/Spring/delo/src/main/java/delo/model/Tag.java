package delo.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Override
    public String toString() {
        return name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    //@OneToMany(mappedBy = "tag")
    //private Set<Zadacha> zadachas = new HashSet<Zadacha>();

   // public void addZadacha(Zadacha zadacha){
    //    this.zadachas.add(zadacha);
    //}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        return getId().equals(tag.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

}
