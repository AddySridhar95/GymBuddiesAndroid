package com.ab.store.gymbuddies.gymbuddies;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import GymBuddies.Global.Constants;
import GymBuddies.Helpers.HTTPRequestWrapper;
import GymBuddies.Helpers.VolleyCallback;

public class NotificationActivity extends AppCompatActivity {

    // User match list
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        final ArrayList<User> matchList = new ArrayList<>();
        final HTTPRequestWrapper requestWrapper = new HTTPRequestWrapper(Constants.BASE_URL, this);
        final VolleyCallback successCallback = new VolleyCallback() {
            @Override
            public void onSuccessResponse(String response) {
                try {
                    JSONArray jsonData = new JSONArray(response);

                    for ( int i = 0; i < jsonData.length(); i++ ) {
                        User user = new User(new JSONObject(jsonData.get(i).toString()));
                        matchList.add(user);
                    }

                } catch ( JSONException e ) {
                    e.printStackTrace();
                }
            }
        };

        final VolleyCallback failureCallback = new VolleyCallback() {
            @Override
            public void onSuccessResponse(String response) {
                Log.e("FAILURE", "Failed to construct matchlist.");
            }
        };


        // Get matched users
        requestWrapper.makeGetRequest(Constants.GET_MATCH_ENDPOINT , successCallback, failureCallback);

        ArrayAdapter<User> itemsAdapter = new ArrayAdapter<User>(this, R.layout.list_view, matchList) {
            @Override
            public View getView (int position, View convertView, ViewGroup parent) {
                // assign the view we are converting to a local variable
                View v = convertView;

                // first check to see if the view is null. if so, we have to inflate it.
                // to inflate it basically means to render, or show, the view.
                if (v == null) {
                    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = inflater.inflate(R.layout.list_view, null);
                }

                TextView name = (TextView) v.findViewById(R.id.match_name);
                name.setText(matchList.get(position).getFirstName() +
                        " " + matchList.get(position).getLastName());

                return v;
            }
        };

        ListView matchListView = (ListView) findViewById(R.id.match_list);
        matchListView.setAdapter(itemsAdapter);

    }
}
