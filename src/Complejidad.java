import java.util.Arrays;
import java.util.Scanner;

public class Complejidad {
    private static final Scanner scanner = new Scanner(System.in);
    private static int[] valores;
    private static Ordenamiento ordenamiento;

    public static void main(String[] args) {
        ordenamiento = new Ordenamiento(false);
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1 -> generarValoresAleatorios();
                case 2 -> ejecutarMetodosOrdenamiento();
                case 3 -> ejecutarBusquedas();
                case 4 -> {
                    System.out.println("Hasta la próxima...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        } while (true);
    }

    private static void mostrarMenu() {
        System.out.println("\nMENU:");
        System.out.println("1. Generar Arreglos aleatorios con diferente tamaño");
        System.out.println("2. Ordenar por los 3 metodos");
        System.out.println("3. Buscar valores (búsqueda binaria normal y recursiva)");
        System.out.println("4. Salir");
        System.out.print("Escoja una opción: ");
    }

    private static void generarValoresAleatorios() {
        int tamanoMaximo = 30000;
        valores = Ingreso.generarValoresAleatorios(tamanoMaximo);
        System.out.println("Arreglos generados correctamente.");
    }

    private static void ejecutarMetodosOrdenamiento() {
        if (valores == null) {
            System.out.println("Primero debe generar los valores aleatorios.");
            return;
        }

        int[] tamanos = { 10, 100, 1000, 5000, 10000, 30000 };

        System.out.println("\n--- Tiempos de ejecución de métodos de ordenamiento ---");
        for (int tamano : tamanos) {
            int[] arreglo = Ingreso.obtenerSubArreglo(valores, tamano);
            System.out.printf("Tamaño del arreglo: %d\n", tamano);

            double tiempoBurbuja = medirTiempoMillis(() -> ordenamiento.burbujaConAjuste(arreglo.clone()));
            System.out.printf("Método Burbuja con Ajustes con %d valores: %.9f seg.\n", tamano, tiempoBurbuja);

            double tiempoSeleccion = medirTiempoMillis(() -> ordenamiento.seleccion(arreglo.clone()));
            System.out.printf("Método Selección con %d valores: %.9f seg.\n", tamano, tiempoSeleccion);

            double tiempoInsercion = medirTiempoMillis(() -> ordenamiento.insercion(arreglo.clone()));
            System.out.printf("Método Inserción con %d valores: %.9f seg.\n", tamano, tiempoInsercion);

            System.out.println();
        }
    }

    private static void ejecutarBusquedas() {
        if (valores == null) {
            System.out.println("Primero debe generar los valores aleatorios.");
            return;
        }

        int[] tamanos = { 10, 100, 1000, 5000, 10000, 30000 };
        int[] posiciones = { 9, 98, 957, 4000, 9876, 29475 };

        System.out.println("\n--- Tiempos de ejecución de búsquedas binarias ---");
        for (int i = 0; i < tamanos.length; i++) {
            int[] arreglo = Ingreso.obtenerSubArreglo(valores, tamanos[i]);
            Arrays.sort(arreglo);
            int valor = arreglo[posiciones[i]];

            double tiempoBinariaNormal = medirTiempoNano(() -> Busqueda.busquedaBinariaNormal(arreglo, valor));
            System.out.printf("Búsqueda Binaria Normal con %d valores en posición %d: %.9f seg.\n", tamanos[i],
                    posiciones[i], tiempoBinariaNormal);

            double tiempoBinariaRecursiva = medirTiempoNano(
                    () -> Busqueda.busquedaBinariaRecursiva(arreglo, valor, 0, arreglo.length - 1));
            System.out.printf("Búsqueda Binaria Recursiva con %d valores en posición %d: %.9f seg.\n", tamanos[i],
                    posiciones[i], tiempoBinariaRecursiva);

            System.out.println();
        }
    }

    // Método de medición usando System.currentTimeMillis() para ordenamientos
    private static double medirTiempoMillis(Runnable algoritmo) {
        long inicio = System.currentTimeMillis();
        algoritmo.run();
        long fin = System.currentTimeMillis();
        return (fin - inicio) / 1000.0;
    }

    // Método de medición usando System.nanoTime() para búsquedas binarias
    private static double medirTiempoNano(Runnable algoritmo) {
        long inicio = System.nanoTime();
        algoritmo.run();
        long fin = System.nanoTime();
        return (fin - inicio) / 1_000_000_000.0; // Convertir nanosegundos a segundos
    }
}
