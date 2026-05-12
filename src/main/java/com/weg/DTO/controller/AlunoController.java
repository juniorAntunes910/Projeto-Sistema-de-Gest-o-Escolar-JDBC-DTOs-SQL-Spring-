package com.weg.DTO.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weg.DTO.dto.AlunoRequestDTO;
import com.weg.DTO.dto.AlunoResponseDTO;
import com.weg.DTO.service.AlunoService;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public AlunoResponseDTO postMethodName(@RequestBody AlunoRequestDTO alunoRequestDTO) {
        try{
            AlunoResponseDTO alunoResponseDTO = alunoService.createAluno(alunoRequestDTO);
            return alunoResponseDTO;
        }catch( SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    
}
