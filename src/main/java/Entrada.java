import java.util.Scanner;

public class Entrada {

    public static void main(String[] args) {
        GestionCoches g = new GestionCoches();
        g.cargarDatos();

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            g.mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Hace un salto de linea

            switch (opcion) {
                case 1:
                    g.anadirCoche(scanner);
                    break;
                case 2:
                    g.borrarCoche(scanner);
                    break;
                case 3:
                    g.consultarCoche(scanner);
                    break;
                case 4:
                    g.listarCoches();
                    break;
                case 5:
                    g.guardarDatos();
                    break;
                case 6:
                    g.exportarACSV();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, inténtelo de nuevo.");
            }
        } while (opcion != 5);

        scanner.close();
    }
    }


