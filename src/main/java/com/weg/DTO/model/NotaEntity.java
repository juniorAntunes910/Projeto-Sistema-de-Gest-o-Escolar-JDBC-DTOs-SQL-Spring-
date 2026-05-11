package com.weg.DTO.model;

public class NotaEntity {
    private long id;
    private long aluno_id;
    private long aula_id;
    private double valor;
    public NotaEntity(long aluno_id, long aula_id, double valor) {
        this.aluno_id = aluno_id;
        this.aula_id = aula_id;
        this.valor = valor;
    }
    
    public NotaEntity(long id, long aluno_id, long aula_id, double valor) {
        this.id = id;
        this.aluno_id = aluno_id;
        this.aula_id = aula_id;
        this.valor = valor;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getAluno_id() {
        return aluno_id;
    }
    public void setAluno_id(long aluno_id) {
        this.aluno_id = aluno_id;
    }
    public long getAula_id() {
        return aula_id;
    }
    public void setAula_id(long aula_id) {
        this.aula_id = aula_id;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    
}
