package model;

import java.time.LocalDate;
import service.CSVSerializable;

public class Torneo implements CSVSerializable{

    private int id;
    private String nombre;
    private LocalDate fecha;

    public Torneo(int id, String nombre, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Torneo{" + "id=" + id + ", nombre=" + nombre + ", fecha=" + fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }
    
    
    @Override
    public String toCSV(){
        return id + "," + nombre + "," + fecha + ",";
    }
    
    @Override
    public String toHeaderCSV(){
        return "id, nombre, fecha";
    }
}
