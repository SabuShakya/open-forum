package com.sabu.openforium.service;

import com.sabu.openforium.dto.PostRequestDTO;
import com.sabu.openforium.entity.Post;
import com.sabu.openforium.entity.Users;
import com.sabu.openforium.exception.UserNotFoundException;
import com.sabu.openforium.repository.PostRepository;
import com.sabu.openforium.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addPost(PostRequestDTO post) {
        Optional<Users> users = Optional.ofNullable(userRepository.findByUsername(post.getUsername()));

        if (users.isPresent()) {
            Post postData = new Post();

            postData.setTitle(post.getTitle());
            postData.setDescription(post.getDescription());
            postData.setUser_id(users.get());

            postRepository.save(postData);
        } else {
            throw new UserNotFoundException("No user available");
        }
    }
}
