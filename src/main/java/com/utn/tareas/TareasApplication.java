package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

	private final TareaService tareaService;
	private final MensajeService mensajeService;


	public TareasApplication(TareaService tareaService, MensajeService mensajeService) {
		this.tareaService = tareaService;
		this.mensajeService = mensajeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TareasApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		System.out.println("----------------------------------------------");

		mensajeService.mostrarBienvenida();

		tareaService.imprimirConfiguracion();

		System.out.println("\n3. Tareas Iniciales:");
		tareaService.listarTodas().forEach(System.out::println);

		System.out.println("\n4. Intentando agregar una nueva tarea (ALTA)...");
		tareaService.agregarNuevaTarea("Revisar commits de GitHub", Prioridad.ALTA);
		tareaService.listarTodas().forEach(System.out::println);

		System.out.println("\n5. Tareas Pendientes:");
		tareaService.listarPendientes().forEach(System.out::println);

		System.out.println("\n6. Marcando Tarea ID=2 como completada...");
		if (tareaService.marcarComoCompletada(2L)) {
			System.out.println("Tarea 2 marcada exitosamente.");
		} else {
			System.out.println("Error al marcar Tarea 2.");
		}

		System.out.println("\n7. Estadísticas de Tareas:");
		System.out.println(tareaService.obtenerEstadisticas());

		System.out.println("\n8. Tareas Completadas:");
		tareaService.listarCompletadas().forEach(System.out::println);

		System.out.println("----------------------------------------------");
		mensajeService.mostrarDespedida();
	}
}