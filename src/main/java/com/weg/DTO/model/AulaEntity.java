package com.weg.DTO.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class AulaEntity {
    private long id;
    private long turma_id;
    private LocalDateTime dataHora;
    private String assunto;
    public AulaEntity(long turma_id, LocalDateTime dataHora, String assunto) {
        this.turma_id = turma_id;
        this.dataHora = dataHora;
        this.assunto = assunto;
    }

    
    public AulaEntity(long id, long turma_id, LocalDateTime dataHora, String assunto) {
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
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    public String getAssunto() {
        return assunto;
    }
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    
    
}
