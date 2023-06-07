package com.example.artgallery.controller;

import com.example.artgallery.model.Obra;
import com.example.artgallery.service.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/obra")
public class ObraController {
    @Autowired
    ObraService obraService;

    @GetMapping
    public ResponseEntity<List<Obra>> getAllObra(){
        List<Obra> obras = obraService.getAllObra();
        if(obras.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(obras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obra> getObra(@PathVariable Long id){
        Optional<Obra> obraOptional = obraService.getObra(id);
        if(obraOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Obra obra = obraOptional.get(); // .get() lo convierto a El objeto que es en vez de Optional<Objeto>
        return ResponseEntity.ok(obra);
    }

    @PostMapping
    public ResponseEntity<Obra> postObra(@RequestBody Obra obra){
       Obra obraPost = obraService.postObra(obra);
       if(obraPost == null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
       return ResponseEntity.ok(obraPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteObra(@PathVariable Long id){
        try {
            Obra data =  obraService.getObra(id).get();
            boolean ok = obraService.deleteObra(id);
            if(ok) return ResponseEntity.ok(String.format("%s was removed!", data.getTitulo()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("%s was NOT removed!, something happened!", data.getTitulo()));

        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Something wrong happened!"));
        }
    }
}
