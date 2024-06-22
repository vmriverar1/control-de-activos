package Trabajo;

public abstract class ActivoTI implements IActualizable {
    private String nombre;
    private String descripcion;
    private int cantidadDisponible;
    private String numSerie;
    private double precio;

    public ActivoTI(String nombre, String descripcion, int cantidadDisponible, String numSerie, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadDisponible = cantidadDisponible;
        this.numSerie = numSerie;
        this.precio = precio;
    }

    // Métodos getter y setter
    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nDescripción: " + descripcion + "\nNumero de Serie: " + numSerie
                + "\nCantidad Disponible: " + cantidadDisponible + "\nPrecio: S/." + precio;
    }

    // Métodos abstractos de la interfaz IActualizable
    @Override
    public abstract double calcularValor();

    @Override
    public abstract void actualizarStock(int cantidad);
}
