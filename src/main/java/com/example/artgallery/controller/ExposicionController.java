package com.example.artgallery.controller;

import com.example.artgallery.model.Exposicion;
import com.example.artgallery.model.Obra;
import com.example.artgallery.repository.ObraRepository;
import com.example.artgallery.service.ExposicionService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/exposicion")
public class ExposicionController {
    @Autowired
    ExposicionService exposicionService;

    @Autowired
    ObraRepository obraRepository;

    @GetMapping
    public ResponseEntity<List<Exposicion>> getAllExposicion() {
        List<Exposicion> exposicions = exposicionService.getAllExposicion();
        if (exposicions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(exposicions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exposicion> getExposicion(@PathVariable Long id) {
        Optional<Exposicion> exposicionOptional = exposicionService.getExposicion(id);
        if (exposicionOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Exposicion exposicion = exposicionOptional.get(); // .get() lo convierto a El objeto que es en vez de Optional<Objeto>
        return ResponseEntity.ok(exposicion);
    }

    @PostMapping
    public ResponseEntity<Exposicion> postExposicion(@RequestBody Exposicion exposicion) {
        List<Obra> obras = exposicion.getObras();
        if (obras != null) {
            for (Obra obra : obras) {
                Long id = obra.getId();
                Obra existingObra = obraRepository.findById(id).orElse(null);
                if (existingObra != null) {
                    existingObra.getExposiciones().add(exposicion);
                    //exposicion.getObras().add(existingObra); // Update the relationship on the Exposicion side
                }
            }
        }
        Exposicion exposicionPost = exposicionService.postExposicion(exposicion);
        if (exposicionPost == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(exposicionPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExposicion(@PathVariable Long id) {
        try {
            Exposicion data = exposicionService.getExposicion(id).get();
            boolean ok = exposicionService.deleteExposicion(id);
            if (ok) return ResponseEntity.ok(String.format("%s was removed!", data.getId()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("%s was NOT removed!, something happened!", data.getId()));

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Something wrong happened!"));
        }
    }
}
