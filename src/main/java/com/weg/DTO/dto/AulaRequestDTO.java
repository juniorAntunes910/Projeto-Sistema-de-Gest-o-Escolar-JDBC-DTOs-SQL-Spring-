package com.weg.DTO.dto;

import java.time.LocalDate;

public record AulaRequestDTO(
    long turmaId,
    LocalDate dataHora,
    String assunto
) {

}
