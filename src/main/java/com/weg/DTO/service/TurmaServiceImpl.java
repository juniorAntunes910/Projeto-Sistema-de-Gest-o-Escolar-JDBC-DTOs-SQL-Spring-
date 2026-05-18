package com.weg.DTO.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.DTO.dto.TurmaRequestDTO;
import com.weg.DTO.dto.TurmaResponseDTO;
import com.weg.DTO.mapper.TurmaMapper;
import com.weg.DTO.model.AlunoEntity;
import com.weg.DTO.model.CursoProfessorEntity;
import com.weg.DTO.model.TurmaAlunoEntity;
import com.weg.DTO.model.TurmaEntity;
import com.weg.DTO.repository.AlunoRepository;
import com.weg.DTO.repository.CursoProfessorRepository;
import com.weg.DTO.repository.CursoRepository;
import com.weg.DTO.repository.ProfessorRepository;
import com.weg.DTO.repository.TurmaAlunoRepository;
import com.weg.DTO.repository.TurmaRepository;

@Service
public class TurmaServiceImpl implements TurmaService {
    private TurmaMapper turmaMapper;
    private TurmaRepository turmaRepository;
    private AlunoRepository alunoRepository;
    private ProfessorRepository professorRepository;
    private TurmaAlunoRepository turmaAlunoRepository;
    private CursoProfessorRepository cursoProfessorRepository;
    private CursoRepository cursoRepository;

    public TurmaServiceImpl(TurmaMapper turmaMapper, TurmaRepository turmaRepository, AlunoRepository alunoRepository,
            ProfessorRepository professorRepository, TurmaAlunoRepository turmaAlunoRepository,
            CursoProfessorRepository cursoProfessorRepository, CursoRepository cursoRepository) {
        this.turmaMapper = turmaMapper;
        this.turmaRepository = turmaRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.turmaAlunoRepository = turmaAlunoRepository;
        this.cursoProfessorRepository = cursoProfessorRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public TurmaResponseDTO createTurma(TurmaRequestDTO turmaRequestDTO) throws SQLException {
        TurmaEntity turmaEntity = turmaMapper.toEntity(turmaRequestDTO);
        List<Long> alunos = turmaRequestDTO.listAlunosIds();
        List<String> nomesAlunos = new ArrayList<>();
        turmaEntity = turmaRepository.createTurma(turmaRequestDTO);
        for (Long id : alunos) {
            AlunoEntity alunoEntity = alunoRepository.readAlunoById(id);
            if (alunoEntity == null) {
                throw new RuntimeException("Aluno não existe");
            }
            nomesAlunos.add(alunoEntity.getNome());
            TurmaAlunoEntity turmaAlunoEntity = new TurmaAlunoEntity(turmaEntity.getId(), id);
            turmaAlunoRepository.createTurmaAluno(turmaAlunoEntity);
        }
        String nomeCurso = cursoRepository.readCursoById(turmaRequestDTO.cursoId()).getNome();
        if (nomeCurso == null) {
            throw new RuntimeException("O Curso não foi encontrado");
        }
        String nomeProfessor = professorRepository.readProfessorById(turmaRequestDTO.professorId()).getNome();
        if (nomeProfessor == null) {
            throw new RuntimeException("O Professor não foi encontrado");
        }
        CursoProfessorEntity cursoProfessorEntity = new CursoProfessorEntity(
                professorRepository.readProfessorById(turmaRequestDTO.professorId()).getId(),
                cursoRepository.readCursoById(turmaRequestDTO.cursoId()).getId());

        cursoProfessorRepository.createCursoProfessor(cursoProfessorEntity);
        turmaRepository.createTurma(turmaRequestDTO);
        return turmaMapper.tResponse(turmaEntity, nomeCurso, nomeProfessor, nomesAlunos);

    }

    @Override
    public List<TurmaResponseDTO> readAll() throws SQLException {
        List<TurmaEntity> list = turmaRepository.readAll();
        List<TurmaResponseDTO> turmaResponseDTOs = new ArrayList<>();
        for (TurmaEntity turmaEntity : list) {
            String nomeProfessor = professorRepository.readProfessorById(turmaEntity.getProfessor_id()).getNome();
            String nomeCurso = cursoRepository.readCursoById(turmaEntity.getCurso_id()).getNome();

            List<TurmaAlunoEntity> listaTurmaAluno = turmaAlunoRepository.readAll();
            List<Long> listaIdsAlunos = new ArrayList<>();
            List<String> nomesAlunos = new ArrayList<>();
            for (TurmaAlunoEntity turmaAluno : listaTurmaAluno) {
                if (turmaAluno.getTurma_id() == turmaEntity.getId()) {
                    listaIdsAlunos.add(turmaAluno.getAluno_id());
                }
            }
            for (Long ids : listaIdsAlunos) {
                nomesAlunos.add(alunoRepository.readAlunoById(ids).getNome());
            }
            turmaResponseDTOs.add(
                    turmaMapper.tResponse(turmaEntity, nomeCurso, nomeProfessor, nomesAlunos));
        }
        return turmaResponseDTOs;
    }

    @Override
    public TurmaResponseDTO readTurmaById(long id) throws SQLException {
        TurmaEntity turmaEntity = turmaRepository.readTurmaById(id);
        String nomeCurso = cursoRepository.readCursoById(turmaEntity.getCurso_id()).getNome();
        String nomeProfessor = professorRepository.readProfessorById(turmaEntity.getProfessor_id()).getNome();
        List<TurmaAlunoEntity> listaTurmaAluno = turmaAlunoRepository.readAll();
        List<Long> listaIdsAlunos = new ArrayList<>();
        List<String> nomesAlunos = new ArrayList<>();
        for (TurmaAlunoEntity turmaAluno : listaTurmaAluno) {
            if (turmaAluno.getTurma_id() == turmaEntity.getId()) {
                listaIdsAlunos.add(turmaAluno.getAluno_id());
            }
        }
        for (Long ids : listaIdsAlunos) {
            nomesAlunos.add(alunoRepository.readAlunoById(ids).getNome());
        }
        return turmaMapper.tResponse(turmaEntity, nomeCurso, nomeProfessor, nomesAlunos);

    }



    
    @Override
public void updateTurma(TurmaRequestDTO turmaRequestDTO, long id) throws SQLException {

    TurmaEntity turmaExistente = turmaRepository.readTurmaById(id);

    if (turmaExistente == null) {
        throw new RuntimeException("Turma não existe");
    }

    turmaRepository.updateTurma(turmaRequestDTO, id);

    turmaAlunoRepository.deleteById(id);

    List<Long> alunos = turmaRequestDTO.listAlunosIds();

    for (Long ids : alunos) {

        AlunoEntity alunoEntity = alunoRepository.readAlunoById(ids);

        if (alunoEntity == null) {
            throw new RuntimeException("Aluno não existe");
        }

        TurmaAlunoEntity turmaAlunoEntity =
                new TurmaAlunoEntity(id, ids);

        turmaAlunoRepository.createTurmaAluno(turmaAlunoEntity);
    }
}
    
    

    @Override
    public void deleteById(long id) throws SQLException {
        TurmaEntity turmaEntity = turmaRepository.readTurmaById(id);
        if (turmaEntity == null) {
            throw new RuntimeException();
        }
        turmaAlunoRepository.deleteById(turmaEntity.getId());
        cursoProfessorRepository.deleteById(turmaEntity.getCurso_id());
        turmaRepository.deleteById(id);

    }
}
