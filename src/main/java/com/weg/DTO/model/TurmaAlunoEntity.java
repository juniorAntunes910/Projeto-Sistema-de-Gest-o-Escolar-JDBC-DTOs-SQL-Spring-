package com.weg.DTO.model;

import org.springframework.stereotype.Component;

@Component
public class TurmaAlunoEntity {
    private long turma_id;
    private long aluno_id;
    public TurmaAlunoEntity(long turma_id, long aluno_id) {
        this.turma_id = turma_id;
        this.aluno_id = aluno_id;
    }

    

    
    public TurmaAlunoEntity() {
    }




    public long getTurma_id() {
        return turma_id;
    }
    public void setTurma_id(long turma_id) {
        this.turma_id = turma_id;
    }
    public long getAluno_id() {
        return aluno_id;
    }
    public void setAluno_id(long aluno_id) {
        this.aluno_id = aluno_id;
    }

    
}
