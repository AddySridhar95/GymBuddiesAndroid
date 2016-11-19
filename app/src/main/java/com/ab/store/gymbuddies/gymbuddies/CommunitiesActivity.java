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
        ArrayList<Goal> g1 = new ArrayList<Goal>();
        // g1.add(new Goal(Constants.LEAN, Constants.LEAN_DESC));
        g1.add(new Goal(Constants.BODYFAT, Constants.BODYFAT_DESC));

        ArrayList<Goal> g2 = new ArrayList<Goal>();
        //g2.add(new Goal(constants.GAIN, constants.GAIN_DESC));
        //g2.add(new Goal(constants.STRENGTH, constants.STRENGTH_DESC));

        ArrayList<Goal> g3 = new ArrayList<Goal>();
        //g3.add(new Goal(constants.GAIN, constants.GAIN_DESC));
        //g3.add(new Goal(constants.ENDURANCE, constants.ENDURANCE_DESC));

        users.add(new User(1, "David", "Goliath", 12, g1, "PAC", "N2L3G1"));
        users.add(new User(2, "Sam", "Buhr", 23, g2, "CIF", "N2L3G1"));
        users.add(new User(3, "Xiaofei", "Zhang", 52, g3, "Golden Gym", "N2L3W3"));
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
