package com.weg.DTO.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.DTO.dto.NotaRequestDTO;
import com.weg.DTO.dto.NotaResponseDTO;
import com.weg.DTO.mapper.NotaMapper;
import com.weg.DTO.model.AlunoEntity;
import com.weg.DTO.model.AulaEntity;
import com.weg.DTO.model.NotaEntity;
import com.weg.DTO.repository.AlunoRepository;
import com.weg.DTO.repository.AulaRepository;
import com.weg.DTO.repository.NotaRepository;


@Service
public class NotaServiceImpl implements NotaService {
    private NotaMapper notaMapper;
    private NotaRepository notaRepository;
    private AlunoRepository alunoRepository;
    private AulaRepository aulaRepository;


    

    public NotaServiceImpl(NotaMapper notaMapper, NotaRepository notaRepository, AlunoRepository alunoRepository,
            AulaRepository aulaRepository) {
        this.notaMapper = notaMapper;
        this.notaRepository = notaRepository;
        this.alunoRepository = alunoRepository;
        this.aulaRepository = aulaRepository;
    }

    @Override
    public NotaResponseDTO createNota(NotaRequestDTO notaRequestDTO) throws SQLException {
        NotaEntity notaEntity = notaMapper.tEntity(notaRequestDTO);
        AulaEntity aulaEntity = aulaRepository.readAulaById(notaRequestDTO.aulaId());
        AlunoEntity alunoEntity = alunoRepository.readAlunoById(notaRequestDTO.alunoId());
        if (aulaEntity == null) {
            throw new RuntimeException("A Aula não existe");
        }
        if (alunoEntity == null) {
            throw new RuntimeException("O Aluno não existe");
        }
        notaRepository.createNota(notaRequestDTO);
        return notaMapper.tNotaResponse(notaEntity, alunoEntity.getNome(), aulaEntity.getAssunto());

    }

    @Override
    public List<NotaResponseDTO> readAll() throws SQLException {
        List<NotaEntity> list = notaRepository.readAll();
        List<NotaResponseDTO> responseDTOs = new ArrayList<>();
        for (NotaEntity nota : list) {
            AulaEntity aulaEntity = aulaRepository.readAulaById(nota.getAula_id());
            AlunoEntity alunoEntity = alunoRepository.readAlunoById(nota.getAluno_id());
            if (aulaEntity == null) {
                throw new RuntimeException("A Aula não existe");
            }
            if (alunoEntity == null) {
                throw new RuntimeException("O Aluno não existe");
            }
            responseDTOs.add(notaMapper.tNotaResponse(nota, alunoEntity.getNome(), aulaEntity.getAssunto()));
        }
        return responseDTOs;
    }

    @Override
    public NotaResponseDTO readNotaById(long id) throws SQLException {
        NotaEntity notaEntity = notaRepository.readNotaById(id);
        if (notaEntity == null) {
            throw new RuntimeException("A nota não existe");
        }

        AulaEntity aulaEntity = aulaRepository.readAulaById(notaEntity.getAula_id());
        AlunoEntity alunoEntity = alunoRepository.readAlunoById(notaEntity.getAluno_id());
        if (aulaEntity == null) {
            throw new RuntimeException("A Aula não existe");
        }
        if (alunoEntity == null) {
            throw new RuntimeException("O Aluno não existe");
        }
        return notaMapper.tNotaResponse(notaEntity, alunoEntity.getNome(), aulaEntity.getAssunto());

    }

    @Override
    public void updateNota(NotaRequestDTO notaRequestDTO, long id) throws SQLException {
        NotaEntity notaEntity = notaRepository.readNotaById(id);
        if (notaEntity == null) {
            throw new RuntimeException("A nota não existe");
        }

        AulaEntity aulaEntity = aulaRepository.readAulaById(notaEntity.getAula_id());
        AlunoEntity alunoEntity = alunoRepository.readAlunoById(notaEntity.getAluno_id());
        if (aulaEntity == null) {
            throw new RuntimeException("A Aula não existe");
        }
        if (alunoEntity == null) {
            throw new RuntimeException("O Aluno não existe");
        }
        notaRepository.updateNota(notaRequestDTO, id);
    }

    @Override
    public void deleteById(long id) throws SQLException {
        NotaEntity notaEntity = notaRepository.readNotaById(id);
        if (notaEntity == null) {
            throw new RuntimeException("A nota não existe");
        }

        AulaEntity aulaEntity = aulaRepository.readAulaById(notaEntity.getAula_id());
        AlunoEntity alunoEntity = alunoRepository.readAlunoById(notaEntity.getAluno_id());
        if (aulaEntity == null) {
            throw new RuntimeException("A Aula não existe");
        }
        if (alunoEntity == null) {
            throw new RuntimeException("O Aluno não existe");
        }
        notaRepository.deleteById(id);
    }

}
