package com.weg.DTO.mapper;

import org.springframework.stereotype.Component;

import com.weg.DTO.dto.ProfessorRequestDTO;
import com.weg.DTO.dto.ProfessorResponseDTO;
import com.weg.DTO.model.ProfessorEntity;

@Component
public class ProfessorMapper {

    public ProfessorEntity toEntity(
        ProfessorRequestDTO professorRequestDTO
    ){
        return new ProfessorEntity(
            professorRequestDTO.nome(),
             professorRequestDTO.email(),
              professorRequestDTO.disciplina());
    }

    public ProfessorResponseDTO toResponse(
        ProfessorEntity professorEntity
    ){
        return new ProfessorResponseDTO(
            professorEntity.getId(),
            professorEntity.getNome(),
            professorEntity.getEmail(),
            professorEntity.getDisciplina()
        );
    }

}
