
/**
 * Servicio encargado de simular el envío de notificaciones
 * a los clientes de la mueblería.
 *
 * PRINCIPIO SOLID: SRP (Responsabilidad Única).
 * Su única responsabilidad es gestionar las notificaciones.
 * No calcula impuestos, precios ni costos de despacho.
 *
 * PRINCIPIO SOLID: OCP (Abierto/Cerrado).
 * La interfaz Notificable permite incorporar otros servicios
 * de notificación sin modificar la clase Pedido.
 *
 * RELACIÓN: DEPENDENCIA — Pedido utiliza este servicio
 * temporalmente mediante un parámetro de tipo Notificable.
 */
public class ServicioNotificacion implements Notificable {

    /**
     * Simula el envío de una notificación por correo electrónico.
     *
     * @param cliente cliente destinatario.
     * @param mensaje contenido de la notificación.
     */
    @Override
    public void enviarCorreo(Cliente cliente, String mensaje) {

        if (cliente == null) {
            throw new IllegalArgumentException(
                "El cliente no puede ser nulo."
            );
        }

        if (mensaje == null || mensaje.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El mensaje no puede estar vacío."
            );
        }

        System.out.println(
            "[NOTIFICACIÓN EMAIL] Enviado a "
            + cliente.getNombre() + ": " + mensaje
        );
    }
}
