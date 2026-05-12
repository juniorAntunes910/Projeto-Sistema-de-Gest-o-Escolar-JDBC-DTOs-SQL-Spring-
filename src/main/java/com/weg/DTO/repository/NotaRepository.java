package com.weg.DTO.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.DTO.dto.NotaRequestDTO;
import com.weg.DTO.model.NotaEntity;


@Repository
public interface NotaRepository {

    NotaEntity createNota(NotaRequestDTO notaRequestDTO) throws SQLException;

    List<NotaEntity> readAll() throws SQLException;

    NotaEntity readNotaById(long id) throws SQLException;

    void updateNota(NotaRequestDTO notaRequestDTO, long id) throws SQLException;

    void deleteById(long id) throws SQLException;

}
