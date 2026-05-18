package com.weg.DTO.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weg.DTO.dto.CursoRequestDTO;
import com.weg.DTO.dto.CursoResponseDTO;
import com.weg.DTO.dto.NotaRequestDTO;
import com.weg.DTO.dto.NotaResponseDTO;
import com.weg.DTO.service.NotaService;

@RestController
@RequestMapping("/notas")
public class NotaController {
    private NotaService notaService;

    

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    // Create
    @PostMapping()
    public NotaResponseDTO post(@RequestBody NotaRequestDTO notaRequestDTO) {
        try {
            NotaResponseDTO notaResponseDTO = notaService.createNota(notaRequestDTO);
            return notaResponseDTO;
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Read All

    @GetMapping()
    public List<NotaResponseDTO> getAll() {
        try {
            List<NotaResponseDTO> list = notaService.readAll();
            return list;
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Read By Id

    @GetMapping("{id}")
    public NotaResponseDTO getById(@PathVariable long id) {
        try {
            NotaResponseDTO notaResponseDTO = notaService.readNotaById(id);
            return notaResponseDTO;
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Uptade

    @PutMapping("/{id}")
    public String put(@PathVariable Long id, @RequestBody NotaRequestDTO notaRequestDTO) {
        try {
            notaService.updateNota(notaRequestDTO, id);
            return "Curso Atualizado com sucesso!";
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Delete

    @DeleteMapping("{id}")
    public String delete(@PathVariable long id) {
        try {
            notaService.deleteById(id);
            return "Curso deletado com sucesso!";
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
