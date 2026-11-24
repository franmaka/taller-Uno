
/**
 * Servicio externo que calcula y programa el despacho.
 *
 * PRINCIPIO SOLID: SRP (Responsabilidad Única).
 * Su única responsabilidad es gestionar los despachos,
 * incluyendo el cálculo del costo de envío y su programación.
 * No calcula impuestos ni gestiona notificaciones.
 *
 * PRINCIPIO SOLID: OCP (Abierto/Cerrado).
 * Implementa la interfaz Despachable, permitiendo incorporar
 * otros servicios de despacho sin modificar Pedido.
 *
 * RELACIÓN: DEPENDENCIA — Pedido utiliza este servicio
 * temporalmente mediante parámetros de tipo Despachable.
 */
public class ServicioDespacho implements Despachable {

    private static final double COSTO_ENVIO = 5000.0;

    /**
     * Calcula el costo del envío.
     *
     * @param comuna comuna de destino.
     * @return costo del envío.
     */
    @Override
    public double calcularCostoEnvio(String comuna) {

        if (comuna == null || comuna.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Debe indicar la comuna de destino."
            );
        }

        return COSTO_ENVIO;
    }

    /**
     * Programa el despacho.
     *
     * @param fecha fecha programada para el despacho.
     */
    @Override
    public void programarDespacho(String fecha) {

        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Debe indicar la fecha del despacho."
            );
        }

        System.out.println(
            "Despacho programado para el " + fecha + "."
        );
    }
}
