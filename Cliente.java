/**
 * Representa a un cliente de la mueblería.
 * Hereda los datos comunes de Persona y agrega
 * la dirección donde se entregarán los muebles.
 */
public class Cliente extends Persona {

    private String direccionEntrega;

    /**
     * Constructor del cliente.
     * Valida que la dirección de entrega no esté vacía.
     */
    public Cliente(String nombre, String rut, String direccionEntrega) {
        super(nombre, rut);
        setDireccionEntrega(direccionEntrega);
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    /**
     * Modifica la dirección de entrega.
     * @param direccionEntrega nueva dirección del cliente.
     */
    public void setDireccionEntrega(String direccionEntrega) {
        if (direccionEntrega == null || direccionEntrega.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "La dirección de entrega no puede estar vacía."
            );
        }

        this.direccionEntrega = direccionEntrega.trim();
    }

    @Override
    public void mostrarRol() {
        System.out.println(
            "[ROL: CLIENTE] Comprador en la tienda. Dirección: "
            + direccionEntrega
        );
    }

    @Override
    public String toString() {
        return super.toString()
            + ", Dirección de entrega: " + direccionEntrega;
    }
}