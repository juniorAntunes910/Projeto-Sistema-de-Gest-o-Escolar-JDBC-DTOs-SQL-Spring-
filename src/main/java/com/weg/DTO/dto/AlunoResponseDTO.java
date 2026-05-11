package com.weg.DTO.dto;

import java.time.LocalDate;

public record AlunoResponseDTO(
    long id,
    String nome, 
    String email,
    String matricula,
    LocalDate dataNascimento
) {
    
}
