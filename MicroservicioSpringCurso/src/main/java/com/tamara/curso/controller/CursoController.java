package com.tamara.curso.controller;

import com.tamara.SpringCommons.controller.CommonController;
import com.tamara.commonsEntity.Alumno;
import com.tamara.curso.config.Configuration;
import com.tamara.curso.models.entity.Curso;
import com.tamara.curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController extends CommonController<Curso, CursoService> {

    @Autowired
    Configuration configuration;

    @Autowired
    public CursoController(CursoService cursoService) {
        super(cursoService);
    }

    @Value("${config.balanceador.test}")
    private String balanceadorTest;

    //CONFIG

    @GetMapping("/endpoint")
    public String retrieveLimits(){
        return configuration.getValue();
    }

    //CONFIG

    @GetMapping("/balanceador-test")
    public ResponseEntity<?> balanceadorTest() {
        Map<String, Object> response = new HashMap<>();
        response.put("balanceador", balanceadorTest);
        response.put("Cursos", service.findAll());

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id) {
        Optional<Curso> cursoExistente = service.findById(id);

        if (cursoExistente.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Curso cursoActual = cursoExistente.get();
        cursoActual.setNombre(curso.getNombre());
        Curso cursoActualizado = service.save(cursoActual);
        return ResponseEntity.ok(cursoActualizado);
    }

    //NUEVOS CONTROLLERS
    @PutMapping("/{id}/asignar-alumno")
    public ResponseEntity<?> asignarAlumno(@RequestBody List<Alumno> alumno, @PathVariable Long id){

        Optional<Curso> ob = service.findById(id);

        if(ob.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        Curso cursoBd = ob.get();
        alumno.forEach(a -> {
            cursoBd.addAlumnos(a);
        });

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoBd));

    }

    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id) {
        Optional<Curso> ob = service.findById(id);

        if (ob.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Curso cursoBd = ob.get();
        cursoBd.removeAlumnos(alumno);
        service.save(cursoBd);
        return ResponseEntity.ok(cursoBd);
    }




    //////////////////////////////////////


}
