package com.weg.DTO.mapper;

import com.weg.DTO.dto.NotaRequestDTO;
import com.weg.DTO.dto.NotaResponseDTO;
import com.weg.DTO.model.NotaEntity;

public class NotaMapper {

    public NotaEntity tEntity(
        NotaRequestDTO notaRequestDTO
    ){
        return new NotaEntity(
            notaRequestDTO.alunoId(),
             notaRequestDTO.aulaId(),
              notaRequestDTO.valor());
    }
    public NotaResponseDTO tNotaResponse(
        NotaEntity notaEntity, String alunoNome, String aulaAssunto
    ){
        return new NotaResponseDTO(
            notaEntity.getId(),
            alunoNome,
            aulaAssunto,
            notaEntity.getValor()
        );
    }
}
