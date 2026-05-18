package com.weg.DTO.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record TurmaRequestDTO(
    @NotBlank(message = "O nome é obrigatorio")
    String nome,
    @NotBlank(message = "O Id do Curso é obrigatorio")
    long cursoId,
    @NotBlank(message = "O id do professor é obrigatorio")
    long professorId,
    @NotBlank(message = "A lista de Alunos é obrigatorio")
    List<Long> listAlunosIds
) {

}
