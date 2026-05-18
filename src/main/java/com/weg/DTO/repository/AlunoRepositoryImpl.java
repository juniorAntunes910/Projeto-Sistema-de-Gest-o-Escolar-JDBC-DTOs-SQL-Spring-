package com.weg.DTO.repository;

import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.DTO.dto.AlunoRequestDTO;
import com.weg.DTO.infra.ConnectionFactory;
import com.weg.DTO.model.AlunoEntity;

@Repository
public class AlunoRepositoryImpl implements AlunoRepository {
    private ConnectionFactory connectionFactory;

    public AlunoRepositoryImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public AlunoEntity createAluno(AlunoRequestDTO alunoRequestDTO) throws SQLException {
        System.out.println("Create Aluno Entrou ---------------------------------------------------");

        String command = """
                INSERT INTO aluno
                (
                nome,
                email,
                matricula,
                data_nascimento
                )
                VALUES
                (
                ?,
                ?,
                ?,
                ?)
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, alunoRequestDTO.nome());
            stmt.setString(2, alunoRequestDTO.email());
            stmt.setString(3, alunoRequestDTO.matricula());
            stmt.setObject(4, alunoRequestDTO.dataNascimento());
            stmt.executeUpdate();
            System.out.println("Criou o ALuno -----------------------------------------");
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                System.out.println("Entrei no rs");
                return new AlunoEntity(
                        rs.getLong("id"),
                        alunoRequestDTO.nome(),
                        alunoRequestDTO.email(),
                        alunoRequestDTO.matricula(),
                        alunoRequestDTO.dataNascimento());
            }
            return null;
        }
    }

    @Override
    public List<AlunoEntity> readAll() throws SQLException {
        String command = """
                SELECT
                
                id,
                nome,
                email,
                matricula,
                data_nascimento
                
                FROM aluno
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            List<AlunoEntity> listAlunoEntities = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listAlunoEntities.add(
                        new AlunoEntity(
                                rs.getLong(1),
                                rs.getString("nome"),
                                rs.getString(("email")),
                                rs.getString("matricula"),
                                rs.getObject("data_nascimento", LocalDate.class)));
            }
            return listAlunoEntities;
        }
    }

    @Override
    public AlunoEntity readAlunoById(long id) throws SQLException {
        String command = """
                SELECT
                
                id,
                nome,
                email,
                matricula,
                data_nascimento
                
                FROM aluno
                WHERE id = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new AlunoEntity(
                        rs.getLong(1),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula"),
                        rs.getObject("data_nascimento", LocalDate.class));
            }
        }
        return null;
    }

    @Override
    public void updateAluno(AlunoRequestDTO alunoRequestDTO, long id) throws SQLException {
        String command = """
                UPDATE aluno
                SET nome = ?,
                    email = ?,
                    matricula = ?,
                    data_nascimento = ?
                    WHERE id = ?
                            """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, alunoRequestDTO.nome());
            stmt.setString(2, alunoRequestDTO.email());
            stmt.setString(3, alunoRequestDTO.matricula());
            stmt.setObject(4, alunoRequestDTO.dataNascimento());
            stmt.setLong(5, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteById(long id) throws SQLException {
        String command = """
                DELETE FROM  aluno
                WHERE id = ?
                            """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public AlunoEntity readByEmail(String email) throws SQLException {
        System.out.println("Read Email Entrou ---------------------------------------------------");
        String command = """
                SELECT
                id, nome, email, matricula, data_nascimento
                FROM aluno
                WHERE email = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new AlunoEntity(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula"),
                        rs.getObject("data_nascimento", LocalDate.class));
            }
        }
        return null;
    }

}
