
/**
 * Representa un mueble comercializado por la mueblería.
 *
 * Implementa las interfaces Vendible y Despachable,
 * permitiendo calcular precios, aplicar descuentos,
 * calcular envíos y programar despachos.
 *
 * PRINCIPIO SOLID: DIP (Inversión de Dependencias).
 * Depende de la abstracción ImpuestoAplicable y no
 * directamente de una implementación concreta como IvaChile.
 *
 * PRINCIPIO SOLID: OCP (Abierto/Cerrado).
 * Permite incorporar nuevas políticas de impuestos
 * sin modificar el código de esta clase.
 */
public class Mueble implements Vendible, Despachable {

    // Constantes del sistema
    private static final double DESCUENTO_MAXIMO = 50.0;
    private static final double COSTO_ENVIO = 5000.0;

    // Atributos privados
    private final String tipo;
    private final String material;
    private double precio;

    // DIP: dependencia de una abstracción.
    private final ImpuestoAplicable impuestoAplicable;

    /**
     * Constructor del mueble con validaciones
     * e inyección de dependencia.
     */
    public Mueble(String tipo, String material, double precio,
                  ImpuestoAplicable impuestoAplicable) {

        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Debe indicar el tipo de mueble."
            );
        }

        if (material == null || material.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Debe indicar el material."
            );
        }

        if (impuestoAplicable == null) {
            throw new IllegalArgumentException(
                "Debe proporcionar una política de impuesto."
            );
        }

        this.tipo = tipo.trim();
        this.material = material.trim();

        // DIP: inyección de dependencia por constructor.
        this.impuestoAplicable = impuestoAplicable;

        setPrecio(precio);
    }

    // GETTERS

    @Override
    public double getPrecio() {
        return precio;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMaterial() {
        return material;
    }

    // SETTER CON VALIDACIÓN

    /**
     * Modifica el precio del mueble.
     *
     * @param precio nuevo precio del mueble.
     */
    public void setPrecio(double precio) {

        if (precio < 0 || Double.isNaN(precio)
                || Double.isInfinite(precio)) {
            throw new IllegalArgumentException(
                "El precio no puede ser negativo ni inválido."
            );
        }

        this.precio = precio;
    }

    // INFORMACIÓN DEL MUEBLE

    public void mostrarInfo() {

        System.out.println("=== Detalle del Mueble ===");
        System.out.println("Tipo: " + tipo);
        System.out.println("Material: " + material);

        System.out.printf("Precio: $%.2f%n", precio);

        System.out.printf("Precio con impuesto: $%.2f%n",
                calcularPrecioFinal());
    }

    // IMPLEMENTACIÓN DE LA INTERFAZ VENDIBLE

    /**
     * Calcula el precio final incluyendo el impuesto.
     *
     * OCP: permite utilizar distintas políticas de impuesto
     * sin modificar este método.
     *
     * DIP: delega el cálculo a la interfaz ImpuestoAplicable.
     */
    @Override
    public double calcularPrecioFinal() {

        return precio + impuestoAplicable.calcularImpuesto(precio);
    }

    /**
     * Aplica un descuento al precio del mueble.
     *
     * @param porcentaje porcentaje de descuento.
     * @return true si el descuento fue aplicado.
     */
    @Override
    public boolean aplicarDescuento(double porcentaje) {

        if (porcentaje > 0 && porcentaje <= DESCUENTO_MAXIMO
                && !Double.isNaN(porcentaje)
                && !Double.isInfinite(porcentaje)) {

            this.precio = this.precio * (1 - porcentaje / 100);
            return true;
        }

        return false;
    }

    // IMPLEMENTACIÓN DE LA INTERFAZ DESPACHABLE

    /**
     * Calcula el costo del envío.
     *
     * @param comuna comuna de destino.
     * @return costo del envío.
     */
    @Override
    public double calcularCostoEnvio(String comuna) {

        if (comuna == null || comuna.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Debe indicar la comuna de destino."
            );
        }

        return COSTO_ENVIO;
    }

    /**
     * Programa el despacho del mueble.
     *
     * @param fecha fecha programada para el despacho.
     */
    @Override
    public void programarDespacho(String fecha) {

        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Debe indicar una fecha de despacho."
            );
        }

        System.out.println(
            "Despacho del mueble (" + tipo
            + ") programado exitosamente para la fecha: "
            + fecha
        );
    }
}
