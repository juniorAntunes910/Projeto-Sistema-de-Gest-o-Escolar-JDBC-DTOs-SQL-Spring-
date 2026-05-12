package com.weg.DTO.repository;

import java.sql.SQLException;
import java.util.List;

import com.weg.DTO.dto.AulaRequestDTO;
import com.weg.DTO.model.AulaEntity;

public interface AulaRepository {

    AulaEntity createAula(AulaRequestDTO aulaRequestDTO) throws SQLException;

    List<AulaEntity> readAll() throws SQLException;

    AulaEntity readAulaById(long id) throws SQLException;

    void updateAula(AulaRequestDTO aulaRequestDTO, long id) throws SQLException;

    void deleteById(long id) throws SQLException;

}
