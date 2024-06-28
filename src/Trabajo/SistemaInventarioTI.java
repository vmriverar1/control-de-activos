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
