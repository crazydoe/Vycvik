package com.example.michal.vycvik.Database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by michal on 05.05.2017.
 */
@Table(name = "User")
public class User extends Model{
    @Column(name = "user_id")
    public int user_id;
    @Column(name = "user_name")
    public String user_name;
    @Column(name = "avatar_url")
    public String avatar_url;
    @Column(name = "location_id")
    public int location_id;
    @Column(name = "pref_1_id")
    public int pref_1_id;
    @Column(name = "pref_2_id")
    public int pref_2_id;
    @Column(name = "pref_3_id")
    public int pref_3_id;
    @Column(name = "pref_4_id")
    public int pref_4_id;
    @Column(name = "pref_5_id")
    public int pref_5_id;

}
