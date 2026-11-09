/**
 * Pedido reúne un detalle propio, conserva al cliente que lo realiza
 * y puede asociarse a un repartidor. Usa un servicio de despacho
 * recibido como parámetro al confirmar el envío.
 */
public class Pedido {
    private final int numero;
    private final Cliente cliente;              // Agregación
    private final DetallePedido detalle;        // Composición
    private Repartidor repartidor;              // Asociación

    public Pedido(int numero, Cliente cliente, String tipoMueble,
                  int cantidad, double precioUnitario) {
        if (numero <= 0) {
            throw new IllegalArgumentException("El número de pedido debe ser positivo.");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("El pedido debe tener un cliente.");
        }
        this.numero = numero;
        this.cliente = cliente;
        // El detalle se construye dentro del Pedido: composición.
        this.detalle = new DetallePedido(tipoMueble, cantidad, precioUnitario);
    }

    public void asignarRepartidor(Repartidor repartidor) {
        if (repartidor == null) {
            throw new IllegalArgumentException("Debe indicar un repartidor.");
        }
        this.repartidor = repartidor;
    }

    public double calcularTotal() {
        return detalle.calcularSubtotal() * (1 + Muebleria.IVA);
    }

    /**
     * Dependencia: Despachable se recibe como parámetro y no se almacena
     * como atributo del Pedido.
     */
    public void confirmarDespacho(Despachable servicio, String comuna, String fecha) {
        if (servicio == null) {
            throw new IllegalArgumentException("Debe proporcionar un servicio de despacho.");
        }
        if (repartidor == null) {
            throw new IllegalStateException("Primero debe asignar un repartidor.");
        }
        double costoEnvio = servicio.calcularCostoEnvio(comuna);
        System.out.println("\n=== CONFIRMACIÓN DEL PEDIDO N° " + numero + " ===");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Detalle: " + detalle);
        System.out.println("Repartidor: " + repartidor.getNombre());
        System.out.println("Subtotal: $" + detalle.calcularSubtotal());
        System.out.println("Total con IVA: $" + calcularTotal());
        System.out.println("Costo de envío: $" + costoEnvio);
        System.out.println("Total a pagar: $" + (calcularTotal() + costoEnvio));
        servicio.programarDespacho(fecha);
    }

    public int getNumero() { return numero; }
    public Cliente getCliente() { return cliente; }
    public Repartidor getRepartidor() { return repartidor; }
    public DetallePedido getDetalle() { return detalle; }
}
