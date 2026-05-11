package com.weg.DTO.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.weg.DTO.dto.TurmaRequestDTO;
import com.weg.DTO.infra.ConnectionFactory;
import com.weg.DTO.model.TurmaEntity;

public class TurmaRepositoryImpl implements TurmaRepository {
    private ConnectionFactory connectionFactory;

    public TurmaRepositoryImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public TurmaEntity createTurma(TurmaRequestDTO turmaRequestDTO) throws SQLException {
        String command = """
                INSERT INTO turma
                (
                nome,
                curso_id,
                professor_id,
                )
                (
                ?,
                ?,
                ?)
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, turmaRequestDTO.nome());
            stmt.setLong(2, turmaRequestDTO.cursoId());
            stmt.setLong(3, turmaRequestDTO.professorId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return new TurmaEntity(
                        rs.getLong(1),
                        turmaRequestDTO.nome(),
                        turmaRequestDTO.cursoId(),
                        turmaRequestDTO.professorId());
            }
            return null;
        }
    }

    @Override
    public List<TurmaEntity> readAll() throws SQLException {
        String command = """
                SELECT

                id,
                nome,
                curso_id,
                professor_id,

                FROM turma
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            List<TurmaEntity> listAlunoEntities = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listAlunoEntities.add(
                        new TurmaEntity(
                                rs.getLong("id"),
                                rs.getString("nome"),
                                rs.getLong("curso_id"),
                                rs.getLong("professor_id")));
            }
            return listAlunoEntities;
        }
    }

    @Override
    public Optional<TurmaEntity> readTurmaById(long id) throws SQLException {
        String command = """
                SELECT
                    id,
                    nome,
                    curso_id,
                    professor_id,
                FROM turma
                WHERE id = ?
                       """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new TurmaEntity(
                        rs.getLong(1),
                        rs.getString("nome"),
                        rs.getLong("curso_id"),
                        rs.getLong("professor_id")));
            }
        }
        return null;
    }

    @Override
    public void updateTurma(TurmaRequestDTO turmaRequestDTO, long id) throws SQLException {
        String command = """
                UPDATE turma
                SET nome = ?,
                    curso_id = ?,
                    professor_id = ?,
                    WHERE id = ?
                            """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, turmaRequestDTO.nome());
            stmt.setLong(2, turmaRequestDTO.cursoId());
            stmt.setLong(3, turmaRequestDTO.professorId());
            stmt.setLong(4, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteById(long id) throws SQLException {
               String command = """
                DELETE FROM  turma
                WHERE id = ?
                            """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

}
