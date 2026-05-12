package com.weg.DTO.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.DTO.dto.ProfessorRequestDTO;
import com.weg.DTO.dto.ProfessorResponseDTO;


@Service
public interface ProfessorService {

    ProfessorResponseDTO createProfessor(ProfessorRequestDTO professorRequestDTO) throws SQLException;

    List<ProfessorResponseDTO> readAll() throws SQLException;

    ProfessorResponseDTO readProfessorById(long id) throws SQLException;

    void updateProfessor(ProfessorRequestDTO professorRequestDTO, long id) throws SQLException;

    void deleteById(long id) throws SQLException;

}
