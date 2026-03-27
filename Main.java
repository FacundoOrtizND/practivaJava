import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        
        int suma = 0;
        int cantidad = 500;

        System.out.println("Generando números...\n");

        for (int i = 1; i <= cantidad; i++) {
            int numero = random.nextInt(991) + 10;

            suma += numero;

            System.out.println("Número " + i + ": " + numero + 
                               " | Suma parcial: " + suma);
        }

        double promedio = (double) suma / cantidad;

        System.out.println("\n--- RESULTADOS FINALES ---");
        System.out.println("Cantidad de números: " + cantidad);
        System.out.println("Suma total: " + suma);
        System.out.println("Promedio: " + promedio);
    }
}