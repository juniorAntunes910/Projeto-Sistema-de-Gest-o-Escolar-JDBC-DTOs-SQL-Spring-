package com.weg.DTO.model;

import org.springframework.stereotype.Component;

@Component
public class CursoProfessorEntity {
    private long professorId;
    private long cursoId;
    public CursoProfessorEntity(long professorId, long cursoId) {
        this.professorId = professorId;
        this.cursoId = cursoId;
    }


    
    public CursoProfessorEntity() {
    }



    public long getProfessorId() {
        return professorId;
    }
    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }
    public long getCursoId() {
        return cursoId;
    }
    public void setCursoId(long cursoId) {
        this.cursoId = cursoId;
    }

    
}
