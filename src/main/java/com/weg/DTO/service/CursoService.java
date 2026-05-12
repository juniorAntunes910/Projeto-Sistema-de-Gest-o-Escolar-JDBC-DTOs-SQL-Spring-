package com.weg.DTO.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.DTO.dto.CursoRequestDTO;
import com.weg.DTO.dto.CursoResponseDTO;


@Service
public interface CursoService {

    CursoResponseDTO createCurso(CursoRequestDTO aCursoRequestDTO) throws SQLException;

    List<CursoResponseDTO> readAll() throws SQLException;

    CursoResponseDTO readCursoById(long id) throws SQLException;

    void updateCurso(CursoRequestDTO aCursoRequestDTO, long id) throws SQLException;

    void deleteById(long id) throws SQLException;

}
