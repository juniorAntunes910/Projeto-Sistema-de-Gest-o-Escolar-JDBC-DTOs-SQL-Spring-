package com.weg.DTO.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weg.DTO.dto.TurmaRequestDTO;
import com.weg.DTO.dto.TurmaResponseDTO;
import com.weg.DTO.service.TurmaService;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/turmas")
public class TurmaController {
    private TurmaService turmaService;

    // create
    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping()
    public TurmaResponseDTO post(@RequestBody TurmaRequestDTO turmaRequestDTO) {
        try {
            TurmaResponseDTO turmaResponseDTO = turmaService.createTurma(turmaRequestDTO);
            return turmaResponseDTO;
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // read all

    @GetMapping()
    public List<TurmaResponseDTO> getAll() {
        try {
            List<TurmaResponseDTO> list = turmaService.readAll();
            return list;
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // read by id
    @GetMapping("{id}")
    public TurmaResponseDTO getById(@PathVariable long id) {
        try {
            TurmaResponseDTO turmaResponseDTO = turmaService.readTurmaById(id);
            return turmaResponseDTO;
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // update

    @PutMapping("/{id}")
    public String put(@PathVariable long id, @RequestBody TurmaRequestDTO turmaRequestDTO) {
        try {
            turmaService.updateTurma(turmaRequestDTO, id);
            return "Turma Atualizada com sucesso!";
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // delete

    @DeleteMapping("{id}")
    public String delete(@PathVariable long id){
        try {
            turmaService.deleteById(id);
            return "Turma Deletada com sucesso!";
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
