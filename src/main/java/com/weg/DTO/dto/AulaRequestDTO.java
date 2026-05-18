package com.weg.DTO.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AulaRequestDTO(
    @NotBlank(message = "O id da turma é obrigatorio")
    long turmaId,
    @NotBlank(message = "A data e hora da aula são obrigatorios")
    LocalDateTime dataHora,
    @NotBlank(message = "O assunto é obrigatorio")
    String assunto
) {

}
