package com.sabu.openforium.service;


import com.sabu.openforium.dto.PostRequestDTO;
import com.sabu.openforium.entity.Post;

public interface PostService {

    public void addPost(PostRequestDTO post);
}
