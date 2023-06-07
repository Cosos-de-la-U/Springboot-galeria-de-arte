package com.example.artgallery.model;

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
    private String estilo;
    private double precioSalida;

    @ManyToOne(cascade =  CascadeType.ALL)
    private Propietario propietario;

    @ManyToMany(mappedBy = "obras")
    private List<Exposicion> exposiciones;
}