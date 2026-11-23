public class Mueble implements Vendible, Despachable {

    private final String tipo;
    private final String material;
    private double precio;
    private final ImpuestoAplicable impuestoAplicable;

    public Mueble(String tipo, String material, double precio,
                  ImpuestoAplicable impuestoAplicable) {

        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe indicar el tipo de mueble.");
        }

        if (material == null || material.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe indicar el material.");
        }

        if (impuestoAplicable == null) {
            throw new IllegalArgumentException("Debe proporcionar una política de impuesto.");
        }

        this.tipo = tipo;
        this.material = material;
        this.impuestoAplicable = impuestoAplicable;
        setPrecio(precio);
    }

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

    public void mostrarInfo() {
        System.out.println("=== Detalle del Mueble ===");
        System.out.println("Tipo: " + tipo);
        System.out.println("Material: " + material);
        System.out.println("Precio: $" + precio);
        System.out.println("Precio con IVA: $" +
                (precio * (1 + impuestoAplicable.obtenerTasa())));
    }

    @Override
    public double calcularPrecioFinal() {
        return precio * (1 + impuestoAplicable.obtenerTasa());
    }

    @Override
    public boolean aplicarDescuento(double porcentaje) {
        if (porcentaje > 0 && porcentaje <= 50) {
            this.precio = this.precio * (1 - porcentaje / 100);
            return true;
        }
        return false;
    }

    @Override
    public double calcularCostoEnvio(String comuna) {
        return 5000.0;
    }

    @Override
    public void programarDespacho(String fecha) {
        System.out.println("Despacho del mueble (" + tipo +
                ") programado exitosamente para la fecha: " + fecha);
    }
}