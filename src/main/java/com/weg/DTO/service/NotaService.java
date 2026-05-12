package com.weg.DTO.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.DTO.dto.NotaRequestDTO;
import com.weg.DTO.dto.NotaResponseDTO;

@Service
public interface NotaService {

    NotaResponseDTO createNota(NotaRequestDTO notaRequestDTO) throws SQLException;

    List<NotaResponseDTO> readAll() throws SQLException;

    NotaResponseDTO readNotaById(long id) throws SQLException;

    void updateNota(NotaRequestDTO notaRequestDTO, long id) throws SQLException;

    void deleteById(long id) throws SQLException;

}
