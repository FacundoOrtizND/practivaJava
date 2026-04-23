/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author ofacu
 */

import java.io.*;
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

            Thread recibir = new Thread(() -> {
                String msg;
                try {
                    while ((msg = entrada.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    System.out.println("Servidor desconectado");
                }
            });

            recibir.start();

            System.out.println("Comandos disponibles:");
            System.out.println("ALL mensaje → enviar a todos");
            System.out.println("exit → salir");
            System.out.println("--------------------------");

            String mensaje;

            while ((mensaje = teclado.readLine()) != null) {
                salida.println(mensaje);

                if (mensaje.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            salida.close();
            teclado.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}