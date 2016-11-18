package com.ab.store.gymbuddies.gymbuddies;

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

    User(int i, String f, String l, int a, ArrayList<Goal> g, String gy, String gyp) {
        id = i;
        firstName = f;
        lastName = l;
        age = a;
        goals = g;
        gym = gy;
        gymPinCode = gyp;
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
}

