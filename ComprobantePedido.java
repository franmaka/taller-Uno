/**
 * Comprobante (resumen impreso) de un pedido confirmado.
 *
 * PRINCIPIO SOLID: SRP (Responsabilidad Única) — refactorización S2.
 * Hasta C4, Pedido.confirmarDespacho() calculaba los montos,
 * coordinaba los servicios y además daba formato a la salida por
 * consola. Eso daba a Pedido dos razones para cambiar: las reglas del
 * pedido y la forma de presentarlo. Ahora Pedido calcula y coordina,
 * y esta clase solo presenta los datos ya calculados.
 *
 * ENCAPSULAMIENTO: clase inmutable (final, atributos private final,
 * sin setters). Guarda el DetallePedido sin copia porque también es
 * inmutable.
 *
 * RELACIÓN: DEPENDENCIA — Pedido crea este comprobante como variable
 * local dentro de confirmarDespacho() y no lo guarda como atributo.
 */
public final class ComprobantePedido {

    private final int numeroPedido;
    private final String nombreCliente;
    private final String nombreRepartidor;
    private final DetallePedido detalle;
    private final double totalConImpuesto;
    private final double costoEnvio;
    private final double totalPagar;

    public ComprobantePedido(int numeroPedido, String nombreCliente,
                             String nombreRepartidor, DetallePedido detalle,
                             double totalConImpuesto, double costoEnvio,
                             double totalPagar) {

        if (detalle == null) {
            throw new IllegalArgumentException(
                    "El comprobante debe tener un detalle.");
        }

        this.numeroPedido = numeroPedido;
        this.nombreCliente = nombreCliente;
        this.nombreRepartidor = nombreRepartidor;
        this.detalle = detalle;
        this.totalConImpuesto = totalConImpuesto;
        this.costoEnvio = costoEnvio;
        this.totalPagar = totalPagar;
    }

    /**
     * Muestra el comprobante por consola.
     */
    public void imprimir() {

        System.out.println("\n=== CONFIRMACIÓN DEL PEDIDO N° "
                + numeroPedido + " ===");

        System.out.println("Cliente: " + nombreCliente);
        System.out.println("Detalle: " + detalle);
        System.out.println("Repartidor: " + nombreRepartidor);

        System.out.printf("Subtotal: $%.2f%n",
                detalle.calcularSubtotal());

        System.out.printf("Total con impuesto: $%.2f%n",
                totalConImpuesto);

        System.out.printf("Costo de envío: $%.2f%n",
                costoEnvio);

        System.out.printf("Total a pagar: $%.2f%n",
                totalPagar);
    }
}
