package com.weg.DTO.repository;

import java.sql.SQLException;
import java.util.List;

import com.weg.DTO.dto.CursoRequestDTO;
import com.weg.DTO.model.CursoEntity;

public interface CursoRepository {

    
    CursoEntity createCurso(CursoRequestDTO aCursoRequestDTO) throws SQLException;
    List<CursoEntity> readAll() throws SQLException;
    CursoEntity readCursoById(long id) throws SQLException;
    void updateCurso( CursoRequestDTO aCursoRequestDTO, long id) throws SQLException;
    void deleteById(long id) throws SQLException;

}
