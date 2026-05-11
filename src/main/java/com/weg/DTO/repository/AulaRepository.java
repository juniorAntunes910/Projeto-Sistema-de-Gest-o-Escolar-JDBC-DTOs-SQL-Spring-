package com.weg.DTO.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.weg.DTO.dto.AulaRequestDTO;
import com.weg.DTO.model.AulaEntity;

public interface AulaRepository {

    AulaEntity createAluno(AulaRequestDTO aulaRequestDTO) throws SQLException;

    List<AulaEntity> readAll() throws SQLException;

    Optional<AulaEntity> readAlunoById(long id) throws SQLException;

    void updateAluno(AulaRequestDTO aulaRequestDTO, long id) throws SQLException;

    void deleteById(long id) throws SQLException;

}
