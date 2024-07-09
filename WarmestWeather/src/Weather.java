//Class to handle weather data received as JSON
public class Weather {

    Sys sys;
    String name;
    M main;

    public Weather(Sys sys, String name, M main) {
        this.sys = sys;
        this.name = name;
        this.main = main;
    }
}
