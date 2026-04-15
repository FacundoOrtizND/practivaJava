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

public class CuentaBancaria implements Sujeto {

    private String titular;
    private double saldo;
    private List<Observer> observers;

    public CuentaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
        observers = new ArrayList<>();
    }

    @Override
    public void agregarObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void eliminarObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notificarObservers(String mensaje) {
        for (Observer o : observers) {
            o.actualizar(mensaje);
        }
    }

    public void depositar(double monto) {
        saldo += monto;
        notificarObservers("Se depositaron $" + monto + ". Saldo actual: $" + saldo);
    }

    public void retirar(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            notificarObservers("Se retiraron $" + monto + ". Saldo actual: $" + saldo);
        } else {
            System.out.println("Saldo insuficiente");
        }
    }
}