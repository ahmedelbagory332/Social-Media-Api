package com.social.posts.feature.posts.model;


import com.social.posts.feature.posts.data.PostDto;
import com.social.posts.feature.posts.data.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {


    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto createPost(PostDto postDto){
        postRepository.save(postDto);
        return postDto;
    }

    public PostDto findPost(int id) {
        return postRepository.findById(id).orElse(null);
    }
    public List<PostDto> findAll() {
        return postRepository.findAll();
    }

    public void deleteById(int id) {
        postRepository.deleteById(id);

    }

}
