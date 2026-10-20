package com.api.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.blog.dto.request.ComentRequestDto;
import com.api.blog.dto.request.PostRequestDto;
import com.api.blog.dto.response.ComentResponseDto;
import com.api.blog.dto.response.PostResponseDto;
import com.api.blog.service.PostService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
/*@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://127.0.0.1:3000"
}) */
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Lista todos os post
    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        return ResponseEntity.ok(postService.findAll());
    }

    // Lista um post por id
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable UUID id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    // Adicionar novo post
    @PostMapping("/newpost")
    public ResponseEntity<PostResponseDto> SalvarPost(@RequestBody @Valid PostRequestDto dto) {
        PostResponseDto created = postService.createPost(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // edita post
    @PatchMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable UUID id,
            @RequestBody @Valid PostRequestDto dto) {
        return ResponseEntity.ok(postService.updatePost(id, dto));
    }

    // deleta post
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    // Adiciona comentario
    @PostMapping("/comentarios/{postId}")
    public ResponseEntity<ComentResponseDto> createComentario(
            @PathVariable UUID postId,
            @RequestBody @Valid ComentRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.addComentario(postId, dto));
    }

    // Deleta comentario
    @DeleteMapping("/comentarios/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID id) {
        postService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    //Editar comentario
    @PatchMapping("/comentarios/{id}")
    public ResponseEntity<ComentResponseDto> updateComment(
            @PathVariable UUID id,
            @RequestBody @Valid ComentRequestDto dto) {
        return ResponseEntity.ok(postService.updateComment(id, dto));
    }
}
