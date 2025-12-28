package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {
    }

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        //manejo de posibles nulos en la API
        try {
            this.fechaDeNacimiento = Integer.valueOf(datosAutor.fechaDeNacimiento());
        } catch (NumberFormatException e) {
            this.fechaDeNacimiento = null;
        }

        try {
            this.fechaDeFallecimiento = Integer.valueOf(datosAutor.fechaDeFallecimiento());
        } catch (NumberFormatException e) {
            this.fechaDeFallecimiento = null;
        }
    }

    @Override
    public String toString() {
        return
                "------Autor------"+ '\n' +
                "Autor: " + nombre + '\n' +
                        "Fecha de Nacimiento: " + fechaDeNacimiento + '\n' +
                        "Fecha de Fallecimiento: " + fechaDeFallecimiento + '\n' +
                        "Libros: " + libros.stream().map(l -> l.getTitulo()).toList() + '\n' +
                        "-----------------"+'\n';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
