package com.weg.DTO.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record CursoRequestDTO(
    @NotBlank(message = "O nome é obrigatorio")
    String nome,
    @NotBlank(message = "O codigo é obrigatorio")
    String codigo,
    @NotBlank(message = "A lista de Id é obrigatoria")
    List<Long> listaProfessorIds
) {

}
