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
        users.add(new User(1, "abc", "xyz", 12, "sjdfklsadjf"));
        users.add(new User(1, "abc", "xyz", 12, "sjdfklsadjf"));
        users.add(new User(1, "abc", "xyz", 12, "sjdfklsadjf"));

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recList.setLayoutManager(llm);

        UserAdapter userAdapter = new UserAdapter(users);
        recList.setAdapter(userAdapter);
    }
}
