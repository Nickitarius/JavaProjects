import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String APIkey = "1a73cbf8ac89ee26916051e7f578292e";
        //String which contains request sent to openweathermap API
        String Request;
        String CitiesID[] = {"2023469", "498698", "478544", "690485", "658225", "2073124", "2013348", "524901", "3067696", "2643743"};
        Weather CitiesWeather[] = new Weather[10];
        for (int i = 0; i < 10; i++) {
            Request = "https://api.openweathermap.org/data/2.5/weather?id=" + CitiesID[i] + "&appid=" + APIkey + "&units=metric";
            //URL of the API request
            URL weatherUrl = new URL(Request);
            //Reading JSON into a string
            InputStream JSONStream = (InputStream) weatherUrl.getContent();
            //Reading from the JSON-derived string into our Weather object
            InputStreamReader reader = new InputStreamReader(JSONStream);
            //Creating a Weather instance read from 'reader'
            CitiesWeather[i] = gson.fromJson(reader, Weather.class);
        }
        //Bubble-sorting cities by temperature from hottest to coldest
        Weather temp;
        for (int i = CitiesWeather.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (CitiesWeather[j].main.temp < CitiesWeather[j + 1].main.temp) {
                    temp = CitiesWeather[j];
                    CitiesWeather[j] = CitiesWeather[j + 1];
                    CitiesWeather[j + 1] = temp;
                }
            }
        }
        for (Weather w : CitiesWeather) {
            System.out.println(w.name + ": " + w.main.temp);
        }
    }

}
