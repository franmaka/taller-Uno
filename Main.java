public class Main {
    public static void main(String[] args) {
        // Instancia de la clase hija Cliente
        Cliente cliente1 = new Cliente("Carlos Muñoz", "15.423.111-9", "Av. Pedro de Valdivia 456");
        
        // Instancia de la clase hija Repartidor
        Repartidor repartidor1 = new Repartidor("Gonzalo Pérez", "12.876.543-2", "Camión Ford F-150");

        // Demostración de toString() reusando super.toString()
        System.out.println("=== DATOS DEL CLIENTE ===");
        System.out.println(cliente1.toString());

        System.out.println("\n=== DATOS DEL REPARTIDOR ===");
        System.out.println(repartidor1.toString());

        // Invocación de métodos propios
        System.out.println("\n=== ACCIONES ===");
        cliente1.realizarPedido("Mesa Comedor de Roble");
        repartidor1.entregarMueble("Mesa Comedor de Roble", cliente1.getNombre());
    }
}