package com.example.artgallery.service;

import com.example.artgallery.model.Exposicion;
import com.example.artgallery.repository.ExposicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExposicionService {
    @Autowired
    ExposicionRepository exposicionRepository;

    public List<Exposicion> getAllExposicion(){
        return (List<Exposicion>) exposicionRepository.findAll();
    }

    public Optional<Exposicion> getExposicion(Long id){
        return exposicionRepository.findById(id);
    }

    public Exposicion postExposicion(Exposicion exposicion){
        return exposicionRepository.save(exposicion);
    }

    public boolean deleteExposicion(Long id){
        try {
            exposicionRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
