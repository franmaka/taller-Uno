public class Cliente extends Persona {
    private String direccionEntrega;

    public Cliente(String nombre, String rut, String direccionEntrega) {
        super(nombre, rut);
        this.direccionEntrega = direccionEntrega;
    }

    public void realizarPedido(String producto) {
        System.out.println("El cliente " + getNombre() + " ha solicitado el producto: " + producto);
    }

    @Override
    public String toString() {
        return super.toString() + ", Dirección de entrega: " + direccionEntrega;
    }
}