package com.social.posts.feature.users.model;

import com.social.posts.feature.posts.model.PostMapper;
import com.social.posts.feature.users.data.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class UserMapper {


    @Autowired
    private   PostMapper postMapper;



    public UserModel toUserModel(String lang, UserDto userDto) {
        return new UserModel(
                userDto.getId(),
                lang.equals("ar") ? userDto.getNameAr() : userDto.getNameEn(),
                "",
                "",
                userDto.getBirthDate(),
                postMapper.toListPostModel(lang,userDto.getPosts())

        );
    }


    public UserDto toUserDto(UserModel userModel) {
        return new UserDto(
                userModel.getId(),
                userModel.getNameEn(),
                userModel.getNameAr(),
                userModel.getBirthDate(),
                postMapper.toListPostDta(userModel.getPosts())

        );
    }

    public UserDto toUserDtoWithOutPost(UserModel userModel) {
        return new UserDto(
                userModel.getId(),
                userModel.getNameEn(),
                userModel.getNameAr(),
                userModel.getBirthDate(),
                new ArrayList<>()

        );
    }


}
