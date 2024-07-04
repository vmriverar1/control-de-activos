package Trabajo;

import java.util.HashMap;
import java.util.Scanner;

public class SistemaInventarioTI {
    private static HashMap<String, String> credenciales = new HashMap<>();
    private static Usuario usuarioActual = null;

    public static void main(String[] args) {
        // Ruta del archivo donde se ubican las credenciales
        String filePath = "src\\Usuarios.txt";
        LectorUsuarioContrasena reader = LectorUsuarioContrasena.getInstance();
        int resultado = reader.leerArchivoUsuarioYContraseña(filePath, credenciales);

        if (resultado == -1) {
            System.out.println("No se pudo encontrar el archivo de Usuarios. No puede continuar");
            return;
        }

        InventarioTI inventarioTI = new InventarioTI();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (usuarioActual == null) {
                autenticarUsuario(scanner);
                if (usuarioActual == null) {
                    continue;
                }
            }

            mostrarMenu(scanner, inventarioTI);
        }
    }

    private static void autenticarUsuario(Scanner scanner) {
        System.out.println("BIENVENIDO");
        System.out.println("RSS CONSULTING S.A.C.");
        System.out.println("***Sistema de Ingreso***");
        System.out.print("Nombre de Usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        if (autenticar(nombreUsuario, contrasena)) {
            System.out.println("Inicio de sesión exitoso.");
        } else {
            System.out.println("Credenciales inválidas. Inténtalo nuevamente");
        }
    }

    private static void mostrarMenu(Scanner scanner, InventarioTI inventarioTI) {
        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Registrar Activo de TI");
            System.out.println("2. Buscar Activo de TI");
            System.out.println("3. Listar Activos de TI");
            System.out.println("4. Calcular Valor del Inventario de TI");
            System.out.println("5. Actualizar Inventario de TI");
            System.out.println("6. Alertas de Stock Bajo");
            System.out.println("7. Salir");
            System.out.print("Elija una opción: ");

            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    registrarActivo(scanner, inventarioTI);
                    break;
                case 2:
                    buscarActivo(scanner, inventarioTI);
                    break;
                case 3:
                    listarActivos(inventarioTI);
                    break;
                case 4:
                    calcularValorInventario(inventarioTI);
                    break;
                case 5:
                    actualizarInventario(scanner, inventarioTI);
                    break;
                case 6:
                    alertasStockBajo(scanner, inventarioTI);
                    break;
                case 7:
                    scanner.close();
                    System.out.println("¡Gracias por usar el sistema de inventario de TI!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

    private static void registrarActivo(Scanner scanner, InventarioTI inventarioTI) {
        System.out.print("Nombre del Activo de TI: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Número de Serie: ");
        String numeroSerie = scanner.nextLine();
        int cantidad;
        double precio;
        try {
            System.out.print("Cantidad Inicial: ");
            cantidad = Integer.parseInt(scanner.nextLine());
            System.out.print("Precio: ");
            precio = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresaste un formato de número no válido para la cantidad inicial o el precio.");
            return;
        }
        ActivoTI activoTI = new ActivoTITangible(nombre, descripcion, cantidad, numeroSerie, precio);
        inventarioTI.registrarActivoTI(activoTI);
    }

    private static void buscarActivo(Scanner scanner, InventarioTI inventarioTI) {
        System.out.print("Ingrese el nombre del Activo de TI a buscar: ");
        String nombre = scanner.nextLine();
        ActivoTI activoEncontrado = inventarioTI.buscarActivoTI(nombre);
        if (activoEncontrado != null) {
            System.out.println(activoEncontrado);
        } else {
            System.out.println("Activo de TI no encontrado.");
        }
    }

    private static void listarActivos(InventarioTI inventarioTI) {
        System.out.println("---------------");
        System.out.println("Inventario de TI:");
        for (ActivoTI a : inventarioTI.listarActivosTI()) {
            System.out.println("---------------");
            System.out.println(a);
            System.out.println("---------------");
        }
    }

    private static void calcularValorInventario(InventarioTI inventarioTI) {
        System.out.println("---------------");
        double valorInventario = inventarioTI.calcularValorInventario();
        System.out.println("Valor Total del Inventario de TI: S/." + valorInventario);
    }

    private static void actualizarInventario(Scanner scanner, InventarioTI inventarioTI) {
        System.out.println("---------------");
        System.out.print("Ingrese el nombre del Activo de TI a actualizar: ");
        String nombre = scanner.nextLine();
        try {
            System.out.print("Cantidad a agregar (negativa para restar): ");
            int cantidad = Integer.parseInt(scanner.nextLine());
            inventarioTI.actualizarInventario(nombre, cantidad);
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresaste un formato de número no válido para la cantidad.");
        }
    }

    private static void alertasStockBajo(Scanner scanner, InventarioTI inventarioTI) {
        try {
            System.out.print("Ingrese el umbral para las alertas de stock bajo: ");
            int umbral = Integer.parseInt(scanner.nextLine());
            inventarioTI.alertasStockBajo(umbral);
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresaste un formato de número no válido para el umbral.");
        }
    }

    public static boolean autenticar(String nombreUsuario, String contrasena) {
        String contrasenaGuardada = credenciales.get(nombreUsuario);
        if (contrasenaGuardada != null && contrasenaGuardada.equals(contrasena)) {
            usuarioActual = new Usuario(nombreUsuario, contrasena);
            return true;
        }
        return false;
    }
}
