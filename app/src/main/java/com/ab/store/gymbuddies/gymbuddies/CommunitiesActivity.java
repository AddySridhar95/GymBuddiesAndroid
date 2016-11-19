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

        // TODO: currently hardcoding goals. need to fetch this from backend
        // TODO: currently hardcoding users in the communities page. need to fetch this from backend

        ArrayList<User> users = new ArrayList<User>();
        ArrayList<Goal> g1 = new ArrayList<Goal>();
        g1.add(new Goal("LEAN", "Achieve a lean athletic body"));
        g1.add(new Goal("BODY FAT", "Maintain/Reduce body fat"));

        ArrayList<Goal> g2 = new ArrayList<Goal>();
        g2.add(new Goal("GAIN", "Gain muscle mass"));
        g2.add(new Goal("STRENGTH", "Improve physical strength"));

        ArrayList<Goal> g3 = new ArrayList<Goal>();
        g2.add(new Goal("GAIN", "Gain muscle mass"));
        g2.add(new Goal("ENDURANCE", "Endurance training"));


        users.add(new User(1, "David", "Goliath", 12, g1, "PAC", "N2L3G1"));
        users.add(new User(2, "Sam", "Buhr", 23, g2, "CIF", "N2L3G1"));
        users.add(new User(3, "Xiaofei", "Zhang", 52, g3, "Golden Gym", "N2L3W3"));

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recList.setLayoutManager(llm);

        UserAdapter userAdapter = new UserAdapter(users);
        recList.setAdapter(userAdapter);
    }
}
