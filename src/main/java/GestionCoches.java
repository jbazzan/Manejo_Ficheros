import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class GestionCoches {

    private static final String DAT_FILE = "coches.dat";
    private static final String CSV_FILE = "coches.csv";
    private static ArrayList<Coche> coches = new ArrayList<>();


    public static void mostrarMenu() {
        System.out.println("\nGestión de Almacén de Coches");
        System.out.println("1. Añadir nuevo coche");
        System.out.println("2. Borrar coche por id");
        System.out.println("3. Consulta coche por id");
        System.out.println("4. Listado de coches");
        System.out.println("5. Terminar el programa");
        System.out.println("6. Exportar coches a archivo CSV");
        System.out.print("Seleccione una opción: ");
    }

    public static void cargarDatos() {
        ObjectInputStream ois = null;
        try {
            // Abrir el archivo para lectura
            ois = new ObjectInputStream(new FileInputStream(DAT_FILE));
            coches = (ArrayList<Coche>) ois.readObject(); // Leer la lista desde el archivo
            System.out.println("Datos cargados exitosamente.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Iniciando con una lista vacía.");
            coches = new ArrayList<>(); // Inicializar lista vacía
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            coches = new ArrayList<>(); // Inicializar lista vacía en caso de error
        } catch (ClassNotFoundException e) {
            System.err.println("Error en la estructura del archivo: " + e.getMessage());
            coches = new ArrayList<>(); // Inicializar lista vacía en caso de error
        } finally {
            // Asegurar el cierre del flujo
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el archivo: " + e.getMessage());
                }
            }
        }
    }

    public static void guardarDatos() {
        ObjectOutputStream oos = null;
        try {
            // Abrir el archivo para escritura
            oos = new ObjectOutputStream(new FileOutputStream(DAT_FILE));
            oos.writeObject(coches); // Escribir la lista en el archivo
            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
        } finally {
            // Asegurarse de cerrar el recurso
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el archivo: " + e.getMessage());
                }
            }
        }
    }


    public static void anadirCoche(Scanner scanner) {
        System.out.print("Ingrese el id: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea

        // Validar si el id ya existe
        boolean idExiste = false;
        for (Coche coche : coches) {
            if (coche.getId() == id) {
                idExiste = true;
                break;
            }
        }
        if (idExiste) {
            System.out.println("Ya existe un coche con ese id.");
            return;
        }

        System.out.print("Ingrese la matrícula: ");
        String matricula = scanner.nextLine();

        // Validar si la matrícula ya existe
        boolean matriculaExiste = false;
        for (Coche coche : coches) {
            if (coche.getMatricula().equalsIgnoreCase(matricula)) {
                matriculaExiste = true;
                break;
            }
        }
        if (matriculaExiste) {
            System.out.println("Ya existe un coche con esa matrícula.");
            return;
        }

        // Pedir los demás datos
        System.out.print("Ingrese la marca: ");
        String marca = scanner.nextLine();
        System.out.print("Ingrese el modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Ingrese el color: ");
        String color = scanner.nextLine();

        // Añadir el nuevo coche a la lista
        coches.add(new Coche(id, matricula, marca, modelo, color));
        System.out.println("Coche añadido correctamente.");
    }

    public static void borrarCoche(Scanner scanner) {
        System.out.print("Ingrese el id del coche a borrar: ");
        int id = scanner.nextInt();

        boolean eliminado = false;
        for (int i = 0; i < coches.size(); i++) {
            if (coches.get(i).getId() == id) {
                coches.remove(i);
                eliminado = true;
                System.out.println("Coche eliminado correctamente.");
                break; // Detiene el bucle porque ya se eliminó el coche
            }
        }

        if (!eliminado) {
            System.out.println("No se encontró un coche con ese id.");
        }
    }

    public static void consultarCoche(Scanner scanner) {
        System.out.print("Ingrese el id del coche a consultar: ");
        int id = scanner.nextInt();

        boolean encontrado = false;
        for (Coche coche : coches) {
            if (coche.getId() == id) {
                System.out.println("Detalles del coche: " + coche);
                encontrado = true;
                break; // Termina el bucle ya que el coche se encontró
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró un coche con ese id.");
        }
    }

    public static void listarCoches() {
        if (coches.isEmpty()) {
            System.out.println("No hay coches en el almacén.");
        } else {
            for (Coche coche : coches) {
                System.out.println(coche);
            }
        }
    }

    public static void exportarACSV() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(CSV_FILE));
            pw.println("id;matricula;marca;modelo;color"); // Escribir encabezado

            for (Coche coche : coches) {
                String linea = coche.getId() + ";" + coche.getMatricula() + ";" + coche.getMarca() + ";" + coche.getModelo() + ";" + coche.getColor();
                pw.println(linea); // Escribir cada coche en formato CSV
            }

            System.out.println("Datos exportados a coches.csv exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al exportar datos: " + e.getMessage());
        } finally {
            if (pw != null) {
                pw.close(); // Asegurar el cierre del recurso
            }
        }
    }
}

