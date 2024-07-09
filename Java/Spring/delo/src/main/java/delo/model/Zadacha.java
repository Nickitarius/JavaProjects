package delo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "zadacha")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Zadacha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Tag tag;
    private String opisanie;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public Zadacha() {
        //tags = new HashSet<Tag>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zadacha zadacha = (Zadacha) o;

        if (getId() != null ? !getId().equals(zadacha.getId()) : zadacha.getId() != null) return false;
        if (getTag() != null ? !getTag().equals(zadacha.getTag()) : zadacha.getTag() != null) return false;
        if (getOpisanie() != null ? !getOpisanie().equals(zadacha.getOpisanie()) : zadacha.getOpisanie() != null)
            return false;
        return getDate() != null ? getDate().equals(zadacha.getDate()) : zadacha.getDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTag() != null ? getTag().hashCode() : 0);
        result = 31 * result + (getOpisanie() != null ? getOpisanie().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }
}
