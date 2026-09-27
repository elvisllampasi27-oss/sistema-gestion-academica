package pe.edu.unsch.sga.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pe.edu.unsch.sga.business.Docente;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio de docentes: gestiona la persistencia en archivo JSON.
 * Capa: Acceso a Datos.
 */
public class DocenteRepository {
    private static final String ARCHIVO = "data/docentes.json";
    private final Gson gson = new Gson();

    public List<Docente> listar() {
        try (Reader reader = new FileReader(ARCHIVO)) {
            Type tipo = new TypeToken<List<Docente>>() {}.getType();
            List<Docente> docentes = gson.fromJson(reader, tipo);
            return docentes != null ? docentes : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void guardar(List<Docente> docentes) {
        try (Writer writer = new FileWriter(ARCHIVO)) {
            gson.toJson(docentes, writer);
        } catch (Exception e) {
            System.err.println("Error al guardar docentes: " + e.getMessage());
        }
    }
}