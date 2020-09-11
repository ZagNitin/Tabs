package com.example.tabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ImageAdapter mUserAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.images);
        mRecyclerView = findViewById(R.id.imageRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mUserAdapter = new ImageAdapter();

        getAllPhotos();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.posts:
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.images:
                        return true;
                    case R.id.users:
                        Intent intent2 = new Intent(getApplicationContext(),UsersActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    public void getAllPhotos() {
        NetworkHandler.instance().mJsonPlaceHolderApi.photos().enqueue(new Callback<List<Photos>>(){
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                if (response.isSuccessful()) {
                    List<Photos> photos = response.body();
                    mUserAdapter.setData(photos);
                    mRecyclerView.setAdapter(mUserAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {
                Toast.makeText(ImagesActivity.this, "Went wrong", Toast.LENGTH_SHORT).show();
                Log.i("onFailure", "fail");
            }
        } );

    }
}