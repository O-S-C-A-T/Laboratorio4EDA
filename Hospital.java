import java.util.*;
import java.io.*;

public class Hospital {
    private Map<String, Paciente> pacientesTotales;
    private PriorityQueue<Paciente> colaAtencion;
    private Map<String, AreaAtencion> areasAtencion;
    private List<Paciente> pacientesAtendidos;

    public Hospital() {
        pacientesTotales = new HashMap<String, Paciente>();
        colaAtencion = new PriorityQueue<>(
                (Paciente p1, Paciente p2) -> Integer.compare(p1.categoria, p2.categoria)
        );
        areasAtencion = new HashMap<>();
        pacientesAtendidos = new ArrayList<>();

        areasAtencion.put("SAPU", new AreaAtencion("Sapu", 200));
        areasAtencion.put("urgencia_adultos", new AreaAtencion("urgencia_adultos", 200));
        areasAtencion.put("infantil", new AreaAtencion("infantil", 200));
    }

    public void registrarPaciente(Paciente p) {
        if(p != null) {
            colaAtencion.add(p);
            pacientesTotales.put(p.id, p);
            p.registrarCambio("El paciente ingreso a espera");
        }
    }

    public void reasignarCategoria(String id, int nuevaCategoria) {
        if(pacientesTotales.containsKey(id)) {
            Paciente paciente = pacientesTotales.get(id);
            paciente.registrarCambio("Se cambio de categoria de C" + paciente.categoria + " a C" + nuevaCategoria);
            paciente.categoria = nuevaCategoria;
        }
    }

    public Paciente atenderSiguiente(){
        if(!colaAtencion.isEmpty()) {
            Paciente paciente = colaAtencion.poll();
            paciente.estado = "en_atencion";
            paciente.registrarCambio("El paciente ingreso a atencion");
            pacientesAtendidos.add(paciente);

            AreaAtencion area = areasAtencion.get(paciente.area);
            area.ingresarPaciente(paciente);
            return paciente;
        }
        return null;
    }

    public List<Paciente> obtenerPacientesPorCategoria(int categoria) {
        List<Paciente> pacientes = new ArrayList<>();
        for(int i = 0; i < pacientesTotales.size(); i++) {
            Paciente paciente = pacientesTotales.get(i);
            if(paciente.categoria == categoria) {
                pacientes.add(paciente);
            }
        }
        return pacientes;
    }

    public AreaAtencion obtenerAreaAtencion(String area) {
        return areasAtencion.get(area);
    }
}
