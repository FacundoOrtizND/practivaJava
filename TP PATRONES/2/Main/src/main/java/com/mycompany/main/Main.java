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

        // Cuenta básica
        Cuenta cuenta = new CuentaBasica(1000);
        System.out.println(cuenta.getDescripcion());
        System.out.println("Saldo: $" + cuenta.getSaldo());

        System.out.println("---------------");

        // Cuenta con seguro
        cuenta = new SeguroDecorator(cuenta);
        System.out.println(cuenta.getDescripcion());
        System.out.println("Saldo: $" + cuenta.getSaldo());

        System.out.println("---------------");

        // Cuenta con seguro + cashback
        cuenta = new CashbackDecorator(cuenta);
        System.out.println(cuenta.getDescripcion());
        System.out.println("Saldo: $" + cuenta.getSaldo());
    }
}