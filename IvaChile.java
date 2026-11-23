public class IvaChile implements ImpuestoAplicable {

    private static final double TASA_IVA = 0.19;

    @Override
    public double obtenerTasa() {
        return TASA_IVA;
    }

    @Override
    public double calcularImpuesto(double monto) {
        return monto * obtenerTasa();
    }
}