package com.api.blog.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ComentRequestDto(@NotBlank(message = "O comentário não pode ser vazio")
String comentario) {}
