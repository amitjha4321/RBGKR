package com.example.rbgkr.Models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private String id;
    @SerializedName("username")
    private String username;
    @SerializedName("profile_image")
    private Profilemage profilemage =new Profilemage();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Profilemage getProfilemage() {
        return profilemage;
    }

    public void setProfilemage(Profilemage profilemage) {
        this.profilemage = profilemage;
    }
}
