public class ServicioNotificacion implements Notificable {
    @Override
    public void enviarCorreo(Cliente cliente, String mensaje) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (mensaje == null || mensaje.trim().isEmpty()) {
            throw new IllegalArgumentException("El mensaje no puede estar vacío.");
        }
        System.out.println("[NOTIFICACIÓN EMAIL] Enviado a " + cliente.getNombre() + ": " + mensaje);
    }
}