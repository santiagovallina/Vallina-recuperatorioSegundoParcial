package recuperatoriovallina;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import model.CategoriaVideojuego;
import model.TorneoVideojuego;
import service.AppConfig;
import service.GestorVideojuegos;


public class Test {

   
    public static void main(String[] args) {
            try {
                // Crear instancia del gestor
                GestorVideojuegos<TorneoVideojuego> gestor = new GestorVideojuegos<>();
                

                // Agregar torneos de videojuegos
                gestor.agregar(new TorneoVideojuego(1, "Campeonato de League of Legends",
                LocalDate.of(2024, 12, 10), "Team Alpha", CategoriaVideojuego.ONLINE));
                gestor.agregar(new TorneoVideojuego(2, "Torneo de FIFA 24", LocalDate.of(2024,
                11, 20), "FIFA Legends", CategoriaVideojuego.PRESENCIAL));
                gestor.agregar(new TorneoVideojuego(3, "Competencia de Fortnite",
                LocalDate.of(2024, 12, 5), "Battle Stars", CategoriaVideojuego.ONLINE));
                gestor.agregar(new TorneoVideojuego(4, "Campeonato de Valorant",
                LocalDate.of(2025, 1, 15), "Valorant Pros", CategoriaVideojuego.PRESENCIAL));
                

                // Mostrar torneos agregados
                System.out.println("Torneos iniciales:");
                gestor.mostrarTodos();
                

                // Filtrar torneos por categoría
                System.out.println("\nFiltrar por categoría: ONLINE");
                List<TorneoVideojuego> torneosOnline = gestor.filtrar(e -> e.getCategoria() == CategoriaVideojuego.ONLINE);
                torneosOnline.forEach(System.out::println);
                

                // Filtrar torneos por rango de fechas
                System.out.println("\nFiltrar por rango de fechas (2024-12-01 a 2024-12-31):");
                LocalDate desde = LocalDate.of(2024,12,01);
                LocalDate hasta = LocalDate.of(2024,12,31);
                List<TorneoVideojuego> rangoFechas = gestor.filtrar(
                        j1 -> j1.getFecha().isAfter(desde) && j1.getFecha().isBefore(hasta));
                rangoFechas.forEach(System.out::println);
                    

                // Ordenar torneos por nombre
                System.out.println("\nTorneos ordenados por nombre:");
                gestor.ordenar((j1, j2) -> j1.getNombre().compareTo(j2.getNombre()));
                gestor.mostrarTodos();
                

                // Ordenar torneos por fecha
                System.out.println("\nTorneos ordenados por fecha:");
                gestor.ordenar((j1, j2) -> j1.getFecha().compareTo(j2.getFecha()));
                gestor.mostrarTodos();
                

                // Guardar y cargar en archivo binario
                gestor.guardarEnBinario(AppConfig.PATH_SER);
                GestorVideojuegos<TorneoVideojuego> gestorBinario = new GestorVideojuegos<>();
                gestorBinario.cargarDesdeBinario(AppConfig.PATH_SER);
                System.out.println("\nTorneos cargados desde archivo binario:");
                gestorBinario.mostrarTodos();
                

                // Guardar y cargar en archivo CSV
                gestor.guardarEnCSV(AppConfig.PATH_CSV);
                GestorVideojuegos<TorneoVideojuego> gestorCSV = new GestorVideojuegos<>();
                gestorCSV.cargarDesdeCSV(AppConfig.PATH_CSV);
                System.out.println("\nTorneos cargados desde archivo CSV:");
                gestorCSV.mostrarTodos();
                

                // Eliminar torneo
                System.out.println("\nEliminar torneo con índice 1:");
                gestor.eliminar(1);
                gestor.mostrarTodos();
            } 
            catch (FileNotFoundException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            }
    }
}


