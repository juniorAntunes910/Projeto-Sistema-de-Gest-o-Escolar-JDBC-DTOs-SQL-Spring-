package com.weg.DTO.mapper;

import org.springframework.stereotype.Component;

import com.weg.DTO.dto.AlunoRequestDTO;
import com.weg.DTO.dto.AlunoResponseDTO;
import com.weg.DTO.model.AlunoEntity;

@Component
public class AlunoMapper {
    public AlunoEntity toEntity(
            AlunoRequestDTO requestDTO) {
        return new AlunoEntity(
                requestDTO.nome(),
                requestDTO.email(),
                requestDTO.matricula(),
                requestDTO.dataNascimento());
    }

    public AlunoResponseDTO toResponse(
        AlunoEntity aluno
    ){
        return new AlunoResponseDTO(
            aluno.getId(),
            aluno.getNome(),
            aluno.getEmail(), 
            aluno.getMatricula(),
            aluno.getDataNascimento()
        );
    }
}


