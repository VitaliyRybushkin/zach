package com.example.jsonparser;

public class Todos {
    private String id;
    private String userId;
    private String title;
    private String completed;

    public Todos(String id, String userId, String title, String completed) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "\nTodos{" +
                "\nid=" + id +
                ", \nuserId=" + userId +
                ", \ntitle='" + title + '\'' +
                ", \ncompleted='" + completed + '\'' +
                '}';
    }
}
