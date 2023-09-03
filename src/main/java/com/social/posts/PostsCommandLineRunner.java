package com.social.posts;


import com.social.posts.feature.posts.data.PostDto;
import com.social.posts.feature.posts.data.PostRepository;
import com.social.posts.feature.users.data.UserDto;
import com.social.posts.feature.users.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostsCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        List<PostDto> posts = new ArrayList<>();

        UserDto user1 = new UserDto("احمد", "ahmed", LocalDate.now().minusYears(1));
        UserDto user2 = new UserDto("نادر", "nader", LocalDate.now().minusYears(1));

        posts.add(new PostDto("sasasasasوصف1", "1desription",user1));
        posts.add(new PostDto("sasasasasوصف2", "2desription",user2));


        userRepository.save(user1);
        userRepository.save(user2);
        postRepository.saveAll(posts);

    }
}