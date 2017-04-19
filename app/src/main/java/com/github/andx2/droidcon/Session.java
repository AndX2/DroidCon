package com.github.andx2.droidcon;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Andrew on 17.04.2017.
 */

public class Session implements Comparable<Session> {

    public Session(String title, String description, String body, List<String> imgUrls, List<Speaker> speakers, Calendar time, int room) {
        Title = title;
        this.description = description;
        this.body = body;
        this.imgUrls = imgUrls;
        this.speakers = speakers;
        this.time = time;
        this.room = room;
    }

    private String Title;
    private String description;
    private String body;
    private List<String> imgUrls;
    private List<Speaker> speakers;
    private Calendar time;
    private int room;

    @Override
    public int compareTo(@NonNull Session other) {
        return (this.getTitle() + this.getRoom()).compareTo(other.getTitle() + other.getRoom());
    }

    //region get/set
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
    //endregion
}
