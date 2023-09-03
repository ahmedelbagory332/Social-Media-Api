package com.social.posts.feature.users.model;


import com.social.posts.feature.users.data.UserDto;
import com.social.posts.feature.users.data.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto userDto){
        userRepository.save(userDto);
        return userDto;
    }


    public UserDto findByIdWithPosts(int id) {
        Optional<UserDto> user = userRepository.findByIdWithPosts(id);
        return user.orElse(null);

    }
    public List<UserDto> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(int id) {
          userRepository.deleteById(id);

    }

}
