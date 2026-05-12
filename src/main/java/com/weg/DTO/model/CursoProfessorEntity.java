package com.weg.DTO.model;

public class CursoProfessorEntity {
    private long professorId;
    private long cursoId;
    public CursoProfessorEntity(long professorId, long cursoId) {
        this.professorId = professorId;
        this.cursoId = cursoId;
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
