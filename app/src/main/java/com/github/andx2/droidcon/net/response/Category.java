package com.github.andx2.droidcon.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Andrew on 05.04.2017.
 */

public class Category {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("members")
    @Expose
    private List<Member> members = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

}