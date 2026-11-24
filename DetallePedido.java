
/**
 * Representa el detalle de un pedido.
 *
 * PRINCIPIO SOLID: SRP (Responsabilidad Única).
 * Su responsabilidad es representar los datos del detalle
 * del pedido y calcular su subtotal.
 *
 * RELACIÓN: COMPOSICIÓN — Pedido crea internamente este objeto
 * y mantiene su vínculo durante el ciclo de vida del pedido.
 *
 * ENCAPSULAMIENTO: clase inmutable con atributos privados
 * y finales, sin métodos setters.
 *
 * Sus atributos son inmutables porque el detalle representa
 * las condiciones del producto al momento de realizar la compra.
 */
public final class DetallePedido {

    private final String tipoMueble;
    private final int cantidad;
    private final double precioUnitario;

    public DetallePedido(String tipoMueble, int cantidad,
                         double precioUnitario) {

        if (tipoMueble == null || tipoMueble.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Debe indicar el tipo de mueble.");
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero.");
        }

        if (!Double.isFinite(precioUnitario) || precioUnitario < 0) {
            throw new IllegalArgumentException(
                    "El precio debe ser un número válido y no negativo.");
        }

        this.tipoMueble = tipoMueble.trim();
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getTipoMueble() {
        return tipoMueble;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Calcula el subtotal sin impuestos ni despacho.
     *
     * @return subtotal del detalle.
     */
    public double calcularSubtotal() {
        return cantidad * precioUnitario;
    }

    @Override
    public String toString() {
        return String.format(
                "%d x %s ($%.2f) = $%.2f",
                cantidad,
                tipoMueble,
                precioUnitario,
                calcularSubtotal());
    }
}
