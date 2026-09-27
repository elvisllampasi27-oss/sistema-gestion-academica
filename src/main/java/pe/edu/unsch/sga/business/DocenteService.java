package pe.edu.unsch.sga.business;

import pe.edu.unsch.sga.data.DocenteRepository;

import java.util.List;

/**
 * Servicio de docentes: contiene las reglas de negocio del CRUD.
 * Capa: Lógica de Negocio.
 */
public class DocenteService {
    private final DocenteRepository repository;

    public DocenteService() {
        this.repository = new DocenteRepository();
    }

    public void registrar(Docente docente) {
        validar(docente);
        List<Docente> docentes = repository.listar();
        docentes.add(docente);
        repository.guardar(docentes);
    }

    public List<Docente> listar() {
        return repository.listar();
    }

    public boolean actualizar(Docente docente) {
        validar(docente);
        List<Docente> docentes = repository.listar();
        for (Docente d : docentes) {
            if (d.getId() == docente.getId()) {
                d.setNombre(docente.getNombre());
                d.setCorreo(docente.getCorreo());
                d.setEspecialidad(docente.getEspecialidad());
                repository.guardar(docentes);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        List<Docente> docentes = repository.listar();
        boolean eliminado = docentes.removeIf(d -> d.getId() == id);
        if (eliminado) {
            repository.guardar(docentes);
        }
        return eliminado;
    }

    private void validar(Docente docente) {
        if (docente.getNombre() == null || docente.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del docente es obligatorio.");
        }
        if (docente.getCorreo() == null || !docente.getCorreo().contains("@")) {
            throw new IllegalArgumentException("El correo del docente no es válido.");
        }
        if (docente.getEspecialidad() == null || docente.getEspecialidad().isBlank()) {
            throw new IllegalArgumentException("La especialidad del docente es obligatoria.");
        }
    }
}