package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    // Inyeccion de dependencias por constructor
    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu(){
        var opcion = -1;
        while(opcion != 0){
            var menu = """
                    
                    1 - Buscar libro por título (API)
                    2 - Listar libros registrados (Base de Datos)
                    3 - Listar autores registrados (Base de Datos)
                    0 - Salir
                    
                    """;
            System.out.println(menu);
            System.out.print("Seleccione una opción: ");
            try {
                opcion = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                teclado.nextLine(); // Limpiar el buffer
                continue;
            }

            switch (opcion){
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarautoresRegistrados();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }


        }
    }

    private void buscarLibroWeb(){
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ","+"));
        var datosBusqueda = conversor.obtenerDatos(json, DatosGutendex.class);

        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

        if(libroBuscado.isPresent()){
            System.out.println("Libro encontrado: " + libroBuscado.get());
            DatosLibro datosLibro = libroBuscado.get();

            //Verificar si el libro ya existe
            if(libroRepository.findByTituloContainsIgnoreCase(datosLibro.titulo()).isPresent()){
                System.out.println("El libro ya esta registrado en la base de datos,");
                return;
            }

            // Logica para guardar Autor y libro
            Libro libro = new Libro(libroBuscado.get());
            //Buscamos si el autro ya existe
            DatosAutor datosAutor = datosLibro.autor().get(0);
            Autor autor = autorRepository.findByNombreIgnoreCase(datosAutor.nombre())
                            .orElseGet(() -> autorRepository.save(new Autor(datosAutor)));

            libro.setAutor(autor);
            libroRepository.save(libro);
            System.out.println("Libro guardado exitosamente en la base de datos.");

        } else {
            System.out.println("Libro no encontrado");
        }
    }

    private void listarLibrosRegistrados(){
        libroRepository.findAll().forEach(System.out::println);
    }

    private void listarautoresRegistrados(){
        autorRepository.findAll().forEach(System.out::println);
    }



}
