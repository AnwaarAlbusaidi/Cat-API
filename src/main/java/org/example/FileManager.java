package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileManager {
    /**
     * Saves the image at the specified URL to a file in the "data" directory with a unique filename.
     * If an image file with the same name already exists in the "data" directory, the method will
     * not save the image and will print a message to the console instead.
     *
     * @param imageUrl the URL of the image to save
     */
    public void SaveImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // input stream to download the image data
            InputStream inputStream = connection.getInputStream();
            //The image data is stored as an array of bytes using the readAllBytes() method of InputStream class
            byte[] imageData = inputStream.readAllBytes();
            String fileName = FileManager.getUniqueFileName();
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File imageFile = new File(directory, fileName);
            if (imageFile.exists()) {
                System.out.println("Image file already exists: " + fileName);
                return;
            }
            //output stream used to write the image data to a file in the data directory
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            outputStream.write(imageData);
            outputStream.close();
            System.out.println("Image saved to file: " + fileName);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a unique file name based on the current date and a random string.
     *
     * @return a unique file name
     */
    public static String getUniqueFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String randomString = getRandomString(6);
        return timeStamp + "_" + randomString + ".jpg";
    }
    /**
     * Generates a random string of the specified length.
     *
     * @param length the length of the random string to generate
     * @return a random string of the specified length
     */
    private static String getRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            builder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return builder.toString();
    }
}
