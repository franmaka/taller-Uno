public class Cliente extends Persona {
    private String direccionEntrega;

    public Cliente(String nombre, String rut, String direccionEntrega) {
        super(nombre, rut);
        this.direccionEntrega = direccionEntrega;
    }

    @Override
    public void mostrarRol() {
        System.out.println("[ROL: CLIENTE] Comprador en la tienda. Dirección: " + direccionEntrega);
    }

    @Override
    public String toString() {
        return super.toString() + ", Dirección de entrega: " + direccionEntrega;
    }
}