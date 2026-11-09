/**
 * Parte interna de un Pedido. Se crea desde Pedido y su ciclo de vida
 * queda ligado al pedido que lo contiene (composición).
 */
public class DetallePedido {
    private final String tipoMueble;
    private final int cantidad;
    private final double precioUnitario;

    public DetallePedido(String tipoMueble, int cantidad, double precioUnitario) {
        if (tipoMueble == null || tipoMueble.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe indicar el tipo de mueble.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        if (precioUnitario < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.tipoMueble = tipoMueble;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getTipoMueble() { return tipoMueble; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }

    public double calcularSubtotal() {
        return cantidad * precioUnitario;
    }

    @Override
    public String toString() {
        return cantidad + " x " + tipoMueble + " ($" + precioUnitario
                + ") = $" + calcularSubtotal();
    }
}
