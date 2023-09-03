package com.social.posts.feature.posts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.social.posts.feature.users.model.UserModel;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostModel {


    private Integer id;
    @Transient
    private String description;

    @Transient
    private int userId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 10, message = "descriptionAr should have at least 10 characters")
    @NotNull(message = "descriptionAr should not be null")
    private String descriptionAr;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 10, message = "descriptionEn should have at least 10 characters")
    @NotNull(message = "descriptionEn should not be null")
    private String descriptionEn;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserModel user;

    public PostModel(Integer id, String description, int userId, String descriptionAr, String descriptionEn, UserModel user) {
        this.id = id;
        this.description = description;
        this.userId = userId;
        this.descriptionAr = descriptionAr;
        this.descriptionEn = descriptionEn;
        this.user = user;
    }

    public PostModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionAr() {
        return descriptionAr;
    }

    public void setDescriptionAr(String descriptionAr) {
        this.descriptionAr = descriptionAr;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public UserModel getUser() {
        return user;
    }


    public void setUser(UserModel user) {
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", descriptionAr='" + descriptionAr + '\'' +
                ", descriptionEn='" + descriptionEn + '\'' +
                ", user=" + user +
                '}';
    }
}

