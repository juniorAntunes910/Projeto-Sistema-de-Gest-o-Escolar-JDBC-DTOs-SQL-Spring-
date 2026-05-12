package com.weg.DTO.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.weg.DTO.dto.AulaRequestDTO;
import com.weg.DTO.dto.AulaResponseDTO;

@Service
public interface AulaService {

    AulaResponseDTO createAula(AulaRequestDTO aulaRequestDTO) throws SQLException;

    List<AulaResponseDTO> readAll() throws SQLException;

    Optional<AulaResponseDTO> readAulaById(long id) throws SQLException;

    void updateAula(AulaRequestDTO aulaRequestDTO, long id) throws SQLException;

    void deleteById(long id) throws SQLException;

}
