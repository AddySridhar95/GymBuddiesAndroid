package com.ab.store.gymbuddies.gymbuddies;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sherloose (goose) on 2016-10-15.
 */

public class User {
    int id;
    String firstName;
    String lastName;
    int age;
    ArrayList<Goal> goals;
    String gym;
    String gymPinCode;
    String bio;
    String email;

    User (JSONObject userJson) {
        try {
            JSONObject localMeta = userJson.getJSONObject("local");
            JSONObject userAuthMeta = localMeta.getJSONObject("auth");
            firstName = localMeta.getString("first_name");
            lastName = localMeta.getString("last_name");
            email = userAuthMeta.getString("email");
            bio = localMeta.getString("bio");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    User(int i, String f, String l, int a, ArrayList<Goal> g, String gy, String gyp, String bio, String e) {
        Log.d("Creating", Integer.toString(a));
        id = i;
        firstName = f;
        lastName = l;
        age = a;
        goals = g;
        gym = gy;
        gymPinCode = gyp;
        this.bio = bio;
        email = e;
    }

    int getId() {
        return id;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    int getAge() {
        return age;
    }

    ArrayList<Goal> getGoals() {
        return goals;
    }

    String getBio () { return bio; }

    String getEmail() { return email; }
}

