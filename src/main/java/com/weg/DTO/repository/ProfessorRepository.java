package com.weg.DTO.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.weg.DTO.dto.ProfessorRequestDTO;
import com.weg.DTO.model.ProfessorEntity;

public interface ProfessorRepository {

    ProfessorEntity createProfessor(ProfessorRequestDTO professorRequestDTO) throws SQLException;

    List<ProfessorEntity> readAll() throws SQLException;

    Optional<ProfessorEntity> readProfessorById(long id) throws SQLException;

    void updateProfessor(ProfessorRequestDTO professorRequestDTO, long id) throws SQLException;

    void deleteById(long id) throws SQLException;

}
