package pe.edu.unsch.sga.business;

/**
 * Modelo de dominio: representa la calificación de una matrícula.
 * Capa: Lógica de Negocio.
 */
public class Calificacion {
    private int id;
    private int matriculaId;
    private String tipo;      // PARCIAL | FINAL | PRACTICA
    private double nota;      // 0 - 20
    private String fecha;     // Formato: YYYY-MM-DD

    public Calificacion() {
    }

    public Calificacion(int id, int matriculaId, String tipo, double nota, String fecha) {
        this.id = id;
        this.matriculaId = matriculaId;
        this.tipo = tipo;
        this.nota = nota;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMatriculaId() { return matriculaId; }
    public void setMatriculaId(int matriculaId) { this.matriculaId = matriculaId; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    @Override
    public String toString() {
        return String.format("Calificacion{id=%d, matriculaId=%d, tipo='%s', nota=%.2f, fecha='%s'}",
                id, matriculaId, tipo, nota, fecha);
    }
}