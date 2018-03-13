package com.example.michal.vycvik.API;

import com.example.michal.vycvik.API.Models.ModelEvent;
import com.example.michal.vycvik.API.Models.ModelUser;
import com.example.michal.vycvik.API.Models.UserEventModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by michal on 30.05.2017.
 */

public interface ApiService {

    @GET("/events")
    Call<List<ModelEvent>> getEvents();
    
    @GET("/events")
    Call<ModelEvent> getEvent(@Query("id") int id);

    @GET("/users/{id}")
    Call<ModelUser> getUser(@Path("id") int id);

    @GET("/userEvent/{id}")
    Call<List<ModelEvent>> getUserEvents(@Path("id") int id);

    @POST("/userEvent")
    Call<UserEventModel> signUpToTheLecture(@Query("userId") int userId, @Query("eventId") int eventId);

    @POST("/userEvent/delete")
    Call<UserEventModel> signOutFromTheLecture(@Query("userId") int userId, @Query("eventId") int eventId);

}
