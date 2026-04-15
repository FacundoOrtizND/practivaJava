/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author ofacu
 */
import java.util.List;

public interface Sujeto {
    void agregarObserver(Observer o);
    void eliminarObserver(Observer o);
    void notificarObservers(String mensaje);
}