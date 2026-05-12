package com.weg.DTO.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class AulaEntity {
    private long id;
    private long turma_id;
    private LocalDate dataHora;
    private String assunto;
    public AulaEntity(long turma_id, LocalDate dataHora, String assunto) {
        this.turma_id = turma_id;
        this.dataHora = dataHora;
        this.assunto = assunto;
    }

    
    public AulaEntity(long id, long turma_id, LocalDate dataHora, String assunto) {
        this.id = id;
        this.turma_id = turma_id;
        this.dataHora = dataHora;
        this.assunto = assunto;
    }


    

    public AulaEntity() {
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getTurma_id() {
        return turma_id;
    }
    public void setTurma_id(long turma_id) {
        this.turma_id = turma_id;
    }
    public LocalDate getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }
    public String getAssunto() {
        return assunto;
    }
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    
    
}
