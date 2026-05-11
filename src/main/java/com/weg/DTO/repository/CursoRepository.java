package com.weg.DTO.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.weg.DTO.dto.CursoRequestDTO;
import com.weg.DTO.model.CursoEntity;

public interface CursoRepository {

    
    CursoEntity createAluno(CursoRequestDTO aCursoRequestDTO) throws SQLException;
    List<CursoEntity> readAll() throws SQLException;
    Optional<CursoEntity> readAlunoById(long id) throws SQLException;
    void updateAluno( CursoRequestDTO aCursoRequestDTO, long id) throws SQLException;
    void deleteById(long id) throws SQLException;

}
