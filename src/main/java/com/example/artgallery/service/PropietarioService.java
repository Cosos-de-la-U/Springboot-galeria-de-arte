package com.example.artgallery.service;

import com.example.artgallery.model.Propietario;
import com.example.artgallery.repository.PropietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropietarioService {
    @Autowired
    PropietarioRepository propietarioRepository;

    public List<Propietario> getAllPropietario(){
        return (List<Propietario>) propietarioRepository.findAll();
    }

    public Optional<Propietario> getPropietario(Long id){
        return propietarioRepository.findById(id);
    }

    public Propietario postPropietario(Propietario propietario){
        return propietarioRepository.save(propietario);
    }

    public boolean deletePropietario(Long id){
        try {
            propietarioRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
