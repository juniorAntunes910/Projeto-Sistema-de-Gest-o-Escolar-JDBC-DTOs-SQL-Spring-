package com.weg.DTO.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.weg.DTO.dto.TurmaRequestDTO;
import com.weg.DTO.model.TurmaEntity;

public interface TurmaRepository {

    TurmaEntity createTurma(TurmaRequestDTO turmaRequestDTO) throws SQLException;

    List<TurmaEntity> readAll() throws SQLException;

    Optional<TurmaEntity> readTurmaById(long id) throws SQLException;

    void updateTurma(TurmaRequestDTO turmaRequestDTO, long id) throws SQLException;

    void deleteById(long id) throws SQLException;

}
