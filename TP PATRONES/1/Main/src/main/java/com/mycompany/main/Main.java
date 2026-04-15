/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

/**
 *
 * @author ofacu
 */
public class Main {

    public static void main(String[] args) {

        // Obtener la única instancia del banco
        Banco banco = Banco.getInstancia();

        // Crear cuentas
        CuentaBancaria c1 = new CuentaBancaria("Juan", "001", 1000);
        CuentaBancaria c2 = new CuentaBancaria("Ana", "002", 2000);

        // Operaciones
        c1.depositar(500);
        c2.retirar(300);

        // Agregar al banco
        banco.agregarCuenta(c1);
        banco.agregarCuenta(c2);

        // Mostrar todas las cuentas
        banco.mostrarCuentas();
    }
}