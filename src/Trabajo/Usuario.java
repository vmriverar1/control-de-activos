package Trabajo;

public class Usuario {
    private String nombreUsuario;
    private String contrasena;

    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    // Getter para nombreUsuario
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    // Getter para contrasena
    public String getContrasena() {
        return contrasena;
    }

    // Setter para nombreUsuario
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    // Setter para contrasena
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
