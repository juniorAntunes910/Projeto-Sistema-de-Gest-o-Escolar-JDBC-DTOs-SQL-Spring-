package com.weg.DTO.model;

import org.springframework.stereotype.Component;

@Component
public class TurmaEntity {
    private long id;
    private String nome;
    private long curso_id;
    private long professor_id;
    public TurmaEntity(String nome, long curso_id, long professor_id) {
        this.nome = nome;
        this.curso_id = curso_id;
        this.professor_id = professor_id;
    }

    
    public TurmaEntity(long id, String nome, long curso_id, long professor_id) {
        this.id = id;
        this.nome = nome;
        this.curso_id = curso_id;
        this.professor_id = professor_id;
    }


    

    public TurmaEntity() {
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public long getCurso_id() {
        return curso_id;
    }
    public void setCurso_id(long curso_id) {
        this.curso_id = curso_id;
    }
    public long getProfessor_id() {
        return professor_id;
    }
    public void setProfessor_id(long professor_id) {
        this.professor_id = professor_id;
    }

    
}
