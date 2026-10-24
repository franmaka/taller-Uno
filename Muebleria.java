public class Muebleria implements Vendible, Despachable {

    // Constante para el IVA
    public static final double IVA = 0.19; // Impuesto al Valor Agregado (19%)

    // Atributos inmutables de la clase Muebleria (declaramos como final)
    private String tipo;      // Ej: "Sofá", "Mesa"
    private String material;  // Ej: "Madera de pino", "Melamina"
    private double precio;    // Ej: 149990.0

    // Constructor
    public Muebleria(String tipo, String material, double precio) {
        this.tipo = tipo;
        this.material = material;
        setPrecio(precio); // Usamos el setter para validar el precio
    }

    // Setters con validación (Requisito de que el precio sea positivo)
    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public String getTipo() {
        return tipo;
    }
    
    public String getMaterial() {
        return material;
    }

    // Método para mostrar la información del mueble
    public void mostrarInfo() {
        System.out.println("=== Detalle del Mueble ===");
        System.out.println("Tipo: " + tipo);
        System.out.println("Material: " + material);
        System.out.println("Precio: $" + precio);
        System.out.println("Precio con IVA: $" + (precio * (1 + IVA)));
    }

    // Implementación de la interfaz Vendible
    @Override 
    public double calcularPrecioFinal() {
        return precio * (1 + IVA);
    }

    @Override
    public boolean aplicarDescuento(double porcentaje) {
        if (porcentaje > 0 && porcentaje <= 50) {
            this.precio = this.precio * (1 - porcentaje / 100);
            return true;
        }
        return false; // Indica que el descuento no se aplicó correctamente
    }

    // Implementación de la interfaz Despachable
    @Override
    public double calcularCostoEnvio(String comuna) {
        return 5000.0; // Costo fijo de envío del mueble
    }

    @Override
    public void programarDespacho(String fecha) {
        System.out.println("Despacho del mueble(" + tipo + ") programado exitosamente para la fecha: " + fecha);
    }
}    
