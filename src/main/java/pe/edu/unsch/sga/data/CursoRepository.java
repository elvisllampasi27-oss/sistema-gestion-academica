package pe.edu.unsch.sga.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pe.edu.unsch.sga.business.Curso;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio de cursos: gestiona la persistencia en archivo JSON.
 * Capa: Acceso a Datos.
 */
public class CursoRepository {
    private static final String ARCHIVO = "data/cursos.json";
    private final Gson gson = new Gson();

    public List<Curso> listar() {
        try (Reader reader = new FileReader(ARCHIVO)) {
            Type tipo = new TypeToken<List<Curso>>() {}.getType();
            List<Curso> cursos = gson.fromJson(reader, tipo);
            return cursos != null ? cursos : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void guardar(List<Curso> cursos) {
        try (Writer writer = new FileWriter(ARCHIVO)) {
            gson.toJson(cursos, writer);
        } catch (Exception e) {
            System.err.println("Error al guardar cursos: " + e.getMessage());
        }
    }
}