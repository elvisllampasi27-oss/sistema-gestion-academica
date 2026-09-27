package pe.edu.unsch.sga.business;

import pe.edu.unsch.sga.data.CursoRepository;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Servicio de cursos: contiene las reglas de negocio del CRUD.
 * Capa: Lógica de Negocio.
 */
public class CursoService {
    private static final Pattern PATRON_CODIGO = Pattern.compile("^[A-Z]{2,4}-\\d{3}$");

    private final CursoRepository repository;

    public CursoService() {
        this.repository = new CursoRepository();
    }

    public void registrar(Curso curso) {
        validar(curso);
        List<Curso> cursos = repository.listar();
        cursos.add(curso);
        repository.guardar(cursos);
    }

    public List<Curso> listar() {
        return repository.listar();
    }

    public boolean actualizar(Curso curso) {
        validar(curso);
        List<Curso> cursos = repository.listar();
        for (Curso c : cursos) {
            if (c.getId() == curso.getId()) {
                c.setCodigo(curso.getCodigo());
                c.setNombre(curso.getNombre());
                c.setCreditos(curso.getCreditos());
                repository.guardar(cursos);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        List<Curso> cursos = repository.listar();
        boolean eliminado = cursos.removeIf(c -> c.getId() == id);
        if (eliminado) {
            repository.guardar(cursos);
        }
        return eliminado;
    }

    private void validar(Curso curso) {
        if (curso.getCodigo() == null || !PATRON_CODIGO.matcher(curso.getCodigo()).matches()) {
            throw new IllegalArgumentException(
                    "El código del curso debe tener el formato XXX-### (ej: IS-388).");
        }
        if (curso.getNombre() == null || curso.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del curso es obligatorio.");
        }
        if (curso.getCreditos() < 1 || curso.getCreditos() > 10) {
            throw new IllegalArgumentException("Los créditos deben estar entre 1 y 10.");
        }
    }
}