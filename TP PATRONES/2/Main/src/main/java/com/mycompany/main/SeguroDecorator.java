/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author ofacu
 */
public class SeguroDecorator extends CuentaDecorator {

    public SeguroDecorator(Cuenta cuenta) {
        super(cuenta);
    }

    @Override
    public String getDescripcion() {
        return cuenta.getDescripcion() + " + Seguro";
    }

    @Override
    public double getSaldo() {
        return cuenta.getSaldo() - 100; // costo del seguro
    }
}