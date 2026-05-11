package com.weg.DTO.dto;

import java.time.LocalDate;

public record AlunoRequestDTO (
    String nome,
    String email,
    String matricula,
    LocalDate dataNascimento
) {

}
