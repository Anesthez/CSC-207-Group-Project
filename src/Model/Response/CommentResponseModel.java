package Model.Response;

import Model.Model;

import java.util.ArrayList;

public class CommentResponseModel implements Model {
    private final int id;

    private final int userId;

    private String content;
    private String timestamp;

    public CommentResponseModel(int userId, int id, String content, String timestamp) {
        this.userId = userId;
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
    }

    public ArrayList<Object> get(){
        ArrayList<Object> contents = new ArrayList<>();
        contents.add(userId);
        contents.add(id);
        contents.add(content);
        contents.add(timestamp);
        return contents;
    }
}
