package com.weg.DTO.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.DTO.infra.ConnectionFactory;
import com.weg.DTO.model.TurmaAlunoEntity;


@Repository
public class TurmaAlunoRepositoryImpl implements TurmaAlunoRepository {
    private ConnectionFactory connectionFactory;

    public TurmaAlunoRepositoryImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public TurmaAlunoEntity createTurmaAluno(TurmaAlunoEntity turmaAlunoEntity) throws SQLException {
        String command = """
                INSERT INTO turma_aluno
                (
                turma_id,
                aluno_id
                )
                VALUES
                (
                ?,
                ?
                )
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, turmaAlunoEntity.getTurma_id());
            stmt.setLong(2, turmaAlunoEntity.getAluno_id());
            stmt.executeUpdate();
            return turmaAlunoEntity;
        }
    }

    @Override
    public List<TurmaAlunoEntity> readAll() throws SQLException {
        String command = """
                SELECT
                turma_id,
                aluno_id
                FROM
                turma_aluno
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            List<TurmaAlunoEntity> list = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new TurmaAlunoEntity(
                        rs.getLong("turma_id"),
                        rs.getLong("aluno_id")));
            }
            return list;
        }
    }

    @Override
    public TurmaAlunoEntity readTurmaAlunoById(long id) throws SQLException {
        String command = """
                SELECT
                turma_id,
                aluno_id
                FROM
                turma_aluno
                WHERE turma_id = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TurmaAlunoEntity(
                        rs.getLong("turma_id"),
                        rs.getLong("aluno_id"));
            }
        }
        return null;
    }

    @Override
    public void updateTurmaAluno(TurmaAlunoEntity turmaAlunoEntity, long id) throws SQLException {
        String command = """
                UPDATE turma_aluno

                SET turma_id = ?,
                aluno_id = ?
                WHERE turma_id = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, turmaAlunoEntity.getTurma_id());
            stmt.setLong(2, turmaAlunoEntity.getAluno_id());
            stmt.setLong(3, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteById(long id) throws SQLException {
        String command = """
                DELETE FROM turma_aluno
                WHERE turma_id = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
