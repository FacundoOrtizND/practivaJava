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

        CuentaBancaria cuenta = new CuentaBancaria("Facu", 1000);

        // Crear observers
        Observer email = new NotificadorEmail("facu@mail.com");
        Observer sms = new NotificadorSMS("2611234567");

        // Suscribir
        cuenta.agregarObserver(email);
        cuenta.agregarObserver(sms);

        // Operaciones
        cuenta.depositar(500);
        cuenta.retirar(200);
    }
}
