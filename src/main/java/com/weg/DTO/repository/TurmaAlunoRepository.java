package com.weg.DTO.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.DTO.model.TurmaAlunoEntity;


@Repository
public interface TurmaAlunoRepository {
    
    
    TurmaAlunoEntity createTurmaAluno(TurmaAlunoEntity turmaAlunoEntity) throws SQLException;

    List<TurmaAlunoEntity> readAll() throws SQLException;

    TurmaAlunoEntity readTurmaAlunoById(long id) throws SQLException;

    void updateTurmaAluno(TurmaAlunoEntity turmaAlunoEntity, long id) throws SQLException;

    void deleteById(long id) throws SQLException;

}
