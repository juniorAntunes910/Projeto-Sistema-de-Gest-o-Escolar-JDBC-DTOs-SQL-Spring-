package com.weg.DTO.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AlunoRequestDTO (
    @NotBlank(message = "O nome é obrigatorio")
    @Size(min = 3, max = 50, message = "O nome deve conter entre 3 a 50 caracteres")
    String nome,
    @NotBlank(message = "O email não pode ser nulo")
    @Email(message = "Email Invalido")
    String email,
    @NotBlank(message = "A matricula é obrigatoria")
    String matricula,
    @NotBlank(message = "A data de nascimento é obirgatoria")
    LocalDate dataNascimento
) {

}
