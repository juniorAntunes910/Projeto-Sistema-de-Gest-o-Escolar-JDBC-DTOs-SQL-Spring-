package com.weg.DTO.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.weg.DTO.dto.TurmaRequestDTO;
import com.weg.DTO.dto.TurmaResponseDTO;
import com.weg.DTO.model.TurmaEntity;

@Component
public class TurmaMapper {

    public TurmaEntity toEntity(
            TurmaRequestDTO turmaRequestDTO) {
        return new TurmaEntity(
                turmaRequestDTO.nome(),
                turmaRequestDTO.cursoId(),
                turmaRequestDTO.professorId());
    }

    public TurmaResponseDTO tResponse(
        TurmaEntity turmaEntity, String nomeCurso, String nomeProfessor,
        List<String> listaProfessor
    ){
        return new TurmaResponseDTO(
            turmaEntity.getId(),
            turmaEntity.getNome(),
            nomeCurso,
            nomeProfessor,
            listaProfessor
        );
    }
}
