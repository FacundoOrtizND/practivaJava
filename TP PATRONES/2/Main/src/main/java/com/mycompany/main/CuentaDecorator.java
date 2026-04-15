/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author ofacu
 */
public abstract class CuentaDecorator implements Cuenta {

    protected Cuenta cuenta;

    public CuentaDecorator(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}