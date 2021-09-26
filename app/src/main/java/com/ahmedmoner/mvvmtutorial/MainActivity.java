package com.ahmedmoner.mvvmtutorial;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    //MainViewModel viewModel;
    private static final String TAG = "MainActivity";
    ArrayList<Post> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    PostAdapter adapter;

    EditText name, title, disc;
    Button btn_add, btn_deletAll, btn_getAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//          viewModel = new ViewModelProvider(this).get(MainViewModel.class);
//          viewModel.
        intit();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new PostAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        PostDatabase postDatabase = PostDatabase.getInstance(this);

        btn_getAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDatabase.postsDao().getAll()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<Post>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onSuccess(@NonNull List<Post> posts) {
                                adapter.setlist((ArrayList<Post>) posts);
                                adapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });
            }
        });





        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postDatabase.postsDao().insert(new Post(1, name.getText().toString(), title.getText().toString(), disc.getText().toString(), "image 1"))

                        .subscribeOn(Schedulers.computation())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                Log.d(TAG, "onSubscribe: " + d.toString());
                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG, "onComplete: ");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.d(TAG, "onError: " + e.getMessage());
                            }
                        });

            }
        });

//
//        btn_deletAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                postDatabase.postsDao().deletAll()
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                 ;
//                Log.d(TAG, "run: delet all " + Thread.currentThread().getName());
//            }
//        });

    }

    private void intit() {
        name = findViewById(R.id.et_name);
        title = findViewById(R.id.et_title);
        disc = findViewById(R.id.et_disc);

        btn_add = findViewById(R.id.btn_add);
        btn_deletAll = findViewById(R.id.btn_deletall);
        btn_getAll = findViewById(R.id.btn_getall);
    }
}