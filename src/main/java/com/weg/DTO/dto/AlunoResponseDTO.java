package com.weg.DTO.dto;

import java.time.LocalDate;

public record AlunoResponseDTO(
    long id,
    String nome, 
    String email,
    String matricula,
    LocalDate dataNascimento
) {

    public AlunoResponseDTO(long id2, String nome2, String matricula2, LocalDate dataNascimento2) {
        //TODO Auto-generated constructor stub
    }

}
