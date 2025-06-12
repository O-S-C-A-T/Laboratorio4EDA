import java.util.*;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;

public class GeneradorPacientes {
    private List<Paciente> pacientes;
    private String[] nombres = {"Oscar", "Luis", "Claudio", "Sofia", "Valentina", "Martina", "Martin", "Manuel", "Pablo", "Alonso", "Pilar", "Consuelo", "Isidora", "Matias", "Benjamin"};
    private String[] apellidos = {"Gonzales", "Tapia", "Perez", "Cabrera", "Rojas", "Diaz", "Contreras", "Soto", "Silva", "Morales", "Sepulveda", "Torres", "Martinez", "Fuentes", "Canales"};
    private String[] areas = {"SAPU", "urgencia_adultos", "infantil"};

    public GeneradorPacientes(int n) {
        pacientes = generarPacientes(n);
    }

    public List<Paciente> generarPacientes(int n){
        List<Paciente> resultado = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Random r = new Random();
            String nombre = nombres[r.nextInt(nombres.length)];
            String apellido = apellidos[r.nextInt(apellidos.length)];
            int idCounter = r.nextInt(10000000) + 10000;
            String id = "ID-" + Integer.toString(idCounter);
            String area = areas[r.nextInt(areas.length)];
            Paciente p = new Paciente(nombre, apellido, id, area);
            resultado.add(p);
            idCounter++;
        }

        try {
            guardarTxt(resultado);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void guardarTxt(List<Paciente> pacientes) throws IOException {
        try(FileWriter fw = new FileWriter("Pacientes_24h.txt");){
            for (int i = 0; i < pacientes.size(); i++){
                Paciente paciente = pacientes.get(i);
                fw.write(String.join(",", paciente.id, paciente.nombre, paciente.apellido, String.valueOf(paciente.categoria), String.valueOf(paciente.tiempoLlegada), paciente.estado, paciente.area));
                fw.write("\n");
            }
            fw.close();
        }
    }
}
