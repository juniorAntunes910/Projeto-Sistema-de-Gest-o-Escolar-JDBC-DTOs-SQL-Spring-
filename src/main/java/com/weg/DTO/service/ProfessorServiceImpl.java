package com.weg.DTO.service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.weg.DTO.dto.ProfessorRequestDTO;
import com.weg.DTO.dto.ProfessorResponseDTO;
import com.weg.DTO.mapper.ProfessorMapper;
import com.weg.DTO.model.ProfessorEntity;
import com.weg.DTO.repository.ProfessorRepository;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    private ProfessorMapper professorMapper;
    private ProfessorRepository professorRepository;


    

    public ProfessorServiceImpl(ProfessorMapper professorMapper, ProfessorRepository professorRepository) {
        this.professorMapper = professorMapper;
        this.professorRepository = professorRepository;
    }

    @Override
    public ProfessorResponseDTO createProfessor(ProfessorRequestDTO professorRequestDTO) throws SQLException {
        ProfessorEntity ProfessorEntity = professorMapper.toEntity(professorRequestDTO);
        ProfessorEntity professorEntity2 = professorRepository.createProfessor(professorRequestDTO);
        return professorMapper.toResponse(professorEntity2);
    }

    @Override 
    public List<ProfessorResponseDTO> readAll() throws SQLException {
        List<ProfessorEntity> list = professorRepository.readAll();
        if (list.isEmpty()) {
            throw new RuntimeException("A lista esta vazia");
        }

        List<ProfessorResponseDTO> professorResponseDTOs = list.stream()
                .map(item -> professorMapper.toResponse(item))
                .collect(Collectors.toList());
        return professorResponseDTOs;
    }

    @Override
    public ProfessorResponseDTO readProfessorById(long id) throws SQLException {

        ProfessorEntity professorEntity = professorRepository.readProfessorById(id);
        if (professorEntity == null) {
            throw new RuntimeException("O professor não foi encontrado");
        }
        return professorMapper.toResponse(professorEntity);
    }

    @Override
    public void updateProfessor(ProfessorRequestDTO professorRequestDTO, long id) throws SQLException {
        ProfessorEntity professorEntity = professorRepository.readProfessorById(id);
        if (professorEntity == null) {
            throw new RuntimeException("O professor não foi encontrado");
        }
        professorRepository.updateProfessor(professorRequestDTO, id);

    }

    @Override
    public void deleteById(long id) throws SQLException {
        ProfessorEntity professorEntity = professorRepository.readProfessorById(id);
        if (professorEntity == null) {
            throw new RuntimeException("O professor não foi encontrado");
        }
        professorRepository.deleteById(id);
    }

}
