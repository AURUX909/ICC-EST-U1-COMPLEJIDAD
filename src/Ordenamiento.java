class Ordenamiento {
    private final boolean mostrarComparaciones;

    public Ordenamiento(boolean mostrarComparaciones) {
        this.mostrarComparaciones = mostrarComparaciones;
    }

    public void burbujaConAjuste(int[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            boolean intercambiado = false;
            for (int j = 1; j < arreglo.length - i; j++) {
                if (arreglo[j - 1] > arreglo[j]) {
                    int temp = arreglo[j - 1];
                    arreglo[j - 1] = arreglo[j];
                    arreglo[j] = temp;
                    intercambiado = true;
                }
            }
            if (!intercambiado) {
                break;
            }
        }
    }

    public void seleccion(int[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < arreglo.length; j++) {
                if (arreglo[j] < arreglo[indiceMenor]) {
                    indiceMenor = j;
                }
            }
            int temp = arreglo[indiceMenor];
            arreglo[indiceMenor] = arreglo[i];
            arreglo[i] = temp;
        }
    }

    public void insercion(int[] arreglo) {
        for (int i = 1; i < arreglo.length; i++) {
            int actual = arreglo[i];
            int j = i - 1;
            while (j >= 0 && arreglo[j] > actual) {
                arreglo[j + 1] = arreglo[j];
                j--;
            }
            arreglo[j + 1] = actual;
        }
    }
}