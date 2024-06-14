package Trabajo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LectorUsuarioContrasena {
    // Instancia única del Singleton
    private static LectorUsuarioContrasena instancia;

    // Constructor privado para evitar la instanciación directa
    private LectorUsuarioContrasena() {}

    // Método estático para obtener la instancia única del Singleton
    public static LectorUsuarioContrasena getInstance() {
        if (instancia == null) {
            instancia = new LectorUsuarioContrasena();
        }
        return instancia;
    }

    // Método para leer el archivo de usuarios y contraseñas
    public int leerArchivoUsuarioYContraseña(String filePath, HashMap<String, String> credenciales) {
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("El archivo de ruta no puede ser nulo o vacío.");
            return -1;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    if (username.isEmpty() || password.isEmpty()) {
                        System.out.println("Línea inválida: " + line);
                        continue;
                    }
                    credenciales.put(username, password);
                } else {
                    System.out.println("Línea inválida: " + line);
                }
            }
            return 1;
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return -1;
        }
    }
}
