package com.weg.DTO.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.weg.DTO.dto.AulaRequestDTO;
import com.weg.DTO.dto.AulaResponseDTO;
import com.weg.DTO.mapper.AulaMapper;
import com.weg.DTO.model.AulaEntity;
import com.weg.DTO.model.TurmaEntity;
import com.weg.DTO.repository.AulaRepository;
import com.weg.DTO.repository.TurmaRepository;


@Service
public class AulaServiceImpl implements AulaService {
    private AulaMapper aulaMapper;
    private AulaRepository aulaRepository;
    private TurmaRepository turmaRepository;

    public AulaServiceImpl(AulaMapper aulaMapper, AulaRepository aulaRepository, TurmaRepository turmaRepository) {
        this.aulaMapper = aulaMapper;
        this.aulaRepository = aulaRepository;
        this.turmaRepository = turmaRepository;
    }

    @Override
    public AulaResponseDTO createAula(AulaRequestDTO aulaRequestDTO) throws SQLException {
        AulaEntity aulaEntity = aulaMapper.toAulaEntity(aulaRequestDTO);
        aulaRepository.createAula(aulaRequestDTO);
        TurmaEntity turmaEntity = turmaRepository.readTurmaById(aulaEntity.getTurma_id());
        String nome = turmaEntity.getNome();
        return aulaMapper.tAulaResponse(aulaEntity, nome);

    }

    @Override
    public List<AulaResponseDTO> readAll() throws SQLException {
        return aulaRepository.readAll().stream()
                .map(aula -> {
                    try {
                        TurmaEntity turmaEntity = turmaRepository.readTurmaById(aula.getTurma_id());
                        String nome = turmaEntity != null ? turmaEntity.getNome() : null;
                        return aulaMapper.tAulaResponse(aula, nome);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).toList();
    }

    @Override
    public Optional<AulaResponseDTO> readAulaById(long id) throws SQLException {
        AulaEntity aulaEntity = aulaRepository.readAulaById(id);
        if (aulaEntity == null) {
            throw new RuntimeException("A aula não foi encontrada");
        }
        ;
        TurmaEntity turmaEntity = turmaRepository.readTurmaById(aulaEntity.getTurma_id());
        String nome = turmaEntity.getNome();
        return Optional.ofNullable(aulaMapper.tAulaResponse(aulaEntity, nome));

    }

    @Override
    public void updateAula(AulaRequestDTO aulaRequestDTO, long id) throws SQLException {
        AulaEntity aulaEntity = aulaRepository.readAulaById(id);
        if (aulaEntity == null) {
            throw new RuntimeException("A aula não foi encontrada");
        }
        ;
        aulaRepository.updateAula(aulaRequestDTO, id);
    }

    @Override
    public void deleteById(long id) throws SQLException {
        AulaEntity aulaEntity = aulaRepository.readAulaById(id);
        if (aulaEntity == null) {
            throw new RuntimeException("A aula não foi encontrada");
        }
        ;
        aulaRepository.deleteById(id);
    }

}
