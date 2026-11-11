/**
 * Clase abstracta Persona.
 * Representa un concepto general que sirve como clase base
 * para entidades concretas como Cliente y Repartidor.
 */
public abstract class Persona {

    // Atributos privados (encapsulamiento)
    private String nombre;
    private final String rut;

    // Constructor con validaciones
    public Persona(String nombre, String rut) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre no puede estar vacío."
            );
        }

        if (rut == null || rut.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El RUT no puede estar vacío."
            );
        }

        this.nombre = nombre.trim();
        this.rut = rut.trim();
    }

    // Getter del nombre
    public String getNombre() {
        return nombre;
    }

    // Getter del RUT (no tiene setter porque es final)
    public String getRut() {
        return rut;
    }

    // Setter del nombre con validación
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre no puede estar vacío."
            );
        }

        this.nombre = nombre.trim();
    }

    // Método abstracto implementado por las clases hijas
    public abstract void mostrarRol();

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", RUT: " + rut;
    }
}