package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    @Value("${app.nombre}")
    private String appNombre;

    @Value("${app.max-tareas}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;


    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }


    public Tarea agregarNuevaTarea(String descripcion, Prioridad prioridad) {
        // Valida que no se supere max-tareas
        if (tareaRepository.findAll().size() >= maxTareas) {
            System.err.println("ERROR: No se puede agregar la tarea. Se ha superado el límite de " + maxTareas + " tareas.");
            return null;
        }

        Tarea nuevaTarea = new Tarea(null, descripcion, false, prioridad);
        return tareaRepository.save(nuevaTarea);
    }


    public void imprimirConfiguracion() {
        System.out.println("\n--- CONFIGURACIÓN ACTUAL ---");
        System.out.println("Aplicación: " + appNombre);
        System.out.println("Límite Máximo de Tareas: " + maxTareas);
        System.out.println("Mostrar Estadísticas: " + (mostrarEstadisticas ? "Sí" : "No"));
        System.out.println("--------------------------");
    }



    public String obtenerEstadisticas() {
        if (!mostrarEstadisticas) {
            return "Estadísticas deshabilitadas por configuración.";
        }

        List<Tarea> todas = tareaRepository.findAll();
        long total = todas.size();
        long completadas = todas.stream().filter(Tarea::isCompletada).count();
        long pendientes = total - completadas;

        return String.format("Estadísticas de Tareas:\n" +
                        "  Total: %d | Completadas: %d | Pendientes: %d",
                total, completadas, pendientes);
    }


    public List<Tarea> listarTodas() {
        return tareaRepository.findAll();
    }
    public List<Tarea> listarPendientes() {
        return tareaRepository.findAll().stream()
                .filter(t -> !t.isCompletada())
                .collect(Collectors.toList());
    }
    public List<Tarea> listarCompletadas() {
        return tareaRepository.findAll().stream()
                .filter(Tarea::isCompletada)
                .collect(Collectors.toList());
    }
    public boolean marcarComoCompletada(Long id) {
        Optional<Tarea> optionalTarea = tareaRepository.findById(id);

        if (optionalTarea.isPresent()) {
            Tarea tarea = optionalTarea.get();
            tarea.setCompletada(true);
            return true;
        }
        return false;
    }
}