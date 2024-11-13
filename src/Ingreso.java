import java.util.Random;

class Ingreso {
    private static final int MAX_VALUE = 30000;

    // Método para generar un arreglo de valores aleatorios grande
    public static int[] generarValoresAleatorios(int tamanoMaximo) {
        int[] valores = new int[tamanoMaximo];
        Random random = new Random();

        for (int i = 0; i < tamanoMaximo; i++) {
            valores[i] = random.nextInt(MAX_VALUE) + 1;
        }

        return valores;
    }

    // Método para obtener un subarreglo de tamaño específico manteniendo los
    // primeros elementos
    public static int[] obtenerSubArreglo(int[] arregloGrande, int nuevoTamano) {
        int[] subArreglo = new int[nuevoTamano];
        System.arraycopy(arregloGrande, 0, subArreglo, 0, nuevoTamano);
        return subArreglo;
    }
}
