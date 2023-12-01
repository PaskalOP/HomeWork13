package org.example;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.User.Adress;
import org.example.User.Company;
import org.example.User.Geo;
import org.example.User.UserData;
import org.jsoup.Jsoup;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Hello world!");
        UserData user = new UserData();
         user.setId(10);
         user.setName("Clementina DuBuque");
         user.setUsername("Moriah.Stanton");
         user.setEmail("Rey.Padberg@karina.biz");
         user.setAddress(new Adress("Kattie Turnpike","Suite 198","Lebsackbury","31428-2261",
                 new Geo("-38.2386","57.2232")));
         user.setPhone("024-648-3804");
         user.setWebsite("ambrose.net");
         user.setCompany(new Company("Hoeger LLC","Centralized empowering task-force","target end-to-end models"));
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // System.out.println(gson.toJson(user));

        WriteUserToURL userToUrl = new WriteUserToURL(gson);
        userToUrl.writeUser();
        userToUrl.ChangeOrDeleteUser(9,"PUT");
        userToUrl.ChangeOrDeleteUser(9,"DELETE");
        userToUrl.getUsers(0); //all users
        userToUrl.getUsers(3); //user with id
        userToUrl.getUsersForUserName("Antonette");
    }

}