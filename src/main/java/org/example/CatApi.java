package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
/**
 A class that interacts with the Cat API to fetch cat images.
 */
public class CatApi {
    /**
     * Fetches a cat image from the Cat API and saves it to a file in the data directory.
     * @throws RuntimeException if an error occurs while fetching or saving the image.
     */
    public void getCatImage()
    {
        FileManager writeToFile = new FileManager();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            // parse the JSON response to get the image URL
            String json = response.body().string();
            JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
            JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
            String imageUrl = jsonObject.get("url").getAsString();
           // System.out.println(imageUrl);
            writeToFile.SaveImage(imageUrl);
    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
