package pe.edu.unsch.sga.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pe.edu.unsch.sga.business.Estudiante;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio de estudiantes: gestiona la persistencia en archivo JSON.
 * Capa: Acceso a Datos.
 */
public class EstudianteRepository {
    private static final String ARCHIVO = "data/estudiantes.json";
    private final Gson gson = new Gson();

    public List<Estudiante> listar() {
        try (Reader reader = new FileReader(ARCHIVO)) {
            Type tipo = new TypeToken<List<Estudiante>>() {}.getType();
            List<Estudiante> estudiantes = gson.fromJson(reader, tipo);
            return estudiantes != null ? estudiantes : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void guardar(List<Estudiante> estudiantes) {
        try (Writer writer = new FileWriter(ARCHIVO)) {
            gson.toJson(estudiantes, writer);
        } catch (Exception e) {
            System.err.println("Error al guardar estudiantes: " + e.getMessage());
        }
    }
}