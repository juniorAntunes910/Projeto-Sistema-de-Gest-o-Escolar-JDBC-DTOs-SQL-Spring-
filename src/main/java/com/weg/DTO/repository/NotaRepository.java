package com.weg.DTO.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.weg.DTO.dto.NotaRequestDTO;
import com.weg.DTO.model.NotaEntity;

public interface NotaRepository {

    NotaEntity createAluno(NotaRequestDTO notaRequestDTO) throws SQLException;

    List<NotaEntity> readAll() throws SQLException;

    Optional<NotaEntity> readAlunoById(long id) throws SQLException;

    void updateAluno(NotaRequestDTO notaRequestDTO, long id) throws SQLException;

    void deleteById(long id) throws SQLException;

}
