import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    static final String key = "82e622ccc27b4ad0af0918182329a742";

    /*Эпштейн не убивал себя. //
Ich frage euch: Wollt ihr den totalen Krieg?
Wollt ihr ihn, wenn nötig, totaler und radikaler, als wir ihn uns heute überhaupt erst vorstellen können? //*/

    public static void main(String[] args) throws IOException {
        File input = new File("input.txt");
        File output = new File("output1.txt");
        Scanner reader = new Scanner(input);
        Scanner console = new Scanner(System.in);
        String PostText = "";
        String to, from;
        String res = "";
        System.out.println("Enter language codes for 'to' and 'from': ");
        to = console.next();
        from = console.next();
        while (reader.hasNext()) {
            PostText += reader.nextLine();
        }
        System.out.println(PostText);
        res = translateViaAzure(PostText, to, from);
        System.out.println(res);
        Files.writeString(Path.of("output.txt"), res);
    }

    /* My fellow brothers, I, Billy Herrington, stand here today, humbled by the task before us,
 mindful of the sacrifices born by our nico Nico ancestors. We are in the midst of crisis.
 Nico Nico Doga is at war against a far reaching storm of disturbance and of leash.
 Nico Nico's economy is badly weakened, a consequence of carelessness and irresponsibility of the part of management,
 but also on the collective failure to make hard choices and prepare for a new, mad age.
 Today, I say to you that the challenge is real, they are serious, and there are many.
 They will not be easily met or in a short span of time, but know that at Nico Nico, they will be met.
 In reaffirming the greatness of our site, we understand that greatness is never given,
 our journey has never been one of shortcuts. It has not been for the path, for the feint hearted,
 or seek only the fleshly pleasures. Rather, it has been the risk takers, the wasted genie, the creators of mad things.*/


    //from = 'auto' значит автоопределение языка
    public static String translateViaAzure(String origText, String to, String from) throws IOException {
        //Задание 'api-version'  и 'to' через setRequestProperty приводило к ответу с кодом '400', не смог найти ошибку.
        //Пример перевода от майков давал такой альтернативный вариант как приписывание этих параметров к URL.
        String URLparameters = "?api-version=3.0&to=" + to;
        if (!from.matches("(.*)auto(.*)")) {
            System.out.print(from);
            URLparameters += "&from=" + from;
        }
        String APIURL = "https://api.cognitive.microsofttranslator.com/translate";
        String PostText = "[{\"Text\": '" + origText + "'}]";
        URL TargetURL = new URL(APIURL + URLparameters);
        HttpURLConnection TranslatorConnection = (HttpURLConnection) TargetURL.openConnection();

        TranslatorConnection.setConnectTimeout(5000);
        TranslatorConnection.setReadTimeout(5000);
        TranslatorConnection.setRequestProperty("Content-Type", "application/json");
        //TranslatorConnection.setRequestProperty("api-version", "3.0");
        //TranslatorConnection.setRequestProperty("to", "de");

        //TranslatorConnection.setRequestProperty("charset", "UTF-8");

        TranslatorConnection.setRequestProperty("Ocp-Apim-Subscription-Key", key);
        TranslatorConnection.setRequestProperty("Ocp-Apim-Subscription-Region", "westeurope");
        TranslatorConnection.setRequestProperty("Content-Length", PostText.length() + "");
        TranslatorConnection.setDoOutput(true);

        OutputStream OS = TranslatorConnection.getOutputStream();
        OS.write(PostText.getBytes());
        Scanner sc = new Scanner(TranslatorConnection.getInputStream());
        //Удобнее всего с помощью gson вычленять непосредственно переведенный текст
        InputStreamReader ISR;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        if (sc.hasNext()) {
            //Костыль, но я не придумал другого способа
            InputStream is = new ByteArrayInputStream(sc.nextLine().getBytes(StandardCharsets.UTF_8));
            ISR = new InputStreamReader(is);
            TranslationClass res[] = new TranslationClass[1];
            res = gson.fromJson(ISR, TranslationClass[].class);
            TranslatorConnection.disconnect();
            return res[0].translations[0].text;
        } else {
            TranslatorConnection.disconnect();
            return "Auch! Nothing returned by Azure.";
        }
    }
}

