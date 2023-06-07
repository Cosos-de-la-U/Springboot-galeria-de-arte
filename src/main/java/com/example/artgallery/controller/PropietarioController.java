package com.example.artgallery.controller;

import com.example.artgallery.model.Propietario;
import com.example.artgallery.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/propietario")
public class PropietarioController {
    @Autowired
    PropietarioService propietarioService;

    @GetMapping
    public ResponseEntity<List<Propietario>> getAllPropietario(){
        List<Propietario> propietarios = propietarioService.getAllPropietario();
        if(propietarios.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(propietarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Propietario> getPropietario(@PathVariable Long id){
        Optional<Propietario> propietarioOptional = propietarioService.getPropietario(id);
        if(propietarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Propietario propietario = propietarioOptional.get(); // .get() lo convierto a El objeto que es en vez de Optional<Objeto>
        return ResponseEntity.ok(propietario);
    }

    @PostMapping
    public ResponseEntity<Propietario> postPropietario(@RequestBody Propietario propietario){
       Propietario propietarioPost = propietarioService.postPropietario(propietario);
       if(propietarioPost == null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
       return ResponseEntity.ok(propietarioPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePropietario(@PathVariable Long id){
        try {
            Propietario data =  propietarioService.getPropietario(id).get();
            boolean ok = propietarioService.deletePropietario(id);
            if(ok) return ResponseEntity.ok(String.format("%s was removed!", data.getNombre()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("%s was NOT removed!, something happened!", data.getNombre()));

        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Something wrong happened!"));
        }
    }
}
