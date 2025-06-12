import java.util.*;
import java.io.*;

public class AreaAtencion {
    private String nombre;
    private PriorityQueue<Paciente> pacientesHeap;
    private int capacidadMaxima;

    public AreaAtencion(String nombre, int capacidadMaxima) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        pacientesHeap = new PriorityQueue<>(new Comparator<Paciente>() {
            public int compare(Paciente p1, Paciente p2) {
                if(p1.categoria == p2.categoria) {
                    return Long.compare(p1.tiempoLlegada, p2.tiempoLlegada);
                }
                else if(p1.categoria != p2.categoria) {
                    return Integer.compare(p1.categoria, p2.categoria);
                }
                return 0;
            }
        });
    }

    public void ingresarPaciente(Paciente p) {
        pacientesHeap.add(p);
    }

    public Paciente atenderPaciente() {
        if(pacientesHeap.isEmpty()) {
            return null;
        }
        return pacientesHeap.poll();
    }

    public boolean estaSaturada(){
        if(pacientesHeap.size() >= capacidadMaxima) {
            return true;
        }
        return false;
    }

    public List<Paciente> obtenerPacientesPorHeapSort() {
        PriorityQueue<Paciente> copia = new PriorityQueue<>(pacientesHeap);
        List<Paciente> ordenados = new ArrayList<>();
        while (!copia.isEmpty()) {
            ordenados.add(copia.poll());
        }
        return ordenados;
    }

} 