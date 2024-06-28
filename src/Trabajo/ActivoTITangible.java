package Trabajo;

public class ActivoTITangible extends ActivoTI {
    public ActivoTITangible(String nombre, String descripcion, int cantidadDisponible, String numSerie, double precio) {
        super(nombre, descripcion, cantidadDisponible, numSerie, precio);
    }

    @Override
    public double calcularValor() {
        return getPrecio() * getCantidadDisponible();
    }

    @Override
    public void actualizarStock(int cantidad) {
        int cantidadDisponible = getCantidadDisponible();
        setCantidadDisponible(cantidadDisponible + cantidad);
    }
}
