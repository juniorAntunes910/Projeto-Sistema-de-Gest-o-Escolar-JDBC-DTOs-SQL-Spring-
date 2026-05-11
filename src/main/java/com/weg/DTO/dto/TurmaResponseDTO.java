package com.weg.DTO.dto;

import java.util.List;

public record TurmaResponseDTO(
    long id,
    String nome,
    String nomeCurso,
    String nomeProfesor,
    List<String> listaAlunos
) {

}
