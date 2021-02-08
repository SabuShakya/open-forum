package com.sabu.openforium.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {

    private String description;

    private Long postId;
}
