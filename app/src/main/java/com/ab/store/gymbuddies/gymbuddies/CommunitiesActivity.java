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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import GymBuddies.Helpers.HTTPRequestWrapper;
import GymBuddies.Helpers.VolleyCallback;


public class CommunitiesActivity extends AppCompatActivity {
    String personalEmail;

    public void onLoginSuccess() {

    }

    public void onLoginFailed() {

    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communities);
        Intent intent = getIntent();
        personalEmail = intent.getExtras().getString("pEmail");

        final ArrayList<User> users = new ArrayList<User>();


        /**
         * Create a request failure callback
         */
        final VolleyCallback failureCallback = new VolleyCallback() {
            @Override
            public void onSuccessResponse(String res) {
                // onLoginFailed();
            }
        };

         // Wrapper to make HTTP request calls
        final HTTPRequestWrapper requestWrapper = new HTTPRequestWrapper("https://gymbuddyandroid.herokuapp.com/",
                this);

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recList.setLayoutManager(llm);

        final UserAdapter userAdapter = new UserAdapter(users, this);
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

                        // TODO: need to get goals, address, pincode
                        JSONObject auth = new JSONObject(det.get("auth").toString());
                        users.add(new User(i, det.get("first_name").toString(), det.get("last_name").toString(), (int) det.get("age"), new ArrayList<Goal>(), "", "", det.get("bio").toString(), auth.get("email").toString()));

                    }
                    userAdapter.notifyDataSetChanged();
                } catch (org.json.JSONException ex) {
                    ex.printStackTrace();
                }
            }
        };
        requestWrapper.makeGetRequest("communities/users?email=" + personalEmail, successCallback, failureCallback);
        recList.setAdapter(userAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_comm_profile, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        //respond to menu item selection
        switch (item.getItemId()) {
            case R.id.action_notification:
                Intent notificationIntent = new Intent(this, NotificationActivity.class);
                notificationIntent.putExtra("pEmail", personalEmail);
                startActivity(notificationIntent);
                return true;
            case R.id.action_profile:
                Intent profileIntent = new Intent(this, ProfileActivity.class);
                profileIntent.putExtra("userEmail", personalEmail);
                profileIntent.putExtra("isCurrentUser", true);
                startActivity(profileIntent);
                return true;
            case R.id.action_signout:
                Intent mainActivityIntent = new Intent(this, MainActivity.class);
                startActivity(mainActivityIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
