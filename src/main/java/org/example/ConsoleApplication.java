package org.example;
/**
 A Console Application to fetch and save a cat image from The Cat API
 */
public class ConsoleApplication {
    /**
     * The main method to run the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Create a new instance of the CatApi class
        CatApi catApi = new CatApi();
        // Call the getCatImage method to fetch and save a cat image from The Cat API
        catApi.getCatImage();
    }
}