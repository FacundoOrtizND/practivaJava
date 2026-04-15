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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;

public class Servidor {

    public static void main(String[] args) {
        int puerto = 5000;

        try {
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Servidor iniciado, esperando conexión...");

            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado desde: " + cliente.getInetAddress());

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(cliente.getInputStream())
            );

            PrintWriter salida = new PrintWriter(
                    cliente.getOutputStream(), true
            );

            String mensaje;

            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Cliente: " + mensaje);

                if (mensaje.equalsIgnoreCase("exit")) {
                    salida.println("Conexión cerrada por el cliente.");
                    break;
                }

                if (mensaje.toUpperCase().startsWith("RESOLVE")) {
                    String expresion = mensaje.substring(7).trim();
                    try {
                        double resultado = evaluar(expresion);
                        salida.println("Resultado: " + resultado);
                    } catch (Exception e) {
                        salida.println("Error en la expresión.");
                    }
                } else {
                    salida.println("Servidor recibió: " + mensaje);
                }
            }

            entrada.close();
            salida.close();
            cliente.close();
            servidor.close();

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    // 🔥 Evaluador completo con prioridad y paréntesis
    public static double evaluar(String expr) {
        expr = expr.replaceAll("\\s+", ""); // eliminar espacios

        Stack<Double> valores = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            // Número (puede tener varios dígitos)
            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i++));
                }
                i--;
                valores.push(Double.parseDouble(sb.toString()));
            }

            // Paréntesis o corchetes de apertura
            else if (c == '(' || c == '[') {
                ops.push(c);
            }

            // Paréntesis o corchetes de cierre
            else if (c == ')' || c == ']') {
                while (!ops.isEmpty() && !esApertura(ops.peek())) {
                    valores.push(aplicarOp(ops.pop(), valores.pop(), valores.pop()));
                }
                ops.pop(); // sacar el '(' o '['
            }

            // Operadores
            else if (esOperador(c)) {
                while (!ops.isEmpty() && prioridad(ops.peek()) >= prioridad(c)) {
                    valores.push(aplicarOp(ops.pop(), valores.pop(), valores.pop()));
                }
                ops.push(c);
            }
        }

        while (!ops.isEmpty()) {
            valores.push(aplicarOp(ops.pop(), valores.pop(), valores.pop()));
        }

        return valores.pop();
    }

    public static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static boolean esApertura(char c) {
        return c == '(' || c == '[';
    }

    public static int prioridad(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    public static double aplicarOp(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
        }
        return 0;
    }
}