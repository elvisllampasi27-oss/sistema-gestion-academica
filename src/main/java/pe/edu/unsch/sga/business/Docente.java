package pe.edu.unsch.sga.business;

/**
 * Modelo de dominio: representa a un docente del sistema.
 * Capa: Lógica de Negocio.
 */
public class Docente {
    private int id;
    private String nombre;
    private String correo;
    private String especialidad;

    public Docente() {
    }

    public Docente(int id, String nombre, String correo, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.especialidad = especialidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return String.format("Docente{id=%d, nombre='%s', correo='%s', especialidad='%s'}",
                id, nombre, correo, especialidad);
    }
}