package com.weg.DTO.model;

import org.springframework.stereotype.Component;

@Component
public class CursoEntity {
    private long id;
    private String nome;
    private String codigo;

    public CursoEntity(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    
    public CursoEntity(long id, String nome, String codigo) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
    }

    


    public CursoEntity() {
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    
}
