public class Repartidor extends Persona {
    private String vehiculo;

    public Repartidor(String nombre, String rut, String vehiculo) {
        super(nombre, rut);
        this.vehiculo = vehiculo;
    }
    // Implementación obligatoria con @Override y lógica propia
    @Override
    public void mostrarRol() {
        System.out.println("[ROL: REPARTIDOR] Encargado de despachos. Vehículo: " + vehiculo);
    }

    @Override
    public String toString() {
        return super.toString() + ", Vehículo asignado: " + vehiculo;
    }
}
