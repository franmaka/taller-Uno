public class Pedido {

    private final int numero;
    private final Cliente cliente;
    private final DetallePedido detalle;
    private final ImpuestoAplicable impuestoAplicable;
    private Repartidor repartidor;

    public Pedido(int numero, Cliente cliente, String tipoMueble,
                  int cantidad, double precioUnitario,
                  ImpuestoAplicable impuestoAplicable) {

        if (numero <= 0) {
            throw new IllegalArgumentException(
                    "El número de pedido debe ser positivo.");
        }

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "El pedido debe tener un cliente.");
        }

        if (impuestoAplicable == null) {
            throw new IllegalArgumentException(
                    "El pedido debe tener una política de impuesto.");
        }

        this.numero = numero;
        this.cliente = cliente;
        this.impuestoAplicable = impuestoAplicable;

        this.detalle = new DetallePedido(
                tipoMueble, cantidad, precioUnitario);
    }

    public void asignarRepartidor(Repartidor repartidor) {
        if (repartidor == null) {
            throw new IllegalArgumentException(
                    "Debe indicar un repartidor.");
        }

        this.repartidor = repartidor;
    }

    public double calcularTotal() {
        double subtotal = detalle.calcularSubtotal();

        return subtotal * (1 + impuestoAplicable.obtenerTasa());
    }

    public void confirmarDespacho(
            Despachable servicioDespacho,
            Notificable servicioNotificacion,
            String comuna,
            String fecha) {

        if (servicioDespacho == null) {
            throw new IllegalArgumentException(
                    "Debe proporcionar un servicio de despacho.");
        }

        if (servicioNotificacion == null) {
            throw new IllegalArgumentException(
                    "Debe proporcionar un servicio de notificación.");
        }

        if (repartidor == null) {
            throw new IllegalStateException(
                    "Primero debe asignar un repartidor.");
        }

        double costoEnvio =
                servicioDespacho.calcularCostoEnvio(comuna);

        System.out.println("\n=== CONFIRMACIÓN DEL PEDIDO N° "
                + numero + " ===");

        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Detalle: " + detalle);
        System.out.println("Repartidor: " + repartidor.getNombre());
        System.out.println("Subtotal: $" + detalle.calcularSubtotal());
        System.out.println("Total con IVA: $" + calcularTotal());
        System.out.println("Costo de envío: $" + costoEnvio);

        System.out.println("Total a pagar: $"
                + (calcularTotal() + costoEnvio));

        servicioDespacho.programarDespacho(fecha);

        servicioNotificacion.enviarCorreo(
                cliente,
                "Su pedido N° " + numero
                        + " ha sido procesado y programado con éxito.");
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public DetallePedido getDetalle() {
        return detalle;
    }
}