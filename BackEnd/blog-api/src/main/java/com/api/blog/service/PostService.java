package com.api.blog.service;

import java.util.List;
import java.util.UUID;

import com.api.blog.dto.request.ComentRequestDto;
import com.api.blog.dto.request.PostRequestDto;
import com.api.blog.dto.response.ComentResponseDto;
import com.api.blog.dto.response.PostResponseDto;

public interface PostService {

    List<PostResponseDto> findAll();
    PostResponseDto findById(UUID id);
    PostResponseDto createPost(PostRequestDto dto);
    ComentResponseDto addComentario(UUID postId, ComentRequestDto dto);
    void deletePost(UUID id);
    PostResponseDto updatePost(UUID id, PostRequestDto dto);
    void deleteComment(UUID id);
    ComentResponseDto updateComment(UUID id, ComentRequestDto dto);
    

    

}
