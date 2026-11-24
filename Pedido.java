
/**
 * Representa un pedido realizado por un cliente en la mueblería.
 * Gestiona el detalle, el cálculo del total y la coordinación del despacho.
 *
 * PRINCIPIO SOLID: DIP (Inversión de Dependencias).
 * Depende de las interfaces ImpuestoAplicable, Despachable
 * y Notificable, en lugar de implementaciones concretas.
 *
 * PRINCIPIO SOLID: OCP (Abierto/Cerrado).
 * Permite incorporar nuevas políticas de impuestos,
 * servicios de despacho y notificación sin modificar Pedido.
 */
public class Pedido {

    private final int numero;

    // RELACIÓN: AGREGACIÓN — Pedido recibe un Cliente externo
    // que puede existir independientemente del pedido.
    private final Cliente cliente;

    // RELACIÓN: COMPOSICIÓN — Pedido crea internamente su DetallePedido
    // y mantiene su ciclo de vida dentro del pedido.
    private final DetallePedido detalle;

    // DIP: dependencia de una abstracción.
    private final ImpuestoAplicable impuestoAplicable;

    // RELACIÓN: ASOCIACIÓN — Pedido mantiene una referencia a un
    // Repartidor independiente, que puede asignarse posteriormente.
    private Repartidor repartidor;

    /**
     * Constructor del pedido con validaciones
     * e inyección de dependencia.
     */
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

        // DIP: inyección de dependencia por constructor.
        this.impuestoAplicable = impuestoAplicable;

        // COMPOSICIÓN: creación interna del detalle del pedido.
        this.detalle = new DetallePedido(
                tipoMueble, cantidad, precioUnitario);
    }

    /**
     * Modifica el repartidor asignado al pedido.
     *
     * @param repartidor nuevo repartidor.
     */
    public void setRepartidor(Repartidor repartidor) {

        if (repartidor == null) {
            throw new IllegalArgumentException(
                    "Debe indicar un repartidor.");
        }

        this.repartidor = repartidor;
    }

    /**
     * Calcula el total del pedido incluyendo el impuesto.
     *
     * DIP: utiliza la abstracción ImpuestoAplicable.
     *
     * OCP: permite cambiar la política de impuesto
     * sin modificar este método.
     *
     * @return total del pedido con impuesto.
     */
    public double calcularTotal() {

        double subtotal = detalle.calcularSubtotal();

        return subtotal
                + impuestoAplicable.calcularImpuesto(subtotal);
    }

    /**
     * Procesa el despacho utilizando servicios definidos
     * mediante interfaces.
     *
     * DIP: depende de Despachable y Notificable.
     *
     * OCP: admite nuevas implementaciones de estos servicios
     * sin modificar el código del pedido.
     */
    public void confirmarDespacho(
            Despachable servicioDespacho,
            Notificable servicioNotificacion,
            String comuna,
            String fecha) {

        // RELACIÓN: DEPENDENCIA — Pedido utiliza temporalmente
        // los servicios recibidos por parámetros, sin almacenarlos
        // como atributos de instancia.

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

        double totalConImpuesto = calcularTotal();
        double totalPagar = totalConImpuesto + costoEnvio;

        System.out.println("\n=== CONFIRMACIÓN DEL PEDIDO N° "
                + numero + " ===");

        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Detalle: " + detalle);
        System.out.println("Repartidor: " + repartidor.getNombre());

        System.out.printf("Subtotal: $%.2f%n",
                detalle.calcularSubtotal());

        System.out.printf("Total con impuesto: $%.2f%n",
                totalConImpuesto);

        System.out.printf("Costo de envío: $%.2f%n",
                costoEnvio);

        System.out.printf("Total a pagar: $%.2f%n",
                totalPagar);

        servicioDespacho.programarDespacho(fecha);

        servicioNotificacion.enviarCorreo(
                cliente,
                "Su pedido N° " + numero
                        + " ha sido procesado y programado con éxito.");
    }

    // GETTERS

    /**
     * Devuelve el número del pedido.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * ENCAPSULAMIENTO: devuelve una copia defensiva del cliente.
     *
     * Evita que se modifique el objeto original mediante
     * la referencia entregada por este getter.
     * Conserva internamente la relación de agregación.
     */
    public Cliente getCliente() {

        return new Cliente(
                cliente.getNombre(),
                cliente.getRut(),
                cliente.getDireccionEntrega());
    }

    /**
     * ENCAPSULAMIENTO: devuelve una copia defensiva del repartidor.
     *
     * Evita exponer directamente la referencia interna.
     * Conserva internamente la relación de asociación.
     *
     * @return copia del repartidor o null si no está asignado.
     */
    public Repartidor getRepartidor() {

        if (repartidor == null) {
            return null;
        }

        return new Repartidor(
                repartidor.getNombre(),
                repartidor.getRut(),
                repartidor.getVehiculo());
    }

    /**
     * ENCAPSULAMIENTO: devuelve el detalle del pedido.
     *
     * No requiere copia defensiva porque DetallePedido
     * es una clase inmutable con atributos privados y finales.
     */
    public DetallePedido getDetalle() {
        return detalle;
    }
}
