import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.*;
import java.io.*;
import com.google.cloud.storage.*;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.StorageOptions;
import com.google.auth.*;
import com.google.api.*;
import com.google.common.collect.Lists;

import java.io.FileInputStream;

public class Main {

    public static void main(String[] args) throws IOException {
        /*GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\keys\\Learn-198c3f5a6065.json"))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();*/

        TranslationServiceSettings translationServiceSettings =
                TranslationServiceSettings.newBuilder()
                        .setCredentialsProvider(FixedCredentialsProvider.create(myCredentials))
                        .build();
        TranslationServiceClient translationServiceClient =
                TranslationServiceClient.create(translationServiceSettings);
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        Translation translation = translate.translate("¡Hola Mundo!");
        System.out.printf("Translated Text:\n\t%s\n", translation.getTranslatedText());
    }
}
