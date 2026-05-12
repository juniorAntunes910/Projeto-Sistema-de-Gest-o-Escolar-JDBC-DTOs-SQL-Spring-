package com.weg.DTO.repository;

import java.sql.SQLException;
import java.util.List;

import com.weg.DTO.dto.AlunoRequestDTO;
import com.weg.DTO.model.AlunoEntity;

public interface AlunoRepository {

    AlunoEntity createAluno(AlunoRequestDTO alunoRequestDTO) throws SQLException;
    List<AlunoEntity> readAll() throws SQLException;
    AlunoEntity readAlunoById(long id) throws SQLException;
    void updateAluno( AlunoRequestDTO alunoRequestDTO, long id) throws SQLException;
    void deleteById(long id) throws SQLException;
}
