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
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

    public static ArrayList<ClienteHandler> clientes = new ArrayList<>();
    public static boolean activo = true;
    private static int contadorClientes = 1;

    public static void main(String[] args) {
        int puerto = 5000;

        try {
            ServerSocket servidor = new ServerSocket(puerto);
            servidor.setReuseAddress(true);

            System.out.println("SERVIDOR INICIADO");
            System.out.println("Comandos:");
            System.out.println("shutdown → apaga el servidor");
            System.out.println("--------------------------------");

            // 🔥 Hilo para comandos del servidor
            Thread consola = new Thread(() -> {
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
                String comando;
                try {
                    while ((comando = teclado.readLine()) != null) {
                        if (comando.equalsIgnoreCase("shutdown")) {
                            apagarServidor();
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error en consola servidor");
                }
            });
            consola.start();

            while (activo) {
                Socket cliente = servidor.accept();

                String nombre = "C" + contadorClientes++;
                System.out.println("Nuevo cliente: " + nombre);

                ClienteHandler handler = new ClienteHandler(cliente, nombre);
                clientes.add(handler);
                handler.start();
            }

            servidor.close();
            System.out.println("Servidor apagado");

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    public static void apagarServidor() {
        System.out.println("Apagando servidor...");

        for (ClienteHandler cliente : clientes) {
            cliente.enviarMensaje("Servidor cerrado");
            cliente.cerrarConexion();
        }

        clientes.clear();
        activo = false;
    }
}