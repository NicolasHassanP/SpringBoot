package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class MensajeProdService implements MensajeService {

    @Override
    public void mostrarBienvenida() {
        System.out.println("--- INICIO: PERFIL DE PRODUCCIÓN ACTIVO ---");
        System.out.println("Sistema de Tareas iniciado.");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println("Fin de la ejecución.");
    }
}