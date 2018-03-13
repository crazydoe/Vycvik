package com.example.michal.vycvik.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michal on 17.06.2017.
 */

public class UserEventModel {
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("eventId")
    @Expose
    private Integer eventId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}
