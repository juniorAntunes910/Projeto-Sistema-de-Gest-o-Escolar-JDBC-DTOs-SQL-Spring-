package com.weg.DTO.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.weg.DTO.dto.ProfessorRequestDTO;
import com.weg.DTO.infra.ConnectionFactory;
import com.weg.DTO.model.ProfessorEntity;

public class ProfessorRepositoryImpl implements ProfessorRepository {
    private ConnectionFactory connectionFactory;

    public ProfessorRepositoryImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public ProfessorEntity createProfessor(ProfessorRequestDTO professorRequestDTO) throws SQLException {
        String command = """
                INSERT INTO professor
                (
                nome,
                email,
                disciplina
                                )
                (
                ?,
                ?,
                ?)
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, professorRequestDTO.nome());
            stmt.setString(2, professorRequestDTO.email());
            stmt.setString(3, professorRequestDTO.disciplina());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return new ProfessorEntity(
                        rs.getLong(1),
                        professorRequestDTO.nome(),
                        professorRequestDTO.email(),
                        professorRequestDTO.disciplina());
            }
            return null;
        }
    }

    @Override
    public List<ProfessorEntity> readAll() throws SQLException {
        String command = """
                SELECT
                (
                id,
                nome,
                email,
                disciplina
                )
                FROM professor
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            List<ProfessorEntity> listProfessorEntities = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listProfessorEntities.add(
                        new ProfessorEntity(
                                rs.getLong(1),
                                rs.getString("nome"),
                                rs.getString(("email")),
                                rs.getString("disciplina")));
            }
            return listProfessorEntities;
        }
    }

    @Override
    public Optional<ProfessorEntity> readAlunoById(long id) throws SQLException {
        String command = """
                 SELECT
                (
                id,
                nome,
                email,
                disciplina
                )
                FROM professor
                WHERE id = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new ProfessorEntity(
                        rs.getLong(1),
                        rs.getString("nome"),
                        rs.getString(("email")),
                        rs.getString("disciplina")));
            }
            ;
        }
        return null;
    }

    @Override
    public void updateAluno(ProfessorRequestDTO professorRequestDTO, long id) throws SQLException {
        String command = """
                UPDATE professor
                SET nome = ?,
                    email = ?,
                    disciplina = ?
                    WHERE id = ?
                            """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, professorRequestDTO.nome());
            stmt.setString(2, professorRequestDTO.email());
            stmt.setString(3, professorRequestDTO.disciplina());
            stmt.setObject(4, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteById(long id) throws SQLException {
        String command = """
                DELETE FROM  professor
                WHERE id = ?
                            """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
