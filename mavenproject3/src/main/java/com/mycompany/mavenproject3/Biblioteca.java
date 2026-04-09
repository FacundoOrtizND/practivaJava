package com.mycompany.mavenproject3;

import java.util.ArrayList;

public class Biblioteca {

    ArrayList<Libro> libros = new ArrayList<>();
    ArrayList<Usuario> usuarios = new ArrayList<>();

    public void mostrarLibros() {
        if (libros.size() == 0) {
            System.out.println("No hay libros cargados.");
            return;
        }

        for (int i = 0; i < libros.size(); i++) {
            System.out.print((i + 1) + ". ");
            libros.get(i).mostrar();
        }
    }

    public void mostrarUsuarios() {
        if (usuarios.size() == 0) {
            System.out.println("No hay usuarios cargados.");
            return;
        }

        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i).getNombre());
        }
    }

    public void mostrarPrestados() {
        boolean hay = false;

        for (int i = 0; i < libros.size(); i++) {
            if (!libros.get(i).estaDisponible()) {
                System.out.println((i + 1) + ". " + libros.get(i).titulo + " (Usuario: " + libros.get(i).prestadoA.getNombre() + ")");
                hay = true;
            }
        }

        if (!hay) {
            System.out.println("No hay libros prestados.");
        }
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void agregarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public void eliminarUsuario(int index) {
        if (index >= 0 && index < usuarios.size()) {
            usuarios.remove(index);
            System.out.println("Usuario eliminado");
        } else {
            System.out.println("Opción inválida");
        }
    }

    public void prestarLibro(int indexLibro, Usuario u) {
        if (libros.get(indexLibro).estaDisponible()) {
            libros.get(indexLibro).prestar(u);
            System.out.println("Libro prestado a " + u.getNombre());
        } else {
            System.out.println("No disponible");
        }
    }

    public void devolverLibro(int indexLibro) {
        if (!libros.get(indexLibro).estaDisponible()) {
            libros.get(indexLibro).devolver();
            System.out.println("Libro devuelto");
        } else {
            System.out.println("Ese libro no estaba prestado");
        }
    }

    public boolean hayLibros() {
        return libros.size() > 0;
    }

    public boolean hayUsuarios() {
        return usuarios.size() > 0;
    }
}