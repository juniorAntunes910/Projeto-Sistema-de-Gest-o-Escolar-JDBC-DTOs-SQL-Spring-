package com.weg.DTO.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.weg.DTO.dto.CursoRequestDTO;
import com.weg.DTO.infra.ConnectionFactory;
import com.weg.DTO.model.CursoEntity;

public class CursoRepositoryImpl implements CursoRepository {
    private ConnectionFactory connectionFactory;

    public CursoRepositoryImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public CursoEntity createAluno(CursoRequestDTO aCursoRequestDTO) throws SQLException {
        String command = """
                INSERT INTO curso
                (
                nome,
                codigo,
                )
                (
                ?,
                ?)
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, aCursoRequestDTO.nome());
            stmt.setString(2, aCursoRequestDTO.codigo());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return new CursoEntity(
                        rs.getLong(1),
                        aCursoRequestDTO.nome(),
                        aCursoRequestDTO.codigo());
            }
            return null;
        }
    }

    @Override
    public List<CursoEntity> readAll() throws SQLException {
        String command = """
                SELECT
                (
                   id,
                   nome,
                   codigo,
                )
                FROM curso
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            List<CursoEntity> listAlunoEntities = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listAlunoEntities.add(
                        new CursoEntity(
                                rs.getLong(1),
                                rs.getString("nome"),
                                rs.getString(("codigo"))));
            }
            return listAlunoEntities;
        }
    }

    @Override
    public Optional<CursoEntity> readAlunoById(long id) throws SQLException {
        String command = """
                SELECT
                (
                   id,
                   nome,
                   codigo,
                )
                FROM curso
                WHERE ID = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new CursoEntity(
                        rs.getLong(1),
                        rs.getString("nome"),
                        rs.getString("codigo")));
            }
        }
        return null;
    }

    @Override
    public void updateAluno(CursoRequestDTO aCursoRequestDTO, long id) throws SQLException {
        String command = """
                UPDATE aluno
                SET nome = ?,
                    codigo = ?,
                    WHERE id = ?
                            """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, aCursoRequestDTO.nome());
            stmt.setString(2, aCursoRequestDTO.codigo());
            stmt.setLong(3, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteById(long id) throws SQLException {
        String command = """
                DELETE FROM  curso
                WHERE id = ?
                            """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

}
