
/**
 * Representa una política de impuesto del 0 %.
 * Utilizada como ejemplo académico.
 *
 * PRINCIPIO SOLID: OCP (Abierto/Cerrado).
 * Extiende el comportamiento del sistema mediante
 * una nueva implementación de ImpuestoAplicable,
 * sin modificar las clases Mueble ni Pedido.
 *
 * PRINCIPIO SOLID: SRP (Responsabilidad Única).
 * Su única responsabilidad es determinar y calcular
 * el impuesto correspondiente a una tasa del 0 %.
 */
public class SinImpuesto implements ImpuestoAplicable {

    private static final double TASA_IMPUESTO = 0.0;

    @Override
    public double obtenerTasa() {
        return TASA_IMPUESTO;
    }

    @Override
    public double calcularImpuesto(double monto) {
        return monto * obtenerTasa();
    }
}
