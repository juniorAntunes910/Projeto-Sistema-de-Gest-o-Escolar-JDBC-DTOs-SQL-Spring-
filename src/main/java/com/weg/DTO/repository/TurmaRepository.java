package com.weg.DTO.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.DTO.dto.TurmaRequestDTO;
import com.weg.DTO.model.TurmaEntity;


@Repository
public interface TurmaRepository {

    TurmaEntity createTurma(TurmaRequestDTO turmaRequestDTO) throws SQLException;

    List<TurmaEntity> readAll() throws SQLException;

    TurmaEntity readTurmaById(long id) throws SQLException;

    void updateTurma(TurmaRequestDTO turmaRequestDTO, long id) throws SQLException;

    void deleteById(long id) throws SQLException;

}
