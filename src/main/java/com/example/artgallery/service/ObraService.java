package com.example.artgallery.service;

import com.example.artgallery.model.Obra;
import com.example.artgallery.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObraService {
    @Autowired
    ObraRepository obraRepository;

    public List<Obra> getAllObra(){
        return (List<Obra>) obraRepository.findAll();
    }

    public Optional<Obra> getObra(Long id){
        return obraRepository.findById(id);
    }

    public Obra postObra(Obra obra){
        return obraRepository.save(obra);
    }

    public boolean deleteObra(Long id){
        try {
            obraRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
