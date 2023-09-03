package com.social.posts.feature.posts.presentation;


import com.social.posts.core.exception.CustomNotFoundException;
import com.social.posts.feature.posts.model.PostMapper;
import com.social.posts.feature.posts.model.PostModel;
import com.social.posts.feature.posts.model.PostService;
import com.social.posts.feature.users.model.UserMapper;
import com.social.posts.feature.users.model.UserModel;
import com.social.posts.feature.users.model.UserService;
import jakarta.validation.Valid;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("api/social/")
public class PostController {


    private final PostService service;
    private final UserService userService;

    private final PostMapper postMapper;
    private final UserMapper userMapper;

    public PostController(PostService service, UserService userService, PostMapper postMapper, UserMapper userMapper) {
        this.service = service;
        this.userService = userService;
        this.postMapper = postMapper;
        this.userMapper = userMapper;
    }



    @GetMapping("posts")
    public List<PostModel> retrieveAllPosts() {
        Locale locale = LocaleContextHolder.getLocale();
        List<PostModel> posts = new ArrayList<>();

        service.findAll().forEach(postDto -> {
            posts.add(postMapper.toPostModel(locale.getLanguage(),postDto));
        });

        return posts;
    }


    /*
    * EntityModel and WebMvcLinkBuilder to add link for all posts
    * in the fact these two class used to link to any resource you want to show to the client
    * without editing in response class itself
    * */
    @GetMapping("posts/{id}")
    public EntityModel<PostModel> retrievePost(@PathVariable int id) {
        Locale locale = LocaleContextHolder.getLocale();
        PostModel PostModel =postMapper.toPostModel(locale.getLanguage(),service.findPost(id)) ;
        if (PostModel == null)
            throw new CustomNotFoundException("post with id: " + id + " not found");
        EntityModel<PostModel> entityModel = EntityModel.of(PostModel);
        /*
        * WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllPosts())
        * used to get the url of this method without write it manually to avoid any mistake when the url change in anyTime
        * */
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllPosts());
        // all-posts -> the object which has the link come from previous step
        entityModel.add(link.withRel("all-posts"));
        return entityModel;
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable int id) {
        service.deleteById(id);
    }

    @PostMapping("user/{id}/post")
    public EntityModel<PostModel> createPosts(@Valid @RequestBody PostModel postModel , @PathVariable int id) {
         try{
             Locale locale = LocaleContextHolder.getLocale();
             UserModel userModel =userMapper.toUserModel(locale.getLanguage(),userService.findByIdWithPosts(id)) ;
             System.out.println("bagory : " + userModel);

             PostModel createdPost = postMapper.toPostModel(locale.getLanguage(),service.createPost(postMapper.toPostDto(postModel,userModel)),userModel);
             EntityModel<PostModel> entityModel = EntityModel.of(createdPost);
             WebMvcLinkBuilder linkToPosts = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllPosts());
             WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrievePost(createdPost.getId()));
             entityModel.add(linkToPosts.withRel("all-posts"));
             entityModel.add(link.withRel("current-post"));
             return entityModel;
         }catch (Exception e){
             throw new CustomNotFoundException("user with id: " + id + " not found");

         }

    }



}
