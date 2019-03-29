package com.example.sabuj.artificialsoft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sabuj.artificialsoft.models.SearchResult;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class UserDetailsActivity extends AppCompatActivity {
    private ImageView userImageDetails;
    private TextView userNameDetails, userDesignationDetails, muserPhoneDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        userImageDetails = findViewById(R.id.ivImageDetails);
        userNameDetails = findViewById(R.id.tvNameDetails);
        userDesignationDetails = findViewById(R.id.tvWhoDetails);
        muserPhoneDetails = findViewById(R.id.tvMobileDetails);
        Gson gson = new Gson();
        String userObject = getIntent().getStringExtra("userData");
        SearchResult userResult = gson.fromJson(userObject, SearchResult.class);
        Picasso.with(this).load(userResult.getImage()).into(userImageDetails);
        userNameDetails.setText(userResult.getName());
        userDesignationDetails.setText(userResult.getWho());
        muserPhoneDetails.setText(userResult.getUser());

    }
}
