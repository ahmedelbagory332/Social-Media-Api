package com.social.posts.feature.posts.data;



import com.social.posts.feature.users.data.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "posts")
public class PostDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Size(min = 10, message = "descriptionAr should have at least 10 characters")
    @NotNull(message = "descriptionAr should not be null")
    private String descriptionAr;

    @Size(min = 10, message = "descriptionEn should have at least 10 characters")
    @NotNull(message = "descriptionEn should not be null")
    private String descriptionEn;

    @ManyToOne()
    @JoinColumn(name = "user_id")
     private UserDto user;

    public PostDto(Integer id, String descriptionAr, String descriptionEn, UserDto user) {
        this.id = id;
        this.descriptionAr = descriptionAr;
        this.descriptionEn = descriptionEn;
        this.user = user;
    }
    public PostDto( String descriptionAr, String descriptionEn, UserDto user) {
        this.descriptionAr = descriptionAr;
        this.descriptionEn = descriptionEn;
        this.user = user;
    }
    public PostDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

     public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", descriptionAr='" + descriptionAr + '\'' +
                ", descriptionEn='" + descriptionEn + '\'' +
                ", user=" + user +
                '}';
    }
}