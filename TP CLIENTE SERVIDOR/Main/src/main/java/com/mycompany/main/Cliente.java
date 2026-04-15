/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author ofacu
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5000;

        try {
            Socket socket = new Socket(host, puerto);
            System.out.println("Conectado al servidor");

            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            BufferedReader teclado = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            String mensaje;

            System.out.println("Escribí mensajes (exit para salir):");

            while ((mensaje = teclado.readLine()) != null) {
                salida.println(mensaje);

                String respuesta = entrada.readLine();
                System.out.println("Servidor: " + respuesta);

                if (mensaje.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            salida.close();
            entrada.close();
            teclado.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}