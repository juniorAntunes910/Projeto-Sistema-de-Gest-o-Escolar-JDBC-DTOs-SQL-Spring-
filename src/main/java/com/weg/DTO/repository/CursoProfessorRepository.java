package com.weg.DTO.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.DTO.model.CursoProfessorEntity;


@Repository
public interface CursoProfessorRepository {

     
    CursoProfessorEntity createCursoProfessor(CursoProfessorEntity aCursoProfessorEntity) throws SQLException;
    List<CursoProfessorEntity> readAll() throws SQLException;
    CursoProfessorEntity readCursoProfessorById(long id) throws SQLException;
    List<CursoProfessorEntity> readAllByCursoId (long id) throws SQLException;
    void updateCursoProfessor( CursoProfessorEntity aCursoProfessorEntity, long id) throws SQLException;
    void deleteById(long id) throws SQLException;

}
