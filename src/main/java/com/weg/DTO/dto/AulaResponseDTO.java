package com.weg.DTO.dto;

import java.time.LocalDate;

public record AulaResponseDTO(
    long id,
    String nomeTurma,
    LocalDate dataHora,
    String assunto
) {

}
