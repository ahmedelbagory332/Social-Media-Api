package com.social.posts.feature.users.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.social.posts.feature.posts.model.PostModel;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class UserModel {


    private Integer id;
    @Transient
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 2, message = "NameAr should have at least 2 characters")
    @NotNull(message = "NameAr should not be null")
    private String nameAr;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 2, message = "NameEn should have at least 2 characters")
    @NotNull(message = "NameEn should not be null")
    private String nameEn;

    @Past(message = "Birth Date should be in the past")
    @NotNull(message = "Birth Date not be null")
    private LocalDate birthDate;


    private List<PostModel> posts;

    public UserModel(Integer id, String name, String nameAr, String nameEn, LocalDate birthDate, List<PostModel> posts) {
        this.id = id;
        this.name = name;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.birthDate = birthDate;
        this.posts = posts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<PostModel> getPosts() {
        return posts;
    }

    public void setPosts(List<PostModel> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameAr='" + nameAr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", birthDate=" + birthDate +
                ", posts=" + posts +
                '}';
    }
}

