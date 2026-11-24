/**
 * Representa a un repartidor de la mueblería.
 * Hereda los datos comunes de Persona y agrega
 * el vehículo utilizado para realizar los despachos.
 */
public class Repartidor extends Persona {

    private String vehiculo;

    public Repartidor(String nombre, String rut, String vehiculo) {
        super(nombre, rut);
        setVehiculo(vehiculo);
    }

    public String getVehiculo() {
        return vehiculo;
    }

    /**
     * Modifica el vehículo del repartidor.
     * @param vehiculo nuevo vehículo asignado.
     */
    public void setVehiculo(String vehiculo) {
        if (vehiculo == null || vehiculo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El vehículo no puede estar vacío."
            );
        }

        this.vehiculo = vehiculo.trim();
    }

    @Override
    public void mostrarRol() {
        System.out.println(
            "[ROL: REPARTIDOR] Encargado de despachos. Vehículo: "
            + vehiculo
        );
    }

    @Override
    public String toString() {
        return super.toString()
            + ", Vehículo asignado: " + vehiculo;
    }
}