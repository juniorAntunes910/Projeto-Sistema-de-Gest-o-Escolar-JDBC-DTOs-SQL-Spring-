package com.weg.DTO.mapper;

import com.weg.DTO.dto.AulaRequestDTO;
import com.weg.DTO.dto.AulaResponseDTO;
import com.weg.DTO.model.AulaEntity;

public class AulaMapper {
    public AulaEntity toAulaEntity(
        AulaRequestDTO aulaRequestDTO
    ){
        return new AulaEntity(aulaRequestDTO.turmaId(), aulaRequestDTO.dataHora(), aulaRequestDTO.assunto());
    }
    public AulaResponseDTO tAulaResponse(
        AulaEntity aulaEntity, String nomeTurma 
    ){
        return new AulaResponseDTO(
            aulaEntity.getId(),
            nomeTurma,
            aulaEntity.getDataHora(),
            aulaEntity.getAssunto()
            
        );
    }
}
