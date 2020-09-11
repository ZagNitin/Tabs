package com.example.tabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewUsers;
    private InfoUserAdapter mInfoUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.users);

        mRecyclerViewUsers = findViewById(R.id.recyclerViewUser);
        mRecyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewUsers.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mInfoUserAdapter = new InfoUserAdapter();

        getAllInfoUsers();



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
                        Intent intent2 = new Intent(getApplicationContext(),ImagesActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.users:
                        return true;
                }
                return false;
            }
        });
    }
    public void getAllInfoUsers() {
        NetworkHandler.instance().mJsonPlaceHolderApi.users().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body();
                    mInfoUserAdapter.setData(users);
                    mRecyclerViewUsers.setAdapter(mInfoUserAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(UsersActivity.this, "Went wrong", Toast.LENGTH_SHORT).show();
                Log.i("onFailure", "fail");
            }
        });
    }
}