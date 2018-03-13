package com.example.michal.vycvik.Database;

/**
 * Created by michal on 05.05.2017.
 */

public class DB {
    private static final DB ourInstance = new DB();

    public static DB getInstance() {
        return ourInstance;
    }

    private DB() {
    }
}
