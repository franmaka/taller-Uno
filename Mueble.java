
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
 *
 * PRINCIPIO SOLID: SRP (Responsabilidad Única) — refactorización S2.
 * Su responsabilidad es representar el producto (tipo, material,
 * precio y descuentos). Hasta C4, Mueble también fijaba su propia
 * tarifa de envío (constante COSTO_ENVIO = 5000), duplicando la regla
 * que ya tenía ServicioDespacho: un cambio de tarifa obligaba a
 * modificar dos clases. Ahora Mueble sigue cumpliendo el contrato
 * Despachable, pero delega el cálculo y la programación del envío
 * en el servicio de despacho que recibe por constructor.
 */
public class Mueble implements Vendible, Despachable {

    // Constantes del sistema
    private static final double DESCUENTO_MAXIMO = 50.0;

    // Atributos privados
    private final String tipo;
    private final String material;
    private double precio;

    // DIP: dependencia de una abstracción.
    private final ImpuestoAplicable impuestoAplicable;

    // RELACIÓN: ASOCIACIÓN — Mueble mantiene un servicio Despachable
    // creado externamente; el servicio existe y puede compartirse
    // entre muchos muebles y pedidos, sin relación parte–todo.
    // DIP: se declara mediante la interfaz, no mediante ServicioDespacho.
    private final Despachable servicioDespacho;

    /**
     * Constructor del mueble con validaciones
     * e inyección de dependencias.
     *
     * @param tipo              tipo de mueble.
     * @param material          material principal.
     * @param precio            precio base sin impuesto.
     * @param impuestoAplicable política de impuesto (DIP).
     * @param servicioDespacho  servicio que calcula y programa envíos (DIP).
     */
    public Mueble(String tipo, String material, double precio,
                  ImpuestoAplicable impuestoAplicable,
                  Despachable servicioDespacho) {

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

        if (servicioDespacho == null) {
            throw new IllegalArgumentException(
                "Debe proporcionar un servicio de despacho."
            );
        }

        this.tipo = tipo.trim();
        this.material = material.trim();

        // DIP: inyección de dependencia por constructor.
        this.impuestoAplicable = impuestoAplicable;
        this.servicioDespacho = servicioDespacho;

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
     * Se declara final porque el constructor la invoca: así una
     * subclase no puede sobrescribirla y saltarse la validación.
     *
     * @param precio nuevo precio del mueble.
     */
    public final void setPrecio(double precio) {

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
     * Calcula el costo del envío del mueble.
     *
     * SRP: la tarifa no se define aquí; se delega en el
     * servicio de despacho inyectado.
     *
     * @param comuna comuna de destino.
     * @return costo del envío.
     */
    @Override
    public double calcularCostoEnvio(String comuna) {
        return servicioDespacho.calcularCostoEnvio(comuna);
    }

    /**
     * Programa el despacho del mueble.
     *
     * SRP: la programación se delega en el servicio de despacho.
     *
     * @param fecha fecha programada para el despacho.
     */
    @Override
    public void programarDespacho(String fecha) {
        System.out.print("Mueble (" + tipo + "): ");
        servicioDespacho.programarDespacho(fecha);
    }
}
