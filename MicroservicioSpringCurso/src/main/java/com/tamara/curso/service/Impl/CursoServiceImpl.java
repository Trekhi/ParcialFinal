package com.tamara.curso.service.Impl;


import com.tamara.SpringCommons.service.CommonService;
import com.tamara.SpringCommons.service.CommonServiceImpl;
import com.tamara.curso.models.entity.Curso;
import com.tamara.curso.repository.CursoRepository;
import com.tamara.curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

    @Autowired
    public CursoServiceImpl(CursoRepository cursoRepository){
        super();
    }
}
