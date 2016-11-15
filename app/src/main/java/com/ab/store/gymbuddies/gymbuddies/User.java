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

    User(int i, String f, String l, int a, String g) {
        id = i;
        firstName = f;
        lastName = l;
        age = a;
        goals = g;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }
}

