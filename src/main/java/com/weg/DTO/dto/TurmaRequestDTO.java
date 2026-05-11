package com.weg.DTO.dto;

import java.util.List;

public record TurmaRequestDTO(
    String nome,
    long cursoId,
    long professorId,
    List<Integer> listAlunosIds
) {

}
