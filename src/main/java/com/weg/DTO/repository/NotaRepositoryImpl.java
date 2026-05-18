package com.weg.DTO.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.DTO.dto.NotaRequestDTO;
import com.weg.DTO.infra.ConnectionFactory;
import com.weg.DTO.model.NotaEntity;

@Repository
public class NotaRepositoryImpl implements NotaRepository {
    private ConnectionFactory connectionFactory;

    public NotaRepositoryImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public NotaEntity createNota(NotaRequestDTO notaRequestDTO) throws SQLException {
        String command = """
                INSERT INTO nota
                (
                aluno_id,
                aula_id,
                valor
                )
                VALUES
                (
                ?,
                ?,
                ?
                )
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, notaRequestDTO.alunoId());
            stmt.setLong(2, notaRequestDTO.aulaId());
            stmt.setDouble(3, notaRequestDTO.valor());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return new NotaEntity(
                        rs.getLong(1),
                        notaRequestDTO.alunoId(),
                        notaRequestDTO.aulaId(),
                        notaRequestDTO.valor());
            }
            return null;
        }
    }

    @Override
    public List<NotaEntity> readAll() throws SQLException {
        String command = """
                 SELECT

                 id,
                aluno_id,
                 aula_id,
                 valor

                 FROM nota
                 """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            List<NotaEntity> listAlunoEntities = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listAlunoEntities.add(
                        new NotaEntity(
                                rs.getLong(1),
                                rs.getLong("aluno_id"),
                                rs.getLong("aula_id"),
                                rs.getDouble("valor")));
            }
            return listAlunoEntities;
        }
    }

    @Override
    public NotaEntity readNotaById(long id) throws SQLException {
        String command = """
                 SELECT
                 
                 id,
                aluno_id,
                 aula_id,
                 valor
                 
                 FROM nota
                 WHERE id = ?
                 """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new NotaEntity(
                        rs.getLong("id"),
                        rs.getLong("aluno_id"),
                        rs.getLong("aula_id"),
                        rs.getDouble("valor"));
            }
        }
        return null;
    }

    @Override
    public void updateNota(NotaRequestDTO notaRequestDTO, long id) throws SQLException {
        String command = """
                UPDATE nota
                SET aluno_id = ?,
                    aula_id = ?,
                    valor = ?
                WHERE id = ?
                            """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, notaRequestDTO.alunoId());
            stmt.setLong(2, notaRequestDTO.aulaId());
            stmt.setDouble(3, notaRequestDTO.valor());
            stmt.setLong(4, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteById(long id) throws SQLException {
        String command = """
                DELETE FROM  nota
                WHERE id = ?
                            """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

}
