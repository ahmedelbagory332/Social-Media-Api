package com.social.posts.feature.posts.model;

import com.social.posts.feature.posts.data.PostDto;
import com.social.posts.feature.users.model.UserMapper;
import com.social.posts.feature.users.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class PostMapper {

    @Autowired
    private UserMapper userMapper;

    public PostModel toPostModel(String lang, PostDto postDto, UserModel userModel) {

        return new PostModel(
                postDto.getId(),
                lang.equals("ar") ? postDto.getDescriptionAr() : postDto.getDescriptionEn(),
                userModel.getId(),
                "",
                "",
                userModel
        );
    }
    public PostModel toPostModel(String lang, PostDto postDto) {
        return new PostModel(
                postDto.getId(),
                lang.equals("ar") ? postDto.getDescriptionAr() : postDto.getDescriptionEn(),
                postDto.getUser().getId(),
                "",
                "",
                null

        );
    }

    public List<PostModel> toListPostModel(String lang, List<PostDto> postsDto) {
        return postsDto.stream()
                .map(post -> toPostModel(lang,post))
                .collect(Collectors.toList());
    }

    public PostDto toPostDto(PostModel postModel, UserModel userModel) {
        return new PostDto(
                postModel.getId(),
                postModel.getDescriptionAr(),
                postModel.getDescriptionEn(),
                userMapper.toUserDto(userModel)

        );
    }


    public PostDto toPostDto(PostModel postModel) {
        return new PostDto(
                postModel.getId(),
                postModel.getDescriptionAr(),
                postModel.getDescriptionEn(),
                null
        );
    }
    public List<PostDto> toListPostDta( List<PostModel> postsModel) {
        return postsModel.stream()
                .map(this::toPostDto)
                .collect(Collectors.toList());
    }
}
