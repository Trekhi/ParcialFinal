package com.tamara.usuario.controller;


import com.tamara.SpringCommons.controller.CommonController;
//import com.tamara.usuario.models.entity.Alumno;
import com.tamara.commonsEntity.Alumno;
import com.tamara.usuario.config.Configuration;
import com.tamara.usuario.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

    @Autowired
    Configuration configuration;

    @Autowired
    private AlumnoService service;

    @Value("${config.balanceador.test}")
    private String balanceadorTest;

    public AlumnoController(AlumnoService service) {
        super(service);
    }

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
        response.put("alumno", service.findAll());

        return ResponseEntity.ok().body(response);
    }

//    @GetMapping("/todos")
//    public ResponseEntity<?> listarAlumno(){
//        return ResponseEntity.ok().body(service.findAll());
//    }
//
//    @GetMapping("/Uno/{id}")
//    public ResponseEntity<?> ver(@PathVariable Integer id){
//        Optional <Alumno> ob = service.findById(id);
//        if (ob.isEmpty()){
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok().body(ob.get());
//    }

    @PostMapping("/Guardar")
    public ResponseEntity<?> crear(@RequestBody Alumno alumno){

        Alumno alumno1 = service.save(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumno1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id) {
        Optional<Alumno> alumnoExistente = service.findById(id);

        if (alumnoExistente.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Alumno alumnoActual = alumnoExistente.get();

        // Actualizar los campos del alumno
        alumnoActual.setNombre(alumno.getNombre());
        alumnoActual.setApellido(alumno.getApellido());
        alumnoActual.setEmail(alumno.getEmail());

        // Guardar el alumno actualizado
        Alumno alumnoActualizado = service.save(alumnoActual);

        return ResponseEntity.ok(alumnoActualizado);
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Integer id) {
//        Optional<Alumno> alumnoExistente = service.findById(id);
//
//        if (alumnoExistente.isEmpty()) {
//            return ResponseEntity.noContent().build(); // 204 No Content si no se encuentra el alumno
//        }
//
//        Alumno alumnoActual = alumnoExistente.get();
//
//        // Actualizar los campos del alumno
//        alumnoActual.setNombre(alumno.getNombre());
//        alumnoActual.setApellido(alumno.getApellido());
//        alumnoActual.setEmail(alumno.getEmail());
//
//        // Guardar el alumno actualizado
//        service.save(alumnoActual);
//
//        return ResponseEntity.ok(alumnoActual); // Devuelve el alumno actualizado
//    }

//    @DeleteMapping("/eliminar/{id}")
//    public ResponseEntity<?> eliminar(@PathVariable Long id){
//        service.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }

}
