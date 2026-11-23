public class Main {

    public static void main(String[] args) {

        // Herencia y polimorfismo
        Persona[] personas = new Persona[8];

        personas[0] = new Cliente("Carlos Gedeon",
                "15.423.111-9", "Av. Pedro de Valdivia 456");

        personas[1] = new Cliente("Francisca Reyes Altamirano",
                "22.974.985-7", "Calle 3 Nro. 753");

        personas[2] = new Cliente("Edgard Schumann",
                "10.320.198-2", "Baquedano 475");

        personas[3] = new Cliente("Luis Mella",
                "9.123.456-2", "Gabriela Mistral 03");

        personas[4] = new Repartidor("Gonzalo Pérez",
                "12.876.543-2", "Camión Ford F-150");

        personas[5] = new Repartidor("Eduardo Serrano",
                "12.876.555-6", "Camioneta Toyota Hilux");

        personas[6] = new Repartidor("Samuel Reyes",
                "14.057.241-K", "Camión Mercedes Benz Canter 715");

        personas[7] = new Repartidor("Patricio Araya",
                "10.987.468-0", "Camión Mercedes Benz Canter 750");

        System.out.println("=== HERENCIA Y POLIMORFISMO ===");

        for (Persona persona : personas) {
            System.out.println(persona);
            persona.mostrarRol();
            System.out.println("---------------------------------------------");
        }

        // Abstracción e inyección de dependencia del impuesto
        ImpuestoAplicable impuesto = new IvaChile();

        System.out.println("\n=== DEMOSTRACIÓN DE MUEBLE E INTERFACES ===");

        Mueble miMueble = new Mueble(
                "Sofá 3 Cuerpos",
                "Madera y Tela",
                189990.0,
                impuesto
        );

        miMueble.mostrarInfo();

        System.out.println("Precio final con IVA: $"
                + miMueble.calcularPrecioFinal());

        boolean descuentoAplicado =
                miMueble.aplicarDescuento(10.0);

        if (descuentoAplicado) {
            System.out.println("Descuento aplicado.");
            System.out.println("Nuevo precio base: $"
                    + miMueble.getPrecio());

            System.out.println("Nuevo precio final con IVA: $"
                    + miMueble.calcularPrecioFinal());
        }

        System.out.println("Costo de envío: $"
                + miMueble.calcularCostoEnvio("Coronel"));

        miMueble.programarDespacho("2026-11-10");

        // Relaciones entre clases
        System.out.println("\n=== DEMOSTRACIÓN DE RELACIONES ENTRE CLASES ===");

        // Agregación
        Cliente cliente1 = new Cliente(
                "Francisco Fuentealba",
                "15.111.222-3",
                "Av. Colón 123"
        );

        Cliente cliente2 = new Cliente(
                "María Rojas Soto",
                "18.444.555-6",
                "Calle Arturo Prat 456"
        );

        // Asociación
        Repartidor repartidorPedido = new Repartidor(
                "Gonzalo Pérez",
                "12.876.543-2",
                "Camión Ford F-150"
        );

        // Composición e inyección de impuesto
        Pedido pedido1 = new Pedido(
                1001,
                cliente1,
                miMueble.getTipo(),
                1,
                miMueble.getPrecio(),
                impuesto
        );

        Pedido pedido2 = new Pedido(
                1002,
                cliente2,
                "Mesa de Comedor",
                1,
                249990.0,
                impuesto
        );

        pedido1.asignarRepartidor(repartidorPedido);
        pedido2.asignarRepartidor(repartidorPedido);

        // Dependencias
        ServicioDespacho servicioDespacho =
                new ServicioDespacho();

        ServicioNotificacion servicioNotificacion =
                new ServicioNotificacion();

        System.out.println("\n--- Procesando Pedido 1 ---");

        pedido1.confirmarDespacho(
                servicioDespacho,
                servicioNotificacion,
                "Punta Arenas",
                "2026-11-10"
        );

        System.out.println("\n--- Procesando Pedido 2 ---");

        pedido2.confirmarDespacho(
                servicioDespacho,
                servicioNotificacion,
                "Coronel",
                "2026-11-11"
        );
    }
}