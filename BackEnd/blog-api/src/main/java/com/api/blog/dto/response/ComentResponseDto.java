package com.api.blog.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record ComentResponseDto(
    UUID id,
    LocalDate data,
    String comentario
) {}
