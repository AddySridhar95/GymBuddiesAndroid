package com.ab.store.gymbuddies.gymbuddies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import GymBuddies.Global.Constants;
import GymBuddies.Helpers.HTTPRequestWrapper;
import GymBuddies.Helpers.VolleyCallback;


public class CommunitiesActivity extends AppCompatActivity {
    String personalEmail;

    public void onLoginSuccess() {

    }

    public void onLoginFailed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communities);
        Intent intent = getIntent();
        personalEmail = intent.getExtras().getString("pEmail");

        final ArrayList<User> users = new ArrayList<User>();
        /**
         * Create a request success callback
         */
        final VolleyCallback successCallback = new VolleyCallback() {
            @Override
            public void onSuccessResponse(String res) {

                try {
                    JSONArray jsonArr = new JSONArray(res);

                    for (int i = 0; i < jsonArr.length(); i++) {
                        JSONObject l = new JSONObject(jsonArr.getJSONObject(i).toString());
                        JSONObject det = new JSONObject(l.get("local").toString());

                        users.add(new User(i, det.get("first_name").toString(), det.get("last_name").toString(), (int) det.get("age"), new ArrayList<Goal>(), "", "", det.get("bio").toString()));
                    }
                } catch (org.json.JSONException ex) {
                    ex.printStackTrace();
                }
            }
        };

        /**
         * Create a request failure callback
         */
        final VolleyCallback failureCallback = new VolleyCallback() {
            @Override
            public void onSuccessResponse(String res) {
                Log.d("sdfuhsjkdfhsdj", res);
                // onLoginFailed();
            }
        };

         // Wrapper to make HTTP request calls
        final HTTPRequestWrapper requestWrapper = new HTTPRequestWrapper("https://gymbuddyandroid.herokuapp.com/",
                this);

        requestWrapper.makeGetRequest("communities/users?email=" + personalEmail, successCallback, failureCallback);

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recList.setLayoutManager(llm);

        UserAdapter userAdapter = new UserAdapter(users, this);
        recList.setAdapter(userAdapter);
    }
}
