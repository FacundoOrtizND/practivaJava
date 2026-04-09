package com.mycompany.mavenproject3;

public class Libro {

    String titulo;
    String autor;
    boolean disponible;
    Usuario prestadoA; // NUEVO

    // CONSTRUCTOR
    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
        this.prestadoA = null;
    }

    public void mostrar() {
        if (disponible) {
            System.out.println(titulo + " - " + autor + " | Disponible");
        } else {
            System.out.println(titulo + " - " + autor + " | Prestado a: " + prestadoA.getNombre());
        }
    }

    // PRESTAR
    public void prestar(Usuario u) {
        disponible = false;
        prestadoA = u;
    }

    // DEVOLVER
    public void devolver() {
        disponible = true;
        prestadoA = null;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    // SOBRECARGA
    public void info() {
        System.out.println(titulo);
    }

    public void info(String mensaje) {
        System.out.println(mensaje + ": " + titulo);
    }
}