package com.utn.tareas.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tarea {
    private Long id; // TIPO Long (objeto)
    private String descripcion;
    private boolean completada;
    private Prioridad prioridad;


    public Long getId() {
        return id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public boolean isCompletada() {
        return completada;
    }
    public Prioridad getPrioridad() {
        return prioridad;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }


    public Tarea(Long id, String descripcion, boolean completada, Prioridad prioridad) {
        this.id = id;
        this.descripcion = descripcion;
        this.completada = completada;
        this.prioridad = prioridad;
    }
}