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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClienteHandler extends Thread {

    private Socket socket;
    private PrintWriter salida;
    private String nombre;

    public ClienteHandler(Socket socket, String nombre) {
        this.socket = socket;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            salida = new PrintWriter(socket.getOutputStream(), true);

            salida.println("Bienvenido, tu nombre es: " + nombre);
            salida.println("Comandos:");
            salida.println("ALL mensaje → enviar a todos");
            salida.println("C1 mensaje → enviar privado");
            salida.println("LIST → ver usuarios");
            salida.println("TIME → fecha y hora");
            salida.println("RESOLVE expr → resolver operación");
            salida.println("exit → salir");
            salida.println("--------------------------");

            String mensaje;

            while ((mensaje = entrada.readLine()) != null) {
                System.out.println(nombre + ": " + mensaje);

                // ALL
                if (mensaje.toUpperCase().startsWith("ALL ")) {
                    String contenido = mensaje.substring(4).trim();

                    for (ClienteHandler cliente : Servidor.clientes) {
                        cliente.enviarMensaje(nombre + ": " + contenido);
                    }
                }

                // LIST
                else if (mensaje.equalsIgnoreCase("LIST")) {
                    String lista = "Clientes conectados: ";
                    for (ClienteHandler cliente : Servidor.clientes) {
                        lista += cliente.getNombre() + " ";
                    }
                    salida.println(lista);
                }

                // TIME
                else if (mensaje.equalsIgnoreCase("TIME")) {
                    LocalDateTime ahora = LocalDateTime.now();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    salida.println("Hora actual: " + ahora.format(formato));
                }

                // RESOLVE
                else if (mensaje.toUpperCase().startsWith("RESOLVE ")) {
                    String expr = mensaje.substring(8).trim();

                    try {
                        double resultado = evaluarExpresion(expr);
                        salida.println("Resultado: " + resultado);
                    } catch (Exception e) {
                        salida.println("Error al resolver expresión");
                    }
                }

                // PRIVADO
                else if (mensaje.matches("C\\d+ .*")) {

                    String[] partes = mensaje.split(" ", 2);
                    String destino = partes[0];
                    String contenido = partes[1];

                    boolean encontrado = false;

                    for (ClienteHandler cliente : Servidor.clientes) {
                        if (cliente.getNombre().equalsIgnoreCase(destino)) {

                            cliente.enviarMensaje("(Privado) " + nombre + ": " + contenido);
                            salida.println("(Enviado a " + destino + ")");
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        salida.println("Error: cliente " + destino + " no existe");
                    }
                }

                // EXIT
                else if (mensaje.equalsIgnoreCase("exit")) {
                    salida.println("Desconectando...");
                    System.out.println(nombre + " desconectado");
                    Servidor.clientes.remove(this);
                    break;
                }

                else {
                    salida.println("Comando no reconocido");
                }
            }

            cerrarConexion();

        } catch (IOException e) {
            System.out.println("Error con cliente: " + e.getMessage());
        }
    }

    // 🔥 Evaluador simple (respeta prioridad)
    private double evaluarExpresion(String expr) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expr.length()) ? expr.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;

                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expr.substring(startPos, this.pos));
                }

                return x;
            }
        }.parse();
    }

    public void enviarMensaje(String msg) {
        salida.println(msg);
    }

    public void cerrarConexion() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar conexión");
        }
    }

    public String getNombre() {
        return nombre;
    }
}