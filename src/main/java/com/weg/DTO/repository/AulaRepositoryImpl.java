package com.weg.DTO.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.weg.DTO.dto.AulaRequestDTO;
import com.weg.DTO.infra.ConnectionFactory;
import com.weg.DTO.model.AulaEntity;

public class AulaRepositoryImpl implements AulaRepository {
    private ConnectionFactory connectionFactory;

    public AulaRepositoryImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public AulaEntity createAluno(AulaRequestDTO aulaRequestDTO) throws SQLException {
        String command = """
                 INSERT INTO aula(
                 )
                turma_id ,
                data_hora ,
                assunto
                )
                (
                ?,
                ?,
                ?)

                                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, aulaRequestDTO.turmaId());
            stmt.setObject(2, aulaRequestDTO.dataHora());
            stmt.setString(3, aulaRequestDTO.assunto());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return new AulaEntity(
                        rs.getLong(1),
                        aulaRequestDTO.turmaId(),
                        aulaRequestDTO.dataHora(),
                        aulaRequestDTO.assunto());
            }
            return null;
        }
    }

    @Override
    public List<AulaEntity> readAll() throws SQLException {
        String command = """
                SELECT
                (
                id,
                turma_id,
                data_hora,
                assunto
                )
                FROM aula
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            List<AulaEntity> listAlunoEntities = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listAlunoEntities.add(
                        new AulaEntity(
                                rs.getLong("id"),
                                rs.getLong("turma_id"),
                                rs.getObject("data_hora", LocalDate.class),
                                rs.getString("assunto")));
            }
            return listAlunoEntities;
        }
    }

    @Override
    public Optional<AulaEntity> readAlunoById(long id) throws SQLException {
        String command = """
                SELECT
                (
                id,
                turma_id,
                data_hora,
                assunto
                )
                FROM aula
                WHERE id = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new AulaEntity(
                        rs.getLong(1),
                        rs.getLong("turma_id"),
                        rs.getObject("data_hora", LocalDate.class),
                        rs.getString("assunto")));
            }
        }
        return null;
    }

    @Override
    public void updateAluno(AulaRequestDTO aulaRequestDTO, long id) throws SQLException {
        String command = """
                UPDATE aula
                SET turma_id = ?,
                    data_hora = ?,
                    assunto = ?,
                    WHERE id = ?
                            """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, aulaRequestDTO.turmaId());
            stmt.setObject(2, aulaRequestDTO.dataHora());
            stmt.setString(3, aulaRequestDTO.assunto());
            stmt.setLong(4, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteById(long id) throws SQLException {
        String command = """
                DELETE FROM  aula
                WHERE id = ?
                            """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

}
