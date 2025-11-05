package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TareaRepository {

    private final List<Tarea> tareas;
    private final AtomicLong idGenerator = new AtomicLong(0);

    public TareaRepository() {
        this.tareas = new ArrayList<>();
        this.tareas.add(new Tarea(idGenerator.incrementAndGet(), "Configurar Spring Boot", true, Prioridad.ALTA));
        this.tareas.add(new Tarea(idGenerator.incrementAndGet(), "Implementar el Repository", false, Prioridad.ALTA));
        this.tareas.add(new Tarea(idGenerator.incrementAndGet(), "Diseñar MensajeService", false, Prioridad.MEDIA));
    }

    public Tarea save(Tarea tarea) {
        if (tarea.getId() == null) {
            tarea.setId(idGenerator.incrementAndGet());
        }
        this.tareas.add(tarea);
        return tarea;
    }

    public List<Tarea> findAll() {
        return new ArrayList<>(this.tareas);
    }

    public Optional<Tarea> findById(Long id) {
        return this.tareas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    public void deleteById(Long id) {
        this.tareas.removeIf(t -> t.getId().equals(id));
    }
}