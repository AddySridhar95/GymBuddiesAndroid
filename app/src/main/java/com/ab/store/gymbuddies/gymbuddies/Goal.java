package com.ab.store.gymbuddies.gymbuddies;

/**
 * Created by adityasridhar on 2016-11-17.
 */

public class Goal {
    private String name;
    private String desc;
    private int id;

    Goal(String n, String d) {
        name = n;
        desc = d;
//        id = i;
    }

    String getName() {
        return name;
    }

    String getDesc() {
        return desc;
    }

//    int getId() {
//        return id;
//    }
}
