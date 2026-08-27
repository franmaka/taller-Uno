public class Mueble {
    // Atributos
    private String tipo;      // Ej: "Sofá", "Mesa"
    private String material;  // Ej: "Madera de pino", "Melamina"
    private double precio;    // Ej: 149990.0

    // Constructor
    public Mueble(String tipo, String material, double precio) {
        this.tipo = tipo;
        this.material = material;
        this.precio = precio;
    }

    // Método para mostrar la información del mueble
    public void mostrarInfo() {
        System.out.println("=== Detalle del Mueble ===");
        System.out.println("Tipo: " + tipo);
        System.out.println("Material: " + material);
        System.out.println("Precio: $" + precio);
    }
}
    
