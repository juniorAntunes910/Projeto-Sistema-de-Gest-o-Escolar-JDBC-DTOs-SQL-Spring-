package com.weg.DTO.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weg.DTO.dto.AlunoRequestDTO;
import com.weg.DTO.dto.AlunoResponseDTO;
import com.weg.DTO.dto.AulaRequestDTO;
import com.weg.DTO.dto.AulaResponseDTO;
import com.weg.DTO.service.AulaService;

@RestController
@RequestMapping("/aulas")
public class AulaController {
    private AulaService aulaService;

    

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    // Create
    @PostMapping()
    public AulaResponseDTO post(@RequestBody AulaRequestDTO aulaRequestDTO) {
        try {
            AulaResponseDTO aulaResponseDTO = aulaService.createAula(aulaRequestDTO);
            return aulaResponseDTO;
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Read All
    @GetMapping()
    public List<AulaResponseDTO> readAll() {
        try {
            List<AulaResponseDTO> listAll = aulaService.readAll();
            return listAll;
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Read By Id
    @GetMapping("/{id}")
    public Optional<AulaResponseDTO> readById(@PathVariable long id) {
        try {
            Optional<AulaResponseDTO> aulaResponseDTO = aulaService.readAulaById(id);
            return aulaResponseDTO;
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Update

    @PutMapping("/{id}")
    public String put(@PathVariable long id, @RequestBody AulaRequestDTO aulaRequestDTO) {
        try {
            aulaService.updateAula(aulaRequestDTO, id);
            return "Aula atualizada com sucesso!";

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        try {   
            aulaService.deleteById(id);
            return "Aula Deletado com sucesso";
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
