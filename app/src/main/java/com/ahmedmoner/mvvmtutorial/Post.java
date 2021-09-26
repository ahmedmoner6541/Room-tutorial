package com.ahmedmoner.mvvmtutorial;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_Post")
public class Post {
    @PrimaryKey(autoGenerate = true)
    private int id ;
    private int userId;
    private String userName ;
    private  String title ;
    private String disc ;
    private String image;

    public Post(int userId, String userName, String title, String disc, String image) {
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.disc = disc;
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getTitle() {
        return title;
    }

    public String getDisc() {
        return disc;
    }

    public String getImage() {
        return image;
    }
}
