package com.mycompany.mavenproject3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Biblioteca b = new Biblioteca();

        int opcion;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Ver libros");
            System.out.println("2. Agregar libro");
            System.out.println("3. Retirar libro");
            System.out.println("4. Crear usuario");
            System.out.println("5. Eliminar usuario");
            System.out.println("6. Ver libros prestados");
            System.out.println("7. Devolver libro");
            System.out.println("0. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    b.mostrarLibros();
                    break;

                case 2:
                    System.out.print("Titulo: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();

                    b.agregarLibro(new Libro(titulo, autor));
                    break;

                case 3:
                    if (!b.hayLibros() || !b.hayUsuarios()) {
                        System.out.println("Faltan libros o usuarios.");
                        break;
                    }

                    System.out.println("Seleccione libro:");
                    b.mostrarLibros();
                    int l = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Seleccione usuario:");
                    b.mostrarUsuarios();
                    int u = sc.nextInt();
                    sc.nextLine();

                    b.prestarLibro(l - 1, b.usuarios.get(u - 1));
                    break;

                case 4:
                    System.out.print("Nombre usuario: ");
                    String nombre = sc.nextLine();
                    b.agregarUsuario(new Usuario(nombre));
                    break;

                case 5:
                    if (!b.hayUsuarios()) {
                        System.out.println("No hay usuarios.");
                        break;
                    }

                    b.mostrarUsuarios();
                    int elim = sc.nextInt();
                    sc.nextLine();
                    b.eliminarUsuario(elim - 1);
                    break;

                case 6:
                    b.mostrarPrestados();
                    break;

                case 7:
                    b.mostrarPrestados();
                    System.out.println("Seleccione libro a devolver:");
                    int dev = sc.nextInt();
                    sc.nextLine();

                    b.devolverLibro(dev - 1);
                    break;
            }

        } while (opcion != 0);

        System.out.println("Fin del programa");
    }
}