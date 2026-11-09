/**
 * Servicio externo que calcula y programa el despacho.
 * Pedido lo utiliza como parámetro: dependencia.
 */
public class ServicioDespacho implements Despachable {
    @Override
    public double calcularCostoEnvio(String comuna) {
        if (comuna == null || comuna.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe indicar la comuna de destino.");
        }
        return 5000.0;
    }

    @Override
    public void programarDespacho(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe indicar la fecha del despacho.");
        }
        System.out.println("Despacho programado para el " + fecha + ".");
    }
}
