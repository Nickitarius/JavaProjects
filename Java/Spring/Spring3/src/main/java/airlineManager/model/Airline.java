package airlineManager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Objects;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class Airline {

    @NotNull(message = "Destination can not be empty!")
    private Destinations destination;

    //По сути является ID
    @NotNull(message = "Flight Number can not be empty!")
    private Integer flightNumber;

    //Дни недели, в которые рейс летает. 0 - воскресенье, 6 - суббота.
    private boolean[] availableDays = new boolean[7];

    @NotNull(message = "Please, specify the Time of Departure!")
    private LocalTime time;

    public Airline(Destinations destination, int flightNumber, boolean[] availableDays, LocalTime time) {
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.availableDays = availableDays;
        this.time = time;
    }

    //Сравнение происходит по номеру рейса, остальное несущественно
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airline airline = (Airline) o;
        return getFlightNumber() == airline.getFlightNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlightNumber());
    }

    public String writeDays() {
        String res = "";
        for (int i = 0; i < 7; i++) {
            if (availableDays[i]) {
                res += i;
                res += " ";
            }

        }
        return res;
    }
}
