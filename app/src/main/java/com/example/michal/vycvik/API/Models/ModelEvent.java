package com.example.michal.vycvik.API.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.Streams;

/**
 * Created by michal on 30.05.2017.
 */

public class ModelEvent {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("instructorId")
    @Expose
    private Integer instructorId;
    @SerializedName("localization")
    @Expose
    private String localization;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("eventName")
    @Expose
    private String eventName;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("maxMembersCount")
    @Expose
    private Integer maxMembersCount;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageLink")
    @Expose
    private String link;
    @SerializedName("eventDate")
    @Expose
    private String eventDate;
    @SerializedName("eventTime")
    @Expose
    private String eventTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getMaxMembersCount() {
        return maxMembersCount;
    }

    public void setMaxMembersCount(Integer maxMembersCount) {
        this.maxMembersCount = maxMembersCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
}
