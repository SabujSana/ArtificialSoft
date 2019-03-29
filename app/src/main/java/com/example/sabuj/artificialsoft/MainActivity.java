package com.example.sabuj.artificialsoft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sabuj.artificialsoft.adapters.OnItemClickListener;
import com.example.sabuj.artificialsoft.adapters.UserDataAdapter;
import com.example.sabuj.artificialsoft.api.RetrofitClient;
import com.example.sabuj.artificialsoft.api.UserApi;
import com.example.sabuj.artificialsoft.models.SearchResult;
import com.example.sabuj.artificialsoft.models.UserData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvView;
    private List<SearchResult> searchUserDataResult;
    private UserDataAdapter userDataAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvView = findViewById(R.id.text);
        recyclerView = findViewById(R.id.rvUserResult);
        loadUserData();
    }

    private void loadUserData() {
        UserApi userApi = RetrofitClient.getRetrofitClient().create(UserApi.class);
        Call<UserData> call = userApi.getUserData();
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, final Response<UserData> response) {
                searchUserDataResult = new ArrayList<>();
                searchUserDataResult = response.body().getSearchResult();
                userDataAdapter = new UserDataAdapter(getApplicationContext(), searchUserDataResult);
                layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                if (searchUserDataResult != null) {
                    recyclerView.setAdapter(userDataAdapter);
                    userDataAdapter.notifyDataSetChanged();
                }

                userDataAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        SearchResult result = searchUserDataResult.get(position);
                        Gson gson = new Gson();
                        Intent intent = new Intent(MainActivity.this, UserDetailsActivity.class);
                        intent.putExtra("userData", gson.toJson(result));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

            }
        });
    }
}
