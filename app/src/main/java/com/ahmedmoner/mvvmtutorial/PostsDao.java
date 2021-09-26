package com.ahmedmoner.mvvmtutorial;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface PostsDao {
    @Insert
    Completable insert(Post post);

    @Update
    public void updata(Post post);

    @Delete
    public void delet(Post post);

    @Query("DELETE FROM table_Post")
    public void deletAll();

    @Query("SELECT * FROM table_Post")
//اختاريلي كا حتجه من db
    Single <List<Post>> getAll();


    @Query("SELECT * FROM table_Post WHERE userName LIKE :search")
    public List<Post> getsearch(String search);

}


