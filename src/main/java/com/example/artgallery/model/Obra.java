package com.example.artgallery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String numeroRegistro;
    private String titulo;
    private String artista;
    private String image;
    private String estilo;
    private double precioSalida;
    
    @ManyToOne(cascade =  CascadeType.ALL)
    private Exposicion exposicion;
    @ManyToOne(cascade =  CascadeType.ALL)
    private Propietario propietario;
}
