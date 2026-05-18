package com.weg.DTO.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weg.DTO.dto.CursoRequestDTO;
import com.weg.DTO.dto.CursoResponseDTO;
import com.weg.DTO.service.CursoService;

import jakarta.websocket.server.PathParam;

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
@RequestMapping("/cursos")
public class CursoController {
    private CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    

    //Create
    @PostMapping()
    public CursoResponseDTO post(@RequestBody CursoRequestDTO cursoRequestDTO) {
        try {
            CursoResponseDTO cursoResponseDTO = cursoService.createCurso(cursoRequestDTO);
            return cursoResponseDTO;
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    //Read All

    @GetMapping()
    public List<CursoResponseDTO> getAll() {
        try {
            List<CursoResponseDTO> list = cursoService.readAll();
            return list;            
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    

    //Read By Id

    @GetMapping("{id}")
    public CursoResponseDTO getById(@PathVariable long id){
        try {
            CursoResponseDTO cursoResponseDTO = cursoService.readCursoById(id);
            return cursoResponseDTO;
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    

    //Uptade

    @PutMapping("/{id}")
    public String put(@PathVariable Long id, @RequestBody CursoRequestDTO cursoRequestDTO) {
        try {
            cursoService.updateCurso(cursoRequestDTO, id);
            return "Curso Atualizado com sucesso!";            
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //Delete

    @DeleteMapping("{id}")
    public String delete(@PathVariable long id){
        try{
            cursoService.deleteById(id);
            return "Curso deletado com sucesso!";
        }catch(SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
