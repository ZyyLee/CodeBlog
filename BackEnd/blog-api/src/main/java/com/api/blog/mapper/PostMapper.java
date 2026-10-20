package com.api.blog.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.api.blog.dto.request.PostRequestDto;
import com.api.blog.dto.response.ComentResponseDto;
import com.api.blog.dto.response.PostResponseDto;
import com.api.blog.model.ComentModel;
import com.api.blog.model.PostModel;

@Component
public class PostMapper {

    private final ComentMapper comentMapper;

    public PostMapper(ComentMapper comentMapper) {
        this.comentMapper = comentMapper;
    }
    
    public PostModel toEntity(PostRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return new PostModel(
                dto.autor(),
                dto.titulo(),
                dto.texto());
    }

    public PostResponseDto toDto(PostModel entity) {
        if (entity == null) {
            return null;
        }
         List<ComentResponseDto> comentariosDto = new ArrayList<>();
        if (entity.getComentarios() != null) {
            for (ComentModel comentario : entity.getComentarios()) {
                ComentResponseDto dto = comentMapper.toDto(comentario);
                comentariosDto.add(dto);
            }
        }
        return new PostResponseDto(
                entity.getId(),
                entity.getAutor(),
                entity.getData(),
                entity.getTitulo(),
                entity.getTexto(),
                comentariosDto
        );
    }



}
