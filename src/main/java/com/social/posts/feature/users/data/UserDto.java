package com.social.posts.feature.users.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.social.posts.feature.posts.data.PostDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "users")
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Size(min = 2, message = "NameAr should have at least 2 characters")
    @NotNull(message = "NameAr should not be null")
    private String nameAr;

    @Size(min = 2, message = "NameEn should have at least 2 characters")
    @NotNull(message = "NameEn should not be null")
    private String nameEn;

    @Past(message = "Birth Date should be in the past")
    @NotNull(message = "Birth Date not be null")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<PostDto> posts;



    public UserDto(Integer id, String nameAr, String nameEn, LocalDate birthDate, List<PostDto> posts) {
        this.id = id;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.birthDate = birthDate;
        this.posts = posts;
    }

    public UserDto( String nameAr, String nameEn, LocalDate birthDate) {
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.birthDate = birthDate;
    }

    public UserDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }


    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", nameAr='" + nameAr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", birthDate=" + birthDate +
                ", posts=" + posts +
                '}';
    }
}

