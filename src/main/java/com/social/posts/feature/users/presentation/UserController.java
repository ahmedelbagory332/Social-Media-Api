package com.social.posts.feature.users.presentation;


import com.social.posts.core.exception.CustomNotFoundException;
import com.social.posts.feature.users.model.UserMapper;
import com.social.posts.feature.users.model.UserModel;
import com.social.posts.feature.users.model.UserService;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("api/social/")
public class UserController {


    private final UserService service;
    private final MessageSource messageSource;

    private final UserMapper userMapper;

    public UserController(UserService service, MessageSource messageSource, UserMapper userMapper) {
        this.service = service;
        this.messageSource = messageSource;
        this.userMapper = userMapper;
    }



    @GetMapping("users")
    public List<UserModel> retrieveAllUsers() {
        Locale locale = LocaleContextHolder.getLocale();
        List<UserModel> users = new ArrayList<>();

        service.findAll().forEach(userDto -> {
            users.add(userMapper.toUserModel(locale.getLanguage(),userDto));
        });

        return users;
    }


    /*
    * EntityModel and WebMvcLinkBuilder to add link for all users
    * in the fact these two class used to link to any resource you want to show to the client
    * without editing in response class itself
    * */
    @GetMapping("users/{id}")
    public EntityModel<UserModel> retrieveUser(@PathVariable int id) {
        try{
            Locale locale = LocaleContextHolder.getLocale();
            UserModel userModel =userMapper.toUserModel(locale.getLanguage(),service.findByIdWithPosts(id)) ;
            EntityModel<UserModel> entityModel = EntityModel.of(userModel);
            /*
             * WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers())
             * used to get the url of this method without write it manually to avoid any mistake when the url change in anyTime
             * */
            WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
            // all-users -> the object which has the link come from previous step
            entityModel.add(link.withRel("all-users"));
            return entityModel;
        }catch (Exception e){
            throw new CustomNotFoundException("user with id: " + id + " not found");
        }

    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteById(id);
    }

    @PostMapping("users")
    public EntityModel<UserModel> createUsers(@Valid @RequestBody UserModel userModel) {
        Locale locale = LocaleContextHolder.getLocale();
        UserModel createdUser = userMapper.toUserModel(locale.getLanguage(),service.createUser(userMapper.toUserDtoWithOutPost(userModel)));
        EntityModel<UserModel> entityModel = EntityModel.of(createdUser);
        WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveUser(createdUser.getId()));
        entityModel.add(linkToUsers.withRel("all-Users"));
        entityModel.add(link.withRel("current-user"));
        return entityModel;

         // to retrieve path to user in header response
//        Locale locale = LocaleContextHolder.getLocale();
//        UserModel createdUser = userMapper.toUserModel(locale.getLanguage(),service.createUser(userMapper.toUserDto(userModel)));
//        URI userPath = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(createdUser.getId())
//                .toUri();
//        return ResponseEntity.created(userPath).build();


    }



    // just for test localization in project files
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale );

    }

}
