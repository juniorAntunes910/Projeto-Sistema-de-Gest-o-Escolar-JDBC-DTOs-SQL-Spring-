package com.weg.DTO.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import com.weg.DTO.dto.CursoRequestDTO;
import com.weg.DTO.dto.CursoResponseDTO;
import com.weg.DTO.mapper.CursoMapper;
import com.weg.DTO.model.CursoEntity;
import com.weg.DTO.model.CursoProfessorEntity;
import com.weg.DTO.model.ProfessorEntity;
import com.weg.DTO.repository.CursoProfessorRepository;
import com.weg.DTO.repository.CursoRepository;
import com.weg.DTO.repository.ProfessorRepository;

public class CursoServiceImpl implements CursoService {
    private CursoMapper cursoMapper;
    private CursoRepository cursoRepository;
    private ProfessorRepository professorRepository;
    private CursoProfessorRepository cursoProfessorRepository;

    public CursoServiceImpl(CursoMapper cursoMapper, CursoRepository cursoRepository,
            ProfessorRepository professorRepository, CursoProfessorRepository cursoProfessorRepository) {
        this.cursoMapper = cursoMapper;
        this.cursoRepository = cursoRepository;
        this.professorRepository = professorRepository;
        this.cursoProfessorRepository = cursoProfessorRepository;
    }

    @Override
    public CursoResponseDTO createCurso(CursoRequestDTO aCursoRequestDTO) throws SQLException {
        CursoEntity cursoEntity = cursoMapper.tCursoEntity(aCursoRequestDTO);
        cursoEntity = cursoRepository.createCurso(aCursoRequestDTO);
        List<Long> listIdProfessor = aCursoRequestDTO.listaProfessorIds();
        List<String> nomeProfessor = new ArrayList<>();
        for (Long long1 : listIdProfessor) {
            CursoProfessorEntity cursoProfessorEntity = new CursoProfessorEntity(long1, cursoEntity.getId());
            cursoProfessorRepository.createCursoProfessor(cursoProfessorEntity);
            ProfessorEntity professorEntity = professorRepository.readProfessorById(long1);
            nomeProfessor.add(professorEntity.getNome());
        }
        return cursoMapper.tCursoResponseDTO(cursoEntity, nomeProfessor);
    }

    @Override
    public List<CursoResponseDTO> readAll() throws SQLException {
        List<CursoEntity> cursoEntities = cursoRepository.readAll();
        List<CursoResponseDTO> list = new ArrayList<>();
        for (CursoEntity curso : cursoEntities) {
            List<CursoProfessorEntity> cursoProfessorEntities = cursoProfessorRepository
                    .readAllByCursoId(curso.getId());
            List<Long> professoresIds = cursoProfessorEntities.stream().map(item -> item.getProfessorId())
                    .collect(Collectors.toList());
            List<String> listaProfessores = new ArrayList<>();
            if (!professoresIds.isEmpty()) {
                List<ProfessorEntity> professorEntities = professorRepository.readAll();
                listaProfessores = professorEntities.stream().map(item -> item.getNome()).collect(Collectors.toList());
            }
            list.add(cursoMapper.tCursoResponseDTO(curso, listaProfessores));

        }

        return list;

    }

    @Override
    public CursoResponseDTO readCursoById(long id) throws SQLException {
        CursoEntity cursoEntity = cursoRepository.readCursoById(id);
        List<String> nomes = new ArrayList<>();
        List<CursoProfessorEntity> cursoProfessorEntities = cursoProfessorRepository.readAllByCursoId(id);
        List<Long> professoresIds = cursoProfessorEntities.stream().map(item -> item.getProfessorId())
                .collect(Collectors.toList());
        if (!professoresIds.isEmpty()) {
            List<ProfessorEntity> professorEntities = professorRepository.readAll();
            nomes = professorEntities.stream().map(item -> item.getNome()).collect(Collectors.toList());
        }
        return cursoMapper.tCursoResponseDTO(cursoEntity, nomes);
    }

    @Override
    public void updateCurso(CursoRequestDTO aCursoRequestDTO, long id) throws SQLException {
        List<CursoProfessorEntity> cursoProfessorEntity = cursoProfessorRepository.readAllByCursoId(id);
        if (cursoProfessorEntity.isEmpty()) {
            throw new RuntimeException("Curso não existe");
        }
        boolean valido = false;
        for (CursoProfessorEntity cursoProfessorEntity2 : cursoProfessorEntity) {
            if (professorRepository.readProfessorById(cursoProfessorEntity2.getProfessorId()) != null) {
                valido = true;
                break;
            }
        }
        if (!valido) {
            throw new RuntimeException("Professor não existe");
        }
        cursoRepository.createCurso(aCursoRequestDTO);

    }

    @Override
    public void deleteById(long id) throws SQLException {
        List<CursoProfessorEntity> cursoProfessorEntity = cursoProfessorRepository.readAllByCursoId(id);
        if (cursoProfessorEntity.isEmpty()) {
            throw new RuntimeException("Curso não existe");
        }
        boolean valido = false;
        for (CursoProfessorEntity cursoProfessorEntity2 : cursoProfessorEntity) {
            if (professorRepository.readProfessorById(cursoProfessorEntity2.getProfessorId()) != null) {
                valido = true;
                break;
            }
        }
        if (!valido) {
            throw new RuntimeException("Professor não existe");
        }
        cursoRepository.deleteById(id);
    }

}
