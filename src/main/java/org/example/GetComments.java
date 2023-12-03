package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class GetComments {
    private int idUser;

    private String baseUrl = " https://jsonplaceholder.typicode.com/";

    public GetComments(int idUser) {
        this.idUser = idUser;
    }
    public String makeUrl(String first,int number,String second){
        return baseUrl + first +"/"+number+"/"+second;
    }
    public String getBody (String url){
        String text = "";
        try {
            text = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text;

    }
    public int getMaxId(String pageData){

        JsonArray postsArray = JsonParser.parseString(pageData).getAsJsonArray();
        int lastPostId = 0;
        
        for (JsonElement postElement : postsArray) {
            JsonObject postObject = postElement.getAsJsonObject();
            int postId = postObject.get("id").getAsInt();

            if (postId > lastPostId) {
                lastPostId = postId;
            }
        }
        return lastPostId;

    }
    public void writeDataToFile(String dataFromComments,int postID) throws FileNotFoundException {
        JsonArray commentsArray = JsonParser.parseString(dataFromComments).getAsJsonArray();
        PrintStream ps = new PrintStream("user-"+idUser+"-post-"+postID+"-comments.json");
        for (JsonElement comment : commentsArray) {
            JsonObject postObject = comment.getAsJsonObject();
            ps.println(comment );

        }
    }

    public void allOpeningTasks(String dataFromTasks){
        JsonArray tasksArray = JsonParser.parseString(dataFromTasks).getAsJsonArray();
        for (JsonElement task : tasksArray) {
            JsonObject postObject = task .getAsJsonObject();
            boolean taskComplate = postObject.get("completed").getAsBoolean();

            if (!taskComplate) System.out.println(task);
        }
    }
}
