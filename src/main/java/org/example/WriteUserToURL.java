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
    //private UserData userData;
    private Gson user;
   // private URL url;

    public WriteUserToURL(Gson user) {
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
    public void ChangeOrDeleteUser(int id, String method){

        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/users/"+id);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod(method);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            BufferedOutputStream bos = new BufferedOutputStream(urlConnection.getOutputStream());
            bos.write(user.toString().getBytes());



            int result = urlConnection.getResponseCode();;
            System.out.println("Код виконання запиту "+  method + " "+ result);
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
    public  void getUsers(int id){

        String text = null;
        String userForId = "https://jsonplaceholder.typicode.com/users/"+id;
        String allUsers = "https://jsonplaceholder.typicode.com/users";
        try {
            text = Jsoup.connect(id>0?userForId :allUsers )
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(text);
    }

    public void getUsersForUserName(String username){
        String text = null;
        try {
            text = Jsoup.connect("https://jsonplaceholder.typicode.com/users?username="+username)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(text);
    }

}
