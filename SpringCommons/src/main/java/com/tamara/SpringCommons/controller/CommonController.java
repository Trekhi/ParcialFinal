package com.tamara.SpringCommons.controller;

import com.tamara.SpringCommons.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CommonController <E , S extends CommonService<E>> {

    @Autowired
    protected S service;

    @Value("${config.balanceador.test}")
    protected String balanceadorTest;

    public CommonController(S service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.findAll());
    }

    public ResponseEntity<?> verPorId(Long id) {
        Optional<E> entidad = service.findById(id);
        if (entidad.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(entidad.get());
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody E entidad) {
        try {
            E nuevaEntidad = service.save(entidad);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEntidad);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
