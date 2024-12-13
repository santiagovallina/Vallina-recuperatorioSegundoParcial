package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import model.TorneoVideojuego;

public class GestorVideojuegos<T extends CSVSerializable & Comparable<T>> implements Gestionable<T> {
    
    List<T> juegos = new ArrayList<>();
    
    @Override
    public void agregar(T item){
        if(item == null){
            throw new IllegalArgumentException("Es un null.");
        }
        juegos.add(item);
    }
    
    @Override
    public void eliminar(int id){
        juegos.remove(id);
    }
    
    @Override
    public T obtener(T item){
        if(! juegos.contains(item)){
            throw new IllegalArgumentException("No esta en la lista.");
        }
        return item;
    }
    
    @Override
    public void limpiar(){
        juegos.clear();
    }
    
    @Override
    public void ordenar(){
        Collections.sort(juegos);
    }
    
    @Override
    public void ordenar(Comparator<T> comparator){
        Collections.sort(juegos, comparator);
    }
    
    @Override
    public List<T> filtrar(Predicate<T> predicate){
        List<T> toReturn = new ArrayList<>();
        for(T item : juegos){
            if(predicate.test(item)){
                toReturn.add(item);
            }
        }
        return toReturn;
    }
    
    @Override
    public void guardarEnBinario(String path){
        List<T> lista = juegos;
        File archivo = new File(path);
        try(ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))){
            salida.writeObject(lista);
        }
        catch(IOException ex){
            ex.getMessage();
        }
    }
    
    @Override
    public List<T> cargarDesdeBinario(String path) throws ClassNotFoundException{
        List<T> toReturn = new ArrayList<>();
        File archivo = new File(path);
        try(ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))){
            toReturn = (List<T>) entrada.readObject();
        }   
        catch(IOException ex){
            ex.getMessage();
        }
        return toReturn;
    }
    
    @Override
    public void guardarEnCSV(String path){
        File archivo = new File(path);
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))){
            bw.write("id, nombre, fecha, equipo principal, categoria\n");
            for(T item : juegos){
                bw.write(item.toCSV() + "\n");
            }
        }
        catch(IOException ex){
            ex.getMessage();
        }
    }
    
    @Override
    public List<TorneoVideojuego> cargarDesdeCSV(String path) throws FileNotFoundException{
        List<TorneoVideojuego> toReturn = new ArrayList<>();
        File archivo = new File(path);
        try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
            br.readLine();
            String linea;
            while((linea = br.readLine()) != null){
                if(linea.endsWith("\n")){
                    linea.substring(0, linea.length() - 1);
                }
                toReturn.add(TorneoVideojuego.fromCSV(linea));
            }
        }
        catch(IOException ex){
            ex.getMessage();
        }
        return toReturn;
    }
    
    @Override
    public void mostrarTodos(){
        for(T item : juegos){
            System.out.println(item);
        }
    }
}
