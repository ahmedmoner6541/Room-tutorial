package com.ahmedmoner.mvvmtutorial;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Post.class, version = 1)
public abstract class PostDatabase extends RoomDatabase {
    private static PostDatabase instanse;

    public abstract PostsDao postsDao();

    public static synchronized PostDatabase getInstance(Context context) {
        if (instanse == null) {
            instanse = Room.databaseBuilder(context.getApplicationContext()
                    , PostDatabase.class, "posts_database")
                    .fallbackToDestructiveMigration()// TODO: 9/26/2021 لو الفيرجنتاتتغيرر متمسحهاش   _هندلها
                    .build();
        }
        return instanse;
    }
}
