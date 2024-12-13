package service;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import model.TorneoVideojuego;

public interface Gestionable<T> {
    
    void agregar(T item);
    
    void eliminar(int id);
    
    T obtener(T item);
    
    void limpiar();
    
    void ordenar();
    
    void ordenar(Comparator<T> comparator);
    
    List<T> filtrar(Predicate<T> predicate);
    
    void guardarEnBinario(String path);
    
    List<T> cargarDesdeBinario(String path) throws ClassNotFoundException;
    
    void guardarEnCSV(String path);
    
    List<TorneoVideojuego> cargarDesdeCSV(String path) throws FileNotFoundException;
    
    void mostrarTodos();
    
}
