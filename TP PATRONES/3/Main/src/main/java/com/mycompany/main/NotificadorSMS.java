/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author ofacu
 */
public class NotificadorSMS implements Observer {

    private String telefono;

    public NotificadorSMS(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println("SMS a " + telefono + ": " + mensaje);
    }
}