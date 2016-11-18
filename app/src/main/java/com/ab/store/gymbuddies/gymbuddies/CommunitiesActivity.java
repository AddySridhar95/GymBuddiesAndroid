package com.ab.store.gymbuddies.gymbuddies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import android.support.v7.widget.LinearLayoutManager;

public class CommunitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communities);

        ArrayList<User> users = new ArrayList<User>();
        users.add(new User(1, "David", "Goliath", 12, "sjdfklsadjf", "PAC", "N2L3G1"));
        users.add(new User(2, "Sam", "Buhr", 23, "sjdfklsadjf", "CIF", "N2L3G1"));
        users.add(new User(3, "Xiaofei", "Zhang", 52, "sjdfklsadjf", "Golden Gym", "N2L3W3"));

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recList.setLayoutManager(llm);

        UserAdapter userAdapter = new UserAdapter(users);
        recList.setAdapter(userAdapter);
    }
}
