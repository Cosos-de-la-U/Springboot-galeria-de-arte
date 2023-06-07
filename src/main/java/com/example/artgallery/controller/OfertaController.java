package com.example.artgallery.controller;

import com.example.artgallery.model.Oferta;
import com.example.artgallery.service.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/oferta")
public class OfertaController {
    @Autowired
    OfertaService ofertaService;

    @GetMapping
    public ResponseEntity<List<Oferta>> getAllOferta(){
        List<Oferta> ofertas = ofertaService.getAllOferta();
        if(ofertas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(ofertas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oferta> getOferta(@PathVariable Long id){
        Optional<Oferta> ofertaOptional = ofertaService.getOferta(id);
        if(ofertaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Oferta oferta = ofertaOptional.get(); // .get() lo convierto a El objeto que es en vez de Optional<Objeto>
        return ResponseEntity.ok(oferta);
    }

    @PostMapping
    public ResponseEntity<Oferta> postOferta(@RequestBody Oferta oferta){
       Oferta ofertaPost = ofertaService.postOferta(oferta);
       if(ofertaPost == null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
       return ResponseEntity.ok(ofertaPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOferta(@PathVariable Long id){
        try {
            Oferta data =  ofertaService.getOferta(id).get();
            boolean ok = ofertaService.deleteOferta(id);
            if(ok) return ResponseEntity.ok(String.format("%s was removed!", data.getId()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("%s was NOT removed!, something happened!", data.getId()));

        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Something wrong happened!"));
        }
    }
}
