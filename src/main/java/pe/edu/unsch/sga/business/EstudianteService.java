package pe.edu.unsch.sga.business;

import pe.edu.unsch.sga.data.EstudianteRepository;

import java.util.List;

/**
 * Servicio de estudiantes: contiene las reglas de negocio del CRUD.
 * Capa: Lógica de Negocio.
 */
public class EstudianteService {
    private final EstudianteRepository repository;

    public EstudianteService() {
        this.repository = new EstudianteRepository();
    }

    public void registrar(Estudiante estudiante) {
        validar(estudiante);
        List<Estudiante> estudiantes = repository.listar();
        estudiantes.add(estudiante);
        repository.guardar(estudiantes);
    }

    public List<Estudiante> listar() {
        return repository.listar();
    }

    public boolean actualizar(Estudiante estudiante) {
        validar(estudiante);
        List<Estudiante> estudiantes = repository.listar();
        for (Estudiante e : estudiantes) {
            if (e.getId() == estudiante.getId()) {
                e.setNombre(estudiante.getNombre());
                e.setCorreo(estudiante.getCorreo());
                repository.guardar(estudiantes);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        List<Estudiante> estudiantes = repository.listar();
        boolean eliminado = estudiantes.removeIf(e -> e.getId() == id);
        if (eliminado) {
            repository.guardar(estudiantes);
        }
        return eliminado;
    }

    /** Reglas de negocio básicas de validación. */
    private void validar(Estudiante estudiante) {
        if (estudiante.getNombre() == null || estudiante.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del estudiante es obligatorio.");
        }
        if (estudiante.getCorreo() == null || !estudiante.getCorreo().contains("@")) {
            throw new IllegalArgumentException("El correo del estudiante no es válido.");
        }
    }
}