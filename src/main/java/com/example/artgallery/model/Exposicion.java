package com.example.artgallery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exposicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private Date fechaInauguracion;
    private Date fechaClausura;

    /*@ManyToMany(cascade =  {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })*/
    @ManyToMany(cascade =  CascadeType.ALL)
    @JoinTable(
            name = "exposicion_obra",
            joinColumns = @JoinColumn(name = "exposicion_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "obra_id", referencedColumnName = "id")
    )
    private List<Obra> obras;
}