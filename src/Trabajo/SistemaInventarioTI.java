package Trabajo;

import java.util.HashMap;

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
    }
}
