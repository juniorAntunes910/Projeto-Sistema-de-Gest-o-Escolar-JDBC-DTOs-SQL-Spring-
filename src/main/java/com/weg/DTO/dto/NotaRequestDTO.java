package com.weg.DTO.dto;

import jakarta.validation.constraints.NotBlank;

public record NotaRequestDTO(
    @NotBlank(message = "O id do aluno é obrigatorio")
    long alunoId,
    @NotBlank(message = "O id da aula é obrigatorio")
    long aulaId,
    @NotBlank(message = "O valor da aula é obrigatorio")
    double valor
) {

}
