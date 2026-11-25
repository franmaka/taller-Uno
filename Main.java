
/**
 * Clase principal del Sistema de Gestión de Mueblería.
 *
 * Demuestra los contenidos de la Solemne 2:
 *
 * CAPÍTULO 4: Encapsulamiento y copias defensivas.
 * CAPÍTULO 5: Abstracción, interfaces y polimorfismo.
 * CAPÍTULO 6: Composición, agregación, asociación y dependencia.
 * CAPÍTULO 7: Principios SOLID (SRP, OCP y DIP).
 */
public class Main {

    public static void main(String[] args) {

        // =============================================
        // CAPÍTULO 5: HERENCIA Y POLIMORFISMO
        // =============================================

        System.out.println("=== HERENCIA Y POLIMORFISMO ===");

        Persona[] personas = new Persona[8];

        personas[0] = new Cliente(
                "Carlos Gedeon",
                "15.423.111-9",
                "Av. Pedro de Valdivia 456");

        personas[1] = new Cliente(
                "Francisca Reyes Altamirano",
                "22.974.985-7",
                "Calle 3 Nro. 753");

        personas[2] = new Cliente(
                "Edgard Schumann",
                "10.320.198-2",
                "Baquedano 475");

        personas[3] = new Cliente(
                "Luis Mella",
                "9.123.456-2",
                "Gabriela Mistral 03");

        personas[4] = new Repartidor(
                "Gonzalo Pérez",
                "12.876.543-2",
                "Camión Ford F-150");

        personas[5] = new Repartidor(
                "Eduardo Serrano",
                "12.876.555-6",
                "Camioneta Toyota Hilux");

        personas[6] = new Repartidor(
                "Samuel Reyes",
                "14.057.241-K",
                "Camión Mercedes Benz Canter 715");

        personas[7] = new Repartidor(
                "Patricio Araya",
                "10.987.468-0",
                "Camión Mercedes Benz Canter 750");

        // Polimorfismo mediante la clase abstracta Persona.
        for (Persona persona : personas) {

            System.out.println(persona);
            persona.mostrarRol();

            System.out.println(
                    "---------------------------------------------");
        }

        // =============================================
        // CAPÍTULO 5: INTERFACES Y POLIMORFISMO
        // =============================================

        System.out.println(
                "\n=== DEMOSTRACIÓN DE MUEBLE E INTERFACES ===");

        // DIP: referencias declaradas mediante interfaces.
        ImpuestoAplicable impuesto = new IvaChile();

        // SRP (refactorización S2): la tarifa de envío vive solo en
        // ServicioDespacho; Mueble la usa por delegación.
        Despachable servicioDespacho = new ServicioDespacho();

        Mueble miMueble = new Mueble(
                "Sofá 3 Cuerpos",
                "Madera y Tela",
                189990.0,
                impuesto,
                servicioDespacho);

        miMueble.mostrarInfo();

        // Polimorfismo mediante interfaces.
        Vendible productoVendible = miMueble;
        Despachable productoDespachable = miMueble;

        System.out.printf(
                "Precio final con IVA: $%.2f%n",
                productoVendible.calcularPrecioFinal());

        // Aplicación de descuento del 10 %.
        if (productoVendible.aplicarDescuento(10.0)) {

            System.out.println("Descuento aplicado.");

            System.out.printf(
                    "Nuevo precio base: $%.2f%n",
                    productoVendible.getPrecio());

            System.out.printf(
                    "Nuevo precio final con IVA: $%.2f%n",
                    productoVendible.calcularPrecioFinal());

        } else {

            System.out.println(
                    "No fue posible aplicar el descuento.");
        }

        System.out.printf(
                "Costo de envío: $%.2f%n",
                productoDespachable.calcularCostoEnvio(
                        "Punta Arenas"));

        productoDespachable.programarDespacho("2026-11-27");

        // =============================================
        // CAPÍTULO 6: RELACIONES ENTRE CLASES
        // =============================================

        System.out.println(
                "\n=== DEMOSTRACIÓN DE RELACIONES ENTRE CLASES ===");

        // RELACIÓN: AGREGACIÓN — Pedido recibe Cliente: los clientes se
        // crean aquí, fuera de Pedido, y existen independientemente de él.

        Cliente clientePedido1 = new Cliente(
                "Francisco Fuentealba",
                "11.111.111-1",
                "Dirección de prueba 1");

        Cliente clientePedido2 = new Cliente(
                "María Rojas Soto",
                "22.222.222-2",
                "Dirección de prueba 2");

        // RELACIÓN: ASOCIACIÓN — Pedido referencia a Repartidor: el mismo
        // repartidor independiente se asigna a distintos pedidos.

        Repartidor repartidorPedido = new Repartidor(
                "Gonzalo Pérez",
                "12.876.543-2",
                "Camión Ford F-150");

        // SRP: cada servicio tiene una responsabilidad específica.
        // DIP: referencias declaradas mediante interfaces.
        // (servicioDespacho se creó arriba y se reutiliza aquí.)

        Notificable servicioNotificacion =
                new ServicioNotificacion();

        // RELACIÓN: COMPOSICIÓN — Pedido crea DetallePedido: Main solo
        // entrega los datos; el detalle se construye dentro de Pedido.

        Pedido pedido1 = new Pedido(
                1001,
                clientePedido1,
                miMueble.getTipo(),
                1,
                miMueble.getPrecio(),
                impuesto);

        Pedido pedido2 = new Pedido(
                1002,
                clientePedido2,
                "Mesa de Comedor",
                1,
                249990.0,
                impuesto);

        // ASOCIACIÓN: ambos pedidos comparten un repartidor.
        pedido1.setRepartidor(repartidorPedido);
        pedido2.setRepartidor(repartidorPedido);

        // Procesamiento del primer pedido.

        System.out.println("\n--- Procesando Pedido 1 ---");

        // RELACIÓN: DEPENDENCIA — Pedido usa Despachable y Notificable
        // solo como parámetros de confirmarDespacho(), sin almacenarlos.

        pedido1.confirmarDespacho(
                servicioDespacho,
                servicioNotificacion,
                "Punta Arenas",
                "2026-11-27");

        // Procesamiento del segundo pedido.

        System.out.println("\n--- Procesando Pedido 2 ---");

        pedido2.confirmarDespacho(
                servicioDespacho,
                servicioNotificacion,
                "Punta Arenas",
                "2026-11-28");

        // =============================================
        // CAPÍTULO 7: PRINCIPIOS SOLID
        // =============================================

        System.out.println(
                "\n=== DEMOSTRACIÓN DE PRINCIPIOS SOLID ===");

        // SRP: cada política de impuesto tiene
        // una responsabilidad específica.

        // DIP: ambas referencias dependen de la abstracción
        // ImpuestoAplicable y no de un tipo concreto.

        ImpuestoAplicable impuestoNormal = new IvaChile();
        ImpuestoAplicable impuestoCero = new SinImpuesto();

        // OCP: se incorpora una nueva política de impuesto
        // sin modificar Mueble, Pedido ni ImpuestoAplicable.

        Mueble muebleConIva = new Mueble(
                "Mesa de prueba",
                "Madera",
                100000.0,
                impuestoNormal,
                servicioDespacho);

        Mueble muebleSinIva = new Mueble(
                "Mesa de prueba",
                "Madera",
                100000.0,
                impuestoCero,
                servicioDespacho);

        System.out.printf(
                "Precio base: $%.2f%n",
                muebleConIva.getPrecio());

        System.out.printf(
                "Precio con IVA del 19%%: $%.2f%n",
                muebleConIva.calcularPrecioFinal());

        System.out.printf(
                "Precio con impuesto del 0%%: $%.2f%n",
                muebleSinIva.calcularPrecioFinal());

        System.out.println(
                "SRP: cada política calcula su propio impuesto.");

        System.out.println(
                "OCP: nueva política de impuesto incorporada");

        System.out.println(
                "sin modificar las clases Mueble ni Pedido.");

        System.out.println(
                "DIP: ambas políticas utilizan ImpuestoAplicable.");

        // =============================================
        // CAPÍTULO 4: ENCAPSULAMIENTO
        // =============================================

        System.out.println(
                "\n=== PRUEBA DE COPIAS DEFENSIVAS ===");

        // Guardamos los valores originales.
        String nombreOriginal =
                pedido1.getCliente().getNombre();

        String vehiculoOriginal =
                pedido1.getRepartidor().getVehiculo();

        // Obtenemos y modificamos una copia del cliente.
        Cliente copiaCliente = pedido1.getCliente();

        copiaCliente.setNombre("Cliente modificado");

        System.out.println(
                "Nombre de la copia: "
                + copiaCliente.getNombre());

        System.out.println(
                "Nombre original: "
                + pedido1.getCliente().getNombre());

        // Obtenemos y modificamos una copia del repartidor.
        Repartidor copiaRepartidor = pedido1.getRepartidor();

        copiaRepartidor.setVehiculo("Vehículo de prueba");

        System.out.println(
                "Vehículo de la copia: "
                + copiaRepartidor.getVehiculo());

        System.out.println(
                "Vehículo original: "
                + pedido1.getRepartidor().getVehiculo());

        // Verificación de las copias defensivas.

        boolean clienteProtegido =
                nombreOriginal.equals(
                        pedido1.getCliente().getNombre());

        boolean repartidorProtegido =
                vehiculoOriginal.equals(
                        pedido1.getRepartidor().getVehiculo());

        if (clienteProtegido && repartidorProtegido) {

            System.out.println(
                    "[OK] Las copias defensivas protegen "
                    + "los objetos originales mediante los getters.");

        } else {

            throw new IllegalStateException(
                    "Error en la protección de los objetos originales.");
        }

        // DetallePedido es inmutable, por lo tanto
        // no requiere una copia defensiva.

        System.out.println(
                "Detalle inmutable: " + pedido1.getDetalle());

        // =============================================
        // FIN DEL PROGRAMA
        // =============================================

        System.out.println("\n=== FIN DEL PROGRAMA ===");
    }
}
