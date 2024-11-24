package com.tamara.curso.service;

import com.tamara.SpringCommons.service.CommonService;
import com.tamara.curso.models.entity.Curso;

public interface CursoService extends CommonService<Curso> {

    public Curso save (Curso alumno);
    public void deleteById(Long id);
}
