package com.weg.DTO.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AulaResponseDTO(
    long id,
    String nomeTurma,
    LocalDateTime dataHora,
    String assunto
) {

}
