package com.ab.store.gymbuddies.gymbuddies;

/**
 * Created by sherloose (goose) on 2016-10-15.
 */

public class User {
    int id;
    String firstName;
    String lastName;
    int age;
    String goals;
    String gym;
    String gymPinCode;

    User(int i, String f, String l, int a, String g, String gy, String gyp) {
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

    String getGoals() {
        return goals;
    }
}

