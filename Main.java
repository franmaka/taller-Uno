public class Main {
    public static void main(String[] args) {
        // Instancia de la clase hija Cliente
        Cliente cliente1 = new Cliente("Carlos Muñoz", "15.423.111-9", "Av. Pedro de Valdivia 456");
        Cliente cliente2 = new Cliente("Marcela Reyes", "13.660.444-K", "Calle 3 Nro. 753");
        Cliente cliente3 = new Cliente("Edgard Schumann", "10.320.198-2", "Baquedano 475");

        // Instancia de la clase hija Repartidor
        Repartidor repartidor1 = new Repartidor("Gonzalo Pérez", "12.876.543-2", "Camión Ford F-150");
        Repartidor repartidor2 = new Repartidor("Eduardo Gómez", "12.876.555-6", "Camioneta Toyota Hilux");
        Repartidor repartidor3 = new Repartidor("Samuel Reyes", "14.057.241-K", "Camión Mercedes Benz Canter 715");

        // Demostración de toString() reusando super.toString()
        System.out.println("=== DATOS DEL CLIENTE ===");
        System.out.println(cliente1.toString());
        System.out.println(cliente3.toString());

        System.out.println("\n=== DATOS DEL REPARTIDOR ===");
        System.out.println(repartidor1.toString());
        System.out.println(repartidor3.toString());

        // Invocación de métodos propios
        System.out.println("\n=== ACCIONES ===");
        cliente1.realizarPedido("Mesa Comedor de Roble");
        repartidor1.entregarMueble("Mesa Comedor de Roble", cliente1.getNombre());

        System.out.println("\n=== ACCIONES ===");
        cliente2.realizarPedido("Sofá de Pino");
        repartidor2.entregarMueble("Sofá de Pino", cliente2.getNombre());
        
        System.out.println("\n=== ACCIONES ===");
        cliente3.realizarPedido("Mesa Comedor de Rauli");
        repartidor3.entregarMueble("Mesa Comedor de Rauli", cliente3.getNombre());
    }
}
