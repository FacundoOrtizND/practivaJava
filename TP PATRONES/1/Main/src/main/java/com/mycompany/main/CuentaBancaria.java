/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author ofacu
 */
public class CuentaBancaria {

    private String titular;
    private String idCuenta;
    private double saldo;

    public CuentaBancaria(String titular, String idCuenta, double saldo) {
        this.titular = titular;
        this.idCuenta = idCuenta;
        this.saldo = saldo;
    }

    public void depositar(double monto) {
        saldo += monto;
        System.out.println("Depositado: $" + monto);
    }

    public void retirar(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            System.out.println("Retirado: $" + monto);
        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    public void mostrarDatos() {
        System.out.println("Titular: " + titular);
        System.out.println("ID Cuenta: " + idCuenta);
        System.out.println("Saldo: $" + saldo);
    }
}