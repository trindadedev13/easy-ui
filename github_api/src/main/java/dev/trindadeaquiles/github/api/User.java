package dev.trindadeaquiles.github.api.github;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("bio")
    private String bio;

    // Getters
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getBio() {
        return bio;
    }
}