package com.tamara.usuario.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//import com.tamara.usuario.models.entity.Alumno;
import com.tamara.commonsEntity.Alumno;

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

}
