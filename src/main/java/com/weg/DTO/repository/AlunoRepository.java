package com.weg.DTO.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.DTO.dto.AlunoRequestDTO;
import com.weg.DTO.model.AlunoEntity;


@Repository
public interface AlunoRepository {

    AlunoEntity createAluno(AlunoRequestDTO alunoRequestDTO) throws SQLException;
    List<AlunoEntity> readAll() throws SQLException;
    AlunoEntity readAlunoById(long id) throws SQLException;
    AlunoEntity readByEmail(String email) throws SQLException;
    void updateAluno( AlunoRequestDTO alunoRequestDTO, long id) throws SQLException;
    void deleteById(long id) throws SQLException;
}
