package org.example;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.User.Adress;
import org.example.User.Company;
import org.example.User.Geo;
import org.example.User.UserData;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

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

        //Task1
        DataFromURL userToUrl = new DataFromURL(gson);
        //Записати новго юзера на сервер
        userToUrl.writeUser();
        // Змінити дані юзера
        userToUrl.ChangeOrDeleteUser(9,"PUT");
        // Видалили юзера
        userToUrl.ChangeOrDeleteUser(9,"DELETE");
        // Всі користувачі
        userToUrl.getUsers(0); //all users
        //Користувач за ID
        userToUrl.getUsers(3); //user with id
        // Користувач за UserName
        userToUrl.getUsersForUserName("Antonette");

        //Task 2
        GetComments dataFromUrl = new GetComments(2);
        String urlForPosts = dataFromUrl.makeUrl("users",2,"posts") ;
        String posts = dataFromUrl.getBody(urlForPosts);
        int maxid = dataFromUrl.getMaxId(posts);
        String urlForComments = dataFromUrl.makeUrl("posts",maxid,"comments");
        String commentsBody= dataFromUrl.getBody(urlForComments);
        dataFromUrl.writeDataToFile(commentsBody,maxid);

        //Task 3
        String urlFromTasks = dataFromUrl.makeUrl("users",4,"todos");
        String todosBody = dataFromUrl.getBody(urlFromTasks);
        dataFromUrl.allOpeningTasks(todosBody);

    }

}