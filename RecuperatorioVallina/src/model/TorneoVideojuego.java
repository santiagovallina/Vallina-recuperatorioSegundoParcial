package model;

import java.time.LocalDate;
import service.CSVSerializable;


public class TorneoVideojuego extends Torneo implements Comparable<TorneoVideojuego>, CSVSerializable {
    
    private String equipoPrincipal;
    private CategoriaVideojuego categoria;

    public TorneoVideojuego( int id, String nombre, LocalDate fecha, String equipoPrincipal, CategoriaVideojuego categoria){
        super(id, nombre, fecha);
        this.equipoPrincipal = equipoPrincipal;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return super.toString() + "equipoPrincipal=" + equipoPrincipal + ", categoria=" + categoria + '}';
    }
    
    
    @Override
    public int compareTo(TorneoVideojuego o) {
        return this.getFecha().compareTo(o.getFecha());
    }

   @Override
    public String toCSV(){
        return super.toCSV() + equipoPrincipal + "," + categoria + ",";
    }
    
    @Override
    public String toHeaderCSV(){
        return super.toHeaderCSV() + "equipo principal, categoria";
    }
    
    
    public static TorneoVideojuego fromCSV(String linea){
        String[] values = linea.split(",");
        return new TorneoVideojuego(
                Integer.parseInt(values[0]),
                values[1],
                LocalDate.parse(values[2]),
                values[3],
                CategoriaVideojuego.valueOf(values[4])
        );
        
        
      
    }

    public CategoriaVideojuego getCategoria() {
        return categoria;
    }
    
    
  

    
    
    
    
    
    
}
