import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        GeneradorPacientes datos = new GeneradorPacientes(n);
        List<Paciente> resultado = datos.getPacientes();

        /*

        for(int i = 0; i < resultado.size(); i++){
            Paciente paciente = resultado.get(i);

            System.out.println("Datos paciente numero " + (i+1) + ": ");
            System.out.println("Nombre: " + paciente.getNombre() + " " + paciente.getApellido());
            System.out.println("Rut: " + paciente.getId());
            System.out.println("Categoria: C" + paciente.getCategoria());
            System.out.println(" ");
        } */
        SimuladorUrgencia su = new SimuladorUrgencia(resultado);
        su.simular(n);
    }
}