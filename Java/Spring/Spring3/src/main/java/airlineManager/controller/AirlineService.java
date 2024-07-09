package airlineManager.controller;

import airlineManager.model.Airline;
import airlineManager.model.Destinations;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.time.temporal.ChronoUnit.*;

@Service("airlineService")
public class AirlineService {

    private List<Airline> airlines = new LinkedList<Airline>();
    //private String fileAddress = "src/main/webapp/resources/airlines.xml";

    public AirlineService() throws FileNotFoundException {
        //File file = new File(fileAddress);
        //Scanner scanner = new Scanner(file);
        airlines.addAll(Arrays.asList(new Airline[]{
                new Airline(Destinations.Vasiani, 96, new boolean[]{true, false, false, true, false, false, false}
                        , LocalTime.of(14, 0)),
                new Airline(Destinations.Miniralniye_Vodi, 31, new boolean[]{false, false, true, false, false, false, false}
                        , LocalTime.of(9, 30))
        }));
    }

    public void save(Airline airline) {
        if (!airlines.contains(airline)) {
            this.airlines.add(airline);
        }
    }

    public Airline find(Integer flightNumber) {
        for (Airline a :
                airlines) {
            if (a.getFlightNumber() == flightNumber) {
                return a;
            }
        }
        return null;
    }

    public List<Airline> findAll() {
        return airlines;
    }

    public LocalDateTime getTimeToClosestFlight(Airline airline) {
        //Находим минимальное расстояния в наносекундах
        long distances[] = new long[7];
        for (int i = 0; i < 7; i++) {
            if (airline.getAvailableDays()[i]) {
                LocalDateTime t = LocalDateTime.of(LocalDate.now(), airline.getTime());
                if (LocalDateTime.now().getDayOfWeek().getValue() > i) {
                    distances[i] = SECONDS.between(LocalDateTime.now(),
                            t.plus(7 +i- LocalDate.now().getDayOfWeek().getValue(), DAYS));
                } else if ( LocalDateTime.now().getDayOfWeek().getValue() == i &&
                        LocalTime.now().compareTo(airline.getTime()) >= 0) {
                    distances[i] = SECONDS.between(LocalDateTime.now(),
                            t.plus(7, DAYS));
                } else if ( LocalDateTime.now().getDayOfWeek().getValue() == i &&
                        LocalTime.now().compareTo(airline.getTime()) < 0) {
                    distances[i] = SECONDS.between(LocalTime.now(), airline.getTime());
                } else if (LocalDateTime.now().getDayOfWeek().getValue() < i) {
                    distances[i] = SECONDS.between(LocalDateTime.now(),
                            t.plus(i - LocalDate.now().getDayOfWeek().getValue(), DAYS));
                }
            } else {
                distances[i] = Long.MAX_VALUE;
            }
            //distances[i] = Math.abs(distances[i]);
            //LocalDateTime t = SECONDS.between(LocalDateTime.now(), LocalDateTime.);
        }
        long min = Long.MAX_VALUE;
        for (long n : distances) {
            if (n < min) {
                min = n;
            }
        }
        LocalDateTime res = LocalDateTime.of(0, 1, 1, 0, 0);
        res = SECONDS.addTo(res, min);
        return res;
    }
}
