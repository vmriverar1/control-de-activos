package Trabajo;
import java.util.ArrayList;
import java.util.List;

public class InventarioTI {
    private List<ActivoTI> activosTI = new ArrayList<>();

    public void registrarActivoTI(ActivoTI activoTI) {
        if (activoTI != null) {
            activosTI.add(activoTI);
            System.out.println("Activo de TI registrado exitosamente.");
        } else {
            System.out.println("El activo no puede ser nulo.");
        }
    }

    public ActivoTI buscarActivoTI(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return null;
        }
        for (ActivoTI activoTI : activosTI) {
            if (activoTI.getNombre().equalsIgnoreCase(nombre)) {
                return activoTI;
            }
        }
        System.out.println("Activo de TI no encontrado.");
        return null;
    }
}