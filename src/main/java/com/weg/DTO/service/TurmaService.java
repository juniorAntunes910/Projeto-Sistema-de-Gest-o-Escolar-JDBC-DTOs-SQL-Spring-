package com.weg.DTO.service;

import java.sql.SQLException;
import java.util.List;

import com.weg.DTO.dto.TurmaRequestDTO;
import com.weg.DTO.dto.TurmaResponseDTO;

public interface TurmaService {

    
    TurmaResponseDTO createTurma(TurmaRequestDTO turmaRequestDTO) throws SQLException;

    List<TurmaResponseDTO> readAll() throws SQLException;

    TurmaResponseDTO readTurmaById(long id) throws SQLException;

    void updateTurma(TurmaRequestDTO turmaRequestDTO, long id) throws SQLException;

    void deleteById(long id) throws SQLException;

}
