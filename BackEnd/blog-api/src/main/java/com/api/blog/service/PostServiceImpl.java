package com.api.blog.service;
//Regras de negocio podem ser colocadas nessa classe de serviço

import java.util.Comparator;

//ela serve como uma camada intermediaria entre o controler e o repository
//diminui o acoplamento

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.api.blog.dto.request.ComentRequestDto;
import com.api.blog.dto.request.PostRequestDto;
import com.api.blog.dto.response.ComentResponseDto;
import com.api.blog.dto.response.PostResponseDto;
import com.api.blog.mapper.ComentMapper;
import com.api.blog.mapper.PostMapper;
import com.api.blog.model.ComentModel;
import com.api.blog.model.PostModel;
import com.api.blog.repository.ComentRepository;
import com.api.blog.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final ComentRepository comentarioRepository;
    private final ComentMapper comentarioMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper,
            ComentRepository comentarioRepository, ComentMapper comentarioMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.comentarioRepository = comentarioRepository;
        this.comentarioMapper = comentarioMapper;
    }

    // Metodo para mostrar todos os posts
    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll() {
        return postRepository.findAll() 
                .stream() //transforma a List em uma stream que é basicamente uma esteira
                .sorted(Comparator.comparing(PostModel :: getData).reversed()) //Sorted usado para ordenar e comparator para comparar as datas do post, utilizando "::" para utilizar um método ja existente
                .map(post -> postMapper.toDto(post)) //map transforma cada elemento da stream no elemento requerido
                .toList(); // pega a streeam e transforma novamente os resultados em uma lista
    }

    // Método para achar o post por ID
    @Override
    @Transactional(readOnly = true)
    public PostResponseDto findById(UUID id) { //procura no banco o post com esse ID e encontra Optional<PostModel> ou Optional.empty()
        PostModel post = postRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Post não encontrado.")); //O elsethrow pega o valor caso exista o postmodel e se não existir lança uma exceção

        return postMapper.toDto(post);
    }

    // Método para criar um post
    @Override
    @Transactional
    public PostResponseDto createPost(PostRequestDto dto) {
        PostModel post = postMapper.toEntity(dto);
        PostModel saved = postRepository.save(post);
        return postMapper.toDto(saved);
    }

    // Método para adicionar um comentário ao post
    @Override
    @Transactional
    public ComentResponseDto addComentario(UUID postId, ComentRequestDto dto) {
        Optional<PostModel> optionalPost = postRepository.findById(postId);
        PostModel post = optionalPost.get();
        ComentModel comentario = new ComentModel(dto.comentario(), post);
        post.adicionarComentario(comentario);
        ComentModel saved = comentarioRepository.save(comentario);
        return comentarioMapper.toDto(saved);
    }

    // Editar um post
    @Override
    @Transactional
    public PostResponseDto updatePost(UUID id, PostRequestDto dto) {
        PostModel post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post não encontrado"));

        post.setAutor(dto.autor());
        post.setTitulo(dto.titulo());
        post.setTexto(dto.texto());

        return postMapper.toDto(postRepository.save(post));
    }

    // Deletar um post
    @Override
    @Transactional
    public void deletePost(UUID id) {
        PostModel post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post não encontrado"));
        postRepository.delete(post);
    }

    // Deletar um comentario
    @Override
    @Transactional
    public void deleteComment(UUID id) {
        ComentModel comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentário não encontrado"));
        comentarioRepository.delete(comentario);
    }

    // Editar um comentario
    @Override
    @Transactional
    public ComentResponseDto updateComment(UUID id, ComentRequestDto dto) {
        ComentModel comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post não encontrado"));
        comentario.setComentario(dto.comentario());
        return comentarioMapper.toDto(comentarioRepository.save(comentario));
    }

}
