package com.weg.DTO.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AulaRequestDTO(
    long turmaId,
    LocalDateTime dataHora,
    String assunto
) {

}
