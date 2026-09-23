public abstract class Persona {
    private String nombre;
    private String rut;

    public Persona(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    // Método abstracto que será implementado por las clases hijas
    public abstract void mostrarRol();

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", RUT: " + rut;
    }
}