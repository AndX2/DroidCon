package com.github.andx2.droidcon;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Andrew on 17.04.2017.
 */

public class Speaker implements Comparable<Speaker> {

    public Speaker(String firstName, String lastName, String avatarUrl, String photoUrl, String info, List<Company> companies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;
        this.photoUrl = photoUrl;
        this.info = info;
        this.companies = companies;
    }

    private String firstName;
    private String lastName;
    private String avatarUrl;
    private String photoUrl;
    private String info;
    private List<Company> companies;

    @Override
    public int compareTo(@NonNull Speaker other) {
        return (this.getFirstName() + this.getLastName()).compareTo(other.getFirstName() + other.getLastName());
    }

    //region get/set
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    //endregion
}
