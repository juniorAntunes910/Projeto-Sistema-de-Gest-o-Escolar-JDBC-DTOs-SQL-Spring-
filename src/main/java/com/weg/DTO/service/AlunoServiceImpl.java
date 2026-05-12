package com.weg.DTO.service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.weg.DTO.dto.AlunoRequestDTO;
import com.weg.DTO.dto.AlunoResponseDTO;
import com.weg.DTO.mapper.AlunoMapper;
import com.weg.DTO.model.AlunoEntity;
import com.weg.DTO.repository.AlunoRepository;

@Service
public class AlunoServiceImpl implements AlunoService {
    private final AlunoMapper alunoMapper;
    private final AlunoRepository alunoRepository;

    public AlunoServiceImpl(AlunoMapper alunoMapper, AlunoRepository alunoRepository) {
        this.alunoMapper = alunoMapper;
        this.alunoRepository = alunoRepository;
    }

    @Override
    public AlunoResponseDTO createAluno(AlunoRequestDTO alunoRequestDTO) throws SQLException {
        AlunoEntity alunoEntity = alunoMapper.toEntity(alunoRequestDTO);
        if(alunoRepository.readByEmail(alunoEntity.getEmail()) !=null){
            throw new RuntimeException("Email já Cadastrado");
        }
        alunoRepository.createAluno(alunoRequestDTO);
        return alunoMapper.toResponse(alunoEntity);
    }

    @Override
    public List<AlunoResponseDTO> readAll() throws SQLException {
        List<AlunoEntity> list = alunoRepository.readAll();
        if (list.isEmpty()) {
            throw new RuntimeException("A lista esta vazia");
        }

        List<AlunoResponseDTO> alunoResponseDTOs = list.stream()
                .map(item -> alunoMapper.toResponse(item))
                .collect(Collectors.toList());
        return alunoResponseDTOs;
    }

    @Override
    public AlunoResponseDTO readAlunoById(long id) throws SQLException {
        AlunoEntity alunoEntity = alunoRepository.readAlunoById(id);
        if (alunoEntity == null) {
            throw new RuntimeException("O Aluno não existe");
        }
        return alunoMapper.toResponse(alunoEntity);

    }

    @Override
    public void updateAluno(AlunoRequestDTO alunoRequestDTO, long id) throws SQLException {
        AlunoEntity alunoEntity = alunoRepository.readAlunoById(id);
        if (alunoEntity == null) {
            throw new RuntimeException("O Aluno não existe");
        }
        alunoRepository.updateAluno(alunoRequestDTO, id);
    }

    @Override
    public void deleteById(long id) throws SQLException {
        AlunoEntity alunoEntity = alunoRepository.readAlunoById(id);
        if (alunoEntity == null) {
            throw new RuntimeException("O Aluno não existe");
        }
        alunoRepository.deleteById(id);
    }

}
