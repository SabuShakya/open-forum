package com.sabu.openforium.controller;


import com.sabu.openforium.constants.ResponseCodeConstants;
import com.sabu.openforium.dto.GenericResponse;
import com.sabu.openforium.dto.PostRequestDTO;
import com.sabu.openforium.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/addPost")
    public ResponseEntity<?> addPost(@RequestBody PostRequestDTO postRequestDTO){
        log.debug("Adding posts started.");
        postService.addPost(postRequestDTO);
        return new ResponseEntity<>(new GenericResponse(true, ResponseCodeConstants.SUCCESS,"Post added successfully"), HttpStatus.CREATED);
    }

}
