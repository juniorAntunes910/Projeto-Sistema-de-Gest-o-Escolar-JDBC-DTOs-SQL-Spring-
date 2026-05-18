package com.weg.DTO.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weg.DTO.dto.AlunoRequestDTO;
import com.weg.DTO.dto.AlunoResponseDTO;
import com.weg.DTO.service.AlunoService;

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
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }


    //Create
    @PostMapping()
    public AlunoResponseDTO post(@RequestBody AlunoRequestDTO alunoRequestDTO) {
        try{
            AlunoResponseDTO alunoResponseDTO = alunoService.createAluno(alunoRequestDTO);
            return alunoResponseDTO;
        }catch( SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }


    //Read All
    @GetMapping()
    public List<AlunoResponseDTO> readAll(){
        try{
            List<AlunoResponseDTO> listAll = alunoService.readAll();
            return listAll;
        } catch( SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }


    //Read By Id
    @GetMapping("/{id}")
    public AlunoResponseDTO readById(@PathVariable long id){
        try{
            AlunoResponseDTO alunoResponseDTO = alunoService.readAlunoById(id);
            return alunoResponseDTO;
        }catch(SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    //Update 

    @PutMapping("/{id}")
    public String put(@PathVariable long id, @RequestBody AlunoRequestDTO alunoRequestDTO) {
        try{
            alunoService.updateAluno(alunoRequestDTO, id);
            return "Aluno atualizado com sucesso!";

        }catch(SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        try{
            alunoService.deleteById(id);
            return "Aluno Deletado com sucesso";
        }catch(SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }


    
}
