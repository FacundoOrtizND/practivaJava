/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author ofacu
 */
public class CashbackDecorator extends CuentaDecorator {

    public CashbackDecorator(Cuenta cuenta) {
        super(cuenta);
    }

    @Override
    public String getDescripcion() {
        return cuenta.getDescripcion() + " + Cashback";
    }

    @Override
    public double getSaldo() {
        return cuenta.getSaldo() + 50; // beneficio
    }
}