package com.ab.store.gymbuddies.gymbuddies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;

import java.util.ArrayList;
import android.support.v7.widget.LinearLayoutManager;
import GymBuddies.Global.Constants;


public class CommunitiesActivity extends AppCompatActivity {
    String personalEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communities);
        Intent intent = getIntent();
        personalEmail = intent.getExtras().getString("pEmail");


        ArrayList<User> users = new ArrayList<User>();
        ArrayList<Goal> g = new ArrayList<>();
        users.add(new User(1, "David", "Goliath", 12, g, "PAC", "N2L3G1", "Muscle Power!"));
        users.add(new User(2, "Sam", "Buhr", 23, g, "CIF", "N2L3G1", "Hustle the muscle"));
        users.add(new User(3, "Xiaofei", "Zhang", 52, g, "Golden Gym", "N2L3W3", "Get Ripped!"));

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recList.setLayoutManager(llm);

        UserAdapter userAdapter = new UserAdapter(users, this);
        recList.setAdapter(userAdapter);
    }
}
