package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MensajeDevService implements MensajeService {

    @Override
    public void mostrarBienvenida() {
        System.out.println("--- INICIO: PERFIL DE DESARROLLO ACTIVO ---");
        System.out.println("¡Bienvenido/a al Sistema de Tareas de Desarrollo!");
        System.out.println("Se están mostrando logs detallados (DEBUG) y estadísticas.");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println("Adios. La sesión de desarrollo ha finalizado.");
        System.out.println("----------------------------------------------");
    }
}