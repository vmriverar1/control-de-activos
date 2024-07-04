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

    public List<ActivoTI> listarActivosTI() {
        return new ArrayList<>(activosTI);
    }

    public double calcularValorInventario() {
        double valorTotal = 0.0;
        for (ActivoTI activoTI : activosTI) {
            valorTotal += activoTI.calcularValor();
        }
        return valorTotal;
    }

    public void actualizarInventario(String nombreActivo, int cantidad) {
        ActivoTI activo = buscarActivoTI(nombreActivo);
        if (activo != null) {
            try {
                activo.actualizarStock(cantidad);
                System.out.println("Cantidad actualizada exitosamente.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error al actualizar el inventario: " + e.getMessage());
            }
        } else {
            System.out.println("Activo de TI no encontrado.");
        }
    }

    public void alertasStockBajo(int umbral) {
        if (umbral < 0) {
            System.out.println("El umbral no puede ser negativo.");
            return;
        }
        System.out.println("Alertas de Stock Bajo:");
        for (ActivoTI activoTI : activosTI) {
            if (activoTI.getCantidadDisponible() < umbral) {
                System.out.println("Activo de TI: " + activoTI.getNombre() + ", Cantidad Disponible: " + activoTI.getCantidadDisponible());
            }
        }
    }
}
