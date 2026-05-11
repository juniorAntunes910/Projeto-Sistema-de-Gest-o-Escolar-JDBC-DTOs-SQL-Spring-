package com.weg.DTO.dto;

import java.util.List;

public record CursoResponseDTO(
    long id,
    String nome,
    String codigo,
    List<String> listaProfessores
) {

}
