package com.weg.DTO.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfessorRequestDTO(
    @NotBlank(message = "O nome é obrigatorio")
    String nome,
    @NotBlank(message = "O email é obrigatorio")
    @Email(message = "Email invalido")
    String email,
    @NotBlank(message = "A disciplina é obrigatoria")
    String disciplina
) {

}
