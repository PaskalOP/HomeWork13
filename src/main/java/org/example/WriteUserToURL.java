package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.User.UserData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WriteUserToURL {
    private UserData user;
   // private URL url;

    public WriteUserToURL(UserData user) {
        this.user = user;
    }
    public void writeUser() {

        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/users");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            BufferedOutputStream bos = new BufferedOutputStream(urlConnection.getOutputStream());
            bos.write(user.toString().getBytes());

            String result = urlConnection.getResponseMessage();
            System.out.println("Статус запису користувача "+ result);
            try {
                bos.flush(); //очищает поток output-a
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }  catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void ChangeUser(){

        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/users");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("PUT");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            BufferedOutputStream bos = new BufferedOutputStream(urlConnection.getOutputStream());
            bos.write(user.toString().getBytes());

            String result = urlConnection.getResponseMessage();
            System.out.println("Статус запису користувача "+ result);
            try {
                bos.flush(); //очищает поток output-a
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }  catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
