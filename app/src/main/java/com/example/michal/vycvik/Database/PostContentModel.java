package com.example.michal.vycvik.Database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by michal on 05.05.2017.
 */
@Table(name = "content_post")
public class PostContentModel extends Model {
    @Column(name = "prelegant_id")
    public int prelegant_id;
    @Column(name = "location")
    public String location_name;
    @Column(name = "category_id")
    public int category_id;
    @Column(name = "name")
    public String name;
    @Column(name = "price")
    public int price;
    @Column(name = "max_free_quant")
    public int max_free_quant;
    @Column(name = "occupied_quant")
    public int occupied_quant;
    @Column(name = "image_url")
    public String  image_url;
}
