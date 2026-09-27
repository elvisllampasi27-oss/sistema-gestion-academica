package pe.edu.unsch.sga.business;

/**
 * Modelo de dominio: representa un curso del sistema.
 * Capa: Lógica de Negocio.
 */
public class Curso {
    private int id;
    private String codigo;
    private String nombre;
    private int creditos;

    public Curso() {
    }

    public Curso(int id, String codigo, String nombre, int creditos) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return String.format("Curso{id=%d, codigo='%s', nombre='%s', creditos=%d}",
                id, codigo, nombre, creditos);
    }
}