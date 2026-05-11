package com.weg.DTO.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.weg.DTO.dto.CursoRequestDTO;
import com.weg.DTO.dto.CursoResponseDTO;
import com.weg.DTO.model.CursoEntity;

@Component
public class CursoMapper {
    public CursoEntity tCursoEntity(
            CursoRequestDTO cursoRequestDTO) {
        return new CursoEntity(
                cursoRequestDTO.nome(),
                cursoRequestDTO.codigo());
    }

    public CursoResponseDTO tCursoResponseDTO(
        CursoEntity cursoEntity, List<String> listProfessores
    ){
        return new CursoResponseDTO(
            cursoEntity.getId(),
            cursoEntity.getNome(),
            cursoEntity.getCodigo(),
            listProfessores            
        );
    }
}
