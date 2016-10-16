package com.ab.store.gymbuddies.gymbuddies;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class CommunitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communities);

        final ArrayList<User> users = new ArrayList<User>();

        users.add(new User(1, "abc", "xyz", 23, "become fit"));
        users.add(new User(1, "abc", "xyz", 23, "become fit"));
        users.add(new User(1, "abc", "xyz", 23, "become fit"));
        users.add(new User(1, "abc", "xyz", 23, "become fit"));


        ArrayAdapter<User> itemsAdapter = new ArrayAdapter<User>(this, R.layout.communities_list_item, users) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                // assign the view we are converting to a local variable
                View v = convertView;

                // first check to see if the view is null. if so, we have to inflate it.
                // to inflate it basically means to render, or show, the view.
                if (v == null) {
                    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = inflater.inflate(R.layout.communities_list_item, null);
                }

                ImageView profilePic = (ImageView) v.findViewById(R.id.list_icon);
                profilePic.setImageResource(R.drawable.logo);

                TextView name = (TextView) v.findViewById(R.id.list_name);
                name.setText(users.get(position).firstName + " " + users.get(position).lastName);

                TextView age = (TextView) v.findViewById(R.id.list_age);

                if (age != null) { age.setText(Integer.toString(users.get(position).age)); }


                TextView goals = (TextView) v.findViewById(R.id.list_goal);
                goals.setText(users.get(position).goals);

                return v;
            }

        };


        ListView listView = (ListView) findViewById(R.id.communities_list);
        listView.setAdapter(itemsAdapter);

    }
}
