package pe.edu.unsch.sga.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pe.edu.unsch.sga.business.Matricula;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio de matrículas: gestiona la persistencia en archivo JSON.
 * Capa: Acceso a Datos.
 */
public class MatriculaRepository {
    private static final String ARCHIVO = "data/matriculas.json";
    private final Gson gson = new Gson();

    public List<Matricula> listar() {
        try (Reader reader = new FileReader(ARCHIVO)) {
            Type tipo = new TypeToken<List<Matricula>>() {}.getType();
            List<Matricula> matriculas = gson.fromJson(reader, tipo);
            return matriculas != null ? matriculas : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void guardar(List<Matricula> matriculas) {
        try (Writer writer = new FileWriter(ARCHIVO)) {
            gson.toJson(matriculas, writer);
        } catch (Exception e) {
            System.err.println("Error al guardar matrículas: " + e.getMessage());
        }
    }
}