package com.api.blog.mapper;

import org.springframework.stereotype.Component;

import com.api.blog.dto.request.ComentRequestDto;
import com.api.blog.dto.response.ComentResponseDto;
import com.api.blog.model.ComentModel;
import com.api.blog.model.PostModel;

@Component
public class ComentMapper {

    public ComentModel toEntity(ComentRequestDto dto, PostModel entity) {
        if (dto == null) {
            return null;
        }
        return new ComentModel(dto.comentario().toString(), entity);
    }

    public ComentResponseDto toDto(ComentModel entity) {
        if (entity == null) {
            return null;
        }
        return new ComentResponseDto(
                entity.getId(),
                entity.getData(),
                entity.getComentario());
    }
}
