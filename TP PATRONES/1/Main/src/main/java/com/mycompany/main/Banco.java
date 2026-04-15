/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author ofacu
 */
import java.util.ArrayList;
import java.util.List;

public class Banco {

    private static Banco instancia; // única instancia
    private List<CuentaBancaria> cuentas;

    // Constructor privado
    private Banco() {
        cuentas = new ArrayList<>();
    }

    // Método para obtener la instancia única
    public static Banco getInstancia() {
        if (instancia == null) {
            instancia = new Banco();
        }
        return instancia;
    }

    // Agregar cuenta
    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentas.add(cuenta);
    }

    // Mostrar cuentas
    public void mostrarCuentas() {
        for (CuentaBancaria c : cuentas) {
            c.mostrarDatos();
            System.out.println("-------------------");
        }
    }
}