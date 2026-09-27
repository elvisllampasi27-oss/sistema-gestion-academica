package pe.edu.unsch.sga.business;

/**
 * Modelo de dominio: representa la matrícula de un estudiante en un curso.
 * Capa: Lógica de Negocio.
 *
 * Relaciona las entidades Estudiante y Curso mediante sus IDs.
 */
public class Matricula {
    private int id;
    private int estudianteId;
    private int cursoId;
    private String fecha;      // Formato: YYYY-MM-DD
    private String estado;     // ACTIVA | RETIRADA | COMPLETADA

    public Matricula() {
    }

    public Matricula(int id, int estudianteId, int cursoId, String fecha, String estado) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.cursoId = cursoId;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.format("Matricula{id=%d, estudianteId=%d, cursoId=%d, fecha='%s', estado='%s'}",
                id, estudianteId, cursoId, fecha, estado);
    }
}