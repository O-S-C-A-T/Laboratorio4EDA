import java.util.*;
import java.io.*;
import java.time.Instant;

public class Paciente {
    public String nombre;
    public String apellido;
    public String id;
    public int categoria;
    public long tiempoLlegada;
    public int tiempo;
    public String estado;
    public String area;
    public Stack<String> historialCambios;

    public Paciente(String nombre, String apellido, String id, String area) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.categoria = determinarCategoria();
        this.tiempoLlegada = tiempoUnix();
        this.tiempo = 0;
        this.estado = "en_espera";
        this.area = area;
        this.historialCambios = new Stack<>();
    }

    public long tiempoUnix(){
        Instant now = Instant.now();
        return now.getEpochSecond();
    }

    public long tiempoEsperaActual(){
        if(estado.equals("en_espera")){
            Instant now = Instant.now();
            long ahora = now.getEpochSecond();
            long diff = ahora - this.tiempoLlegada;
            return diff/60; //minutos
        }
        System.out.println("El paciente ya no se encuentra en espera.");
        return -1;
    }

    public void registrarCambio(String descripcion){
        historialCambios.push(descripcion);
    }

    public String obtenerUltimoCambio(){
        if(!historialCambios.isEmpty()){
            return historialCambios.pop();
        }
        return "no hay cambios";
    }

    public int determinarCategoria(){
        Random random = new Random();
        int categoria = random.nextInt(100) + 1;
        if(categoria > 0 && categoria <= 10){
            return 1;
        }
        else if(categoria > 10 && categoria <= 25){
            return 2;
        }
        else if(categoria > 25 && categoria <= 43){
            return 3;
        }
        else if(categoria > 43 && categoria <= 70){
            return 4;
        }
        else if(categoria > 70 && categoria <= 100){
            return 5;
        }
        return -1;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getId() {
        return id;
    }

    public int getCategoria() {
        return categoria;
    }
}