import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimuladorUrgencia {
    private Hospital hospital;
    private List<Paciente> pacientes;
    private int pacientesAtendidos;
    private Map<Integer, Integer> conteoPorCategoria;
    private Map<Integer, List<Integer>> tiemposPorCategoria;

    public SimuladorUrgencia(List<Paciente> pacientes) {
        hospital = new Hospital();
        this.pacientes = pacientes;
        pacientesAtendidos = 0;
        conteoPorCategoria = new HashMap<>();
        tiemposPorCategoria = new HashMap<>();
        for(int i=1; i<=5; i++){
            tiemposPorCategoria.put(i, new ArrayList<>());
        }
    }

    public void simular(int pacientesPorDia){
        int tiempoInicial = 24 * 60;
        int enEspera = 0;
        int C1 = 0, C2 = 0, C3 = 0, C4 = 0, C5 = 0;

        for(int i = 0; i <= tiempoInicial; i++){

            if(pacientes.isEmpty()){
                System.out.println("Ya no quedan pacientes por atender");
                break;
            }

            if(i % 10 == 0 && i > 0){
                Paciente paciente = pacientes.remove(0);
                hospital.registrarPaciente(paciente);
                paciente.tiempo = i;
                enEspera++;
                System.out.println("Un paciente ha entrado en espera");
            }

            if(i % 15 == 0 && i > 0){
                Paciente paciente = hospital.atenderSiguiente();
                if (paciente != null) {
                    System.out.println("Un paciente ha sido atendido");
                    int tiempoEspera = i - paciente.tiempo;
                    tiemposPorCategoria.get(paciente.categoria).add(tiempoEspera);
                    pacientesAtendidos++;
                    switch(paciente.categoria) {
                        case 1: C1++; break;
                        case 2: C2++; break;
                        case 3: C3++; break;
                        case 4: C4++; break;
                        case 5: C5++; break;
                    }
                }
            }

            if(enEspera == 3){
                for(int j = 0; j < 2; j++){
                    Paciente paciente = hospital.atenderSiguiente();
                    if (paciente != null) {
                        System.out.println("Un paciente ha sido atendido");
                        int tiempoEspera = i - paciente.tiempo;
                        tiemposPorCategoria.get(paciente.categoria).add(tiempoEspera);
                        pacientesAtendidos++;
                        switch(paciente.categoria) {
                            case 1: C1++; break;
                            case 2: C2++; break;
                            case 3: C3++; break;
                            case 4: C4++; break;
                            case 5: C5++; break;
                        }
                    }
                }
                enEspera = 0;
            }
            System.out.println("Minuto actual: " + i);

            if(i == tiempoInicial){
                System.out.println("Se ha acabado el dia");
            }

        }

        conteoPorCategoria.put(1, C1);
        conteoPorCategoria.put(2, C2);
        conteoPorCategoria.put(3, C3);
        conteoPorCategoria.put(4, C4);
        conteoPorCategoria.put(5, C5);

        System.out.println("Resultados finales: ");
        System.out.println("Pacientes ingresados totales: " + pacientesPorDia);
        System.out.println("Pacientes atendidos: " + pacientesAtendidos);
        System.out.println("Conteos por categoria: " + conteoPorCategoria);

        System.out.println("Tiempos por categoria: " + tiemposPorCategoria);


    }
}
