package com.weg.DTO.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.weg.DTO.infra.ConnectionFactory;
import com.weg.DTO.model.CursoProfessorEntity;

public class CursoProfessorRepositoryImpl implements CursoProfessorRepository {
    private ConnectionFactory connectionFactory;

    public CursoProfessorRepositoryImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public CursoProfessorEntity createCursoProfessor(CursoProfessorEntity aCursoProfessorEntity) throws SQLException {
        String command = """
                INSERT INTO curso_professor
                (
                professor_id,
                curso_id
                )
                VALUES
                (
                ?,
                ?
                )
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, aCursoProfessorEntity.getProfessorId());
            stmt.setLong(2, aCursoProfessorEntity.getCursoId());
            stmt.executeUpdate();
            return aCursoProfessorEntity;
        }
    }

    @Override
    public List<CursoProfessorEntity> readAll() throws SQLException {
        String command = """
                SELECT
                professor_id,
                curso_id
                FROM curso_professor
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();
            List<CursoProfessorEntity> list = new ArrayList<>();
            while (rs.next()) {
                list.add(
                        new CursoProfessorEntity(
                                rs.getLong("professor_id"),
                                rs.getLong("curso_id")));
            }
            return list;
        }
    }

    @Override
    public CursoProfessorEntity readCursoProfessorById(long id) throws SQLException {
        String command = """
                SELECT
                professor_id,
                curso_id
                FROM curso_professor
                WHERE curso_id = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CursoProfessorEntity(
                        rs.getLong("professor_id"),
                        rs.getLong("curso_id"));
            }
        }
        return null;
    }

    @Override
    public void updateCursoProfessor(CursoProfessorEntity aCursoProfessorEntity, long id) throws SQLException {
        String command = """
                UPDATE curso_professor
                SET professor_id = ?,
                curso_id = ?
                WHERE curso_id = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, aCursoProfessorEntity.getProfessorId());
            stmt.setLong(2, aCursoProfessorEntity.getCursoId());
            stmt.setLong(3, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteById(long id) throws SQLException {
        String command = """
                DELETE FROM curso_professor
                WHERE curso_id = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<CursoProfessorEntity> readAllByCursoId(long id) throws SQLException {
        String command = """
                SELECT 
                curso_id, 
                professor_id
                FROM curso_professor
                WHERE curso_id = ?
                """;
                try(Connection conn = connectionFactory.conexao(); 
            PreparedStatement stmt = conn.prepareStatement(command)){
                stmt.setLong(1, id);
                ResultSet rs = stmt.executeQuery();
                List<CursoProfessorEntity> list = new ArrayList<>();
                while(rs.next()){
                    list.add(new CursoProfessorEntity(rs.getLong("professor_id"), rs.getLong("curso_id")));
                }
                return list;
            }
        
    }
}
