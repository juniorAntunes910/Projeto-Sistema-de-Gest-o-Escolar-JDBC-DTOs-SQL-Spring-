package com.weg.DTO.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weg.DTO.dto.ProfessorRequestDTO;
import com.weg.DTO.dto.ProfessorResponseDTO;
import com.weg.DTO.service.ProfessorService;

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
@RequestMapping("/professores")
public class ProfessorController {
    private ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    // Create
    @PostMapping()
    public ProfessorResponseDTO post(@RequestBody ProfessorRequestDTO professorRequestDTO) {
        try{
            ProfessorResponseDTO professorResponseDTO = professorService.createProfessor(professorRequestDTO);
            return professorResponseDTO;
        }catch(SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    // Read All
    @GetMapping()
    public List<ProfessorResponseDTO> getAll() {
        try{
            List<ProfessorResponseDTO> list = professorService.readAll();
            return list;
        }catch(SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    

    // Read By Id

    @GetMapping("/{id}")
    public ProfessorResponseDTO getById(@PathVariable long id) {
        try{
            ProfessorResponseDTO professorResponseDTO = professorService.readProfessorById(id);
            return professorResponseDTO;
        }catch(SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    

    // Update

    @PutMapping("/{id}")
    public String put(@PathVariable long id, @RequestBody ProfessorRequestDTO professorRequestDTO) {
        try{
            professorService.updateProfessor(professorRequestDTO, id);
            return "Professor Atualizado com sucesso!";
        }   catch(SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }     
    }

    // Delete


    @DeleteMapping("{id}")
    public String delete(@PathVariable long id){
        try{
            professorService.deleteById(id);
            return "Professor deletado com sucesso";
        }catch(SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
