/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author ofacu
 */
public class CuentaBasica implements Cuenta {

    private double saldo;

    public CuentaBasica(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String getDescripcion() {
        return "Cuenta Básica";
    }

    @Override
    public double getSaldo() {
        return saldo;
    }
}