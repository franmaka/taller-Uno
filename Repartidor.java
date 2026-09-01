public class Repartidor extends Persona {
    private String vehiculo;

    public Repartidor(String nombre, String rut, String vehiculo) {
        super(nombre, rut);
        this.vehiculo = vehiculo;
    }

    public void entregarMueble(String producto, String cliente) {
        System.out.println("El repartidor " + getNombre() + " va en camino a entregar '" + producto + "' a " + cliente + " en su " + vehiculo + ".");
    }

    @Override
    public String toString() {
        return super.toString() + ", Vehículo asignado: " + vehiculo;
    }
}
