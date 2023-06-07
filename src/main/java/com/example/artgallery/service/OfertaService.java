package com.example.artgallery.service;

import com.example.artgallery.model.Oferta;
import com.example.artgallery.repository.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfertaService {
    @Autowired
    OfertaRepository ofertaRepository;

    public List<Oferta> getAllOferta(){
        return (List<Oferta>) ofertaRepository.findAll();
    }

    public Optional<Oferta> getOferta(Long id){
        return ofertaRepository.findById(id);
    }

    public Oferta postOferta(Oferta oferta){
        return ofertaRepository.save(oferta);
    }

    public boolean deleteOferta(Long id){
        try {
            ofertaRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
