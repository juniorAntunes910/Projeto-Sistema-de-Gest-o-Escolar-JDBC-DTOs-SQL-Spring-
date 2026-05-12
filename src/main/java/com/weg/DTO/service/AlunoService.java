package com.weg.DTO.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.DTO.dto.AlunoRequestDTO;
import com.weg.DTO.dto.AlunoResponseDTO;

@Service
public interface AlunoService {

    AlunoResponseDTO createAluno(AlunoRequestDTO alunoRequestDTO) throws SQLException;

    List<AlunoResponseDTO> readAll() throws SQLException;

    AlunoResponseDTO readAlunoById(long id) throws SQLException;

    void updateAluno(AlunoRequestDTO alunoRequestDTO, long id) throws SQLException;

    void deleteById(long id) throws SQLException;

}
