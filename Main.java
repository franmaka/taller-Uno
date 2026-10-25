public class Main {
    public static void main(String[] args) {
        // Crear un array del tipo clase padre (Persona)
        Persona[] personas = new Persona[8];

        // Llenar el array con objetos de las clases hijas
        personas[0] = new Cliente("Carlos gedeon", "15.423.111-9", "Av. Pedro de Valdivia 456");
        personas[1] = new Cliente("Francisca Reyes Altamirano", "22.974.985-7", "Calle 3 Nro. 753");
        personas[2] = new Cliente("Edgard Schumann", "10.320.198-2", "Baquedano 475");
        personas[3] = new Cliente("Luis Mella", "9.123.456-2", "Gariela Mistral 03");
        personas[4] = new Repartidor("Gonzalo Pérez", "12.876.543-2", "Camión Ford F-150");
        personas[5] = new Repartidor("Eduardo Serrano", "12.876.555-6", "Camioneta Toyota Hilux");
        personas[6] = new Repartidor("Samuel Reyes", "14.057.241-K", "Camión Mercedes Benz Canter 715");
        personas[7] = new Repartidor("Patricio Araya", "10.987.468-0", "Camión Mercedes Benz Canter 750");

        System.out.println("== RECOORIENDO EL ARRAY CON POLIFORMISMO ==");

        // Recorrer el array ejecutando el método polimórfismo
        for (int i = 0; i < personas.length; i++) {
            System.out.println(personas[i].toString());
        
            // Invocación polimórfismo: cada objeto ejecuta la versión de su propia clase
            personas[i].mostrarRol();
            System.out.println("-------------------------------------------------");
        }    
    
        // == PROBANDO LA CLASE MUEBLERÍA E INTERFACES ==
        System.out.println("\n=== DEMOSTRACIÓN DE MUEBLERÍA E INTERFACES ===");
        
        // Creamos un objeto Muebleria sin los nombres de los parámetros
        Muebleria miMueble = new Muebleria("Sofá 3 Cuerpos", "Madera y Tela", 189990.0);

        // Creamos el segundo mueble con los nombres de los parámetros 
        Muebleria miMesa = new Muebleria("Mesa de Comedor 6 sillas", "Madera Roble", 600000.0);

        // Mostramos la info general
        miMueble.mostrarInfo();
        
        // Probamos los métodos de las interfaces
        System.out.println("Precio final (con IVA): $" + miMueble.calcularPrecioFinal());
        
        // Probamos aplicar un descuento (sin escribir "porcentaje:")
        boolean descuentoAplicado = miMueble.aplicarDescuento(10.0); 
        if (descuentoAplicado) {
            System.out.println("¡Descuento aplicado con éxito! Nuevo precio base: $" + miMueble.getPrecio());
            System.out.println("Nuevo precio final con IVA: $" + miMueble.calcularPrecioFinal());
        }
        
        // Probamos el despacho
        System.out.println("Costo de envío: $" + miMueble.calcularCostoEnvio("Coronel"));
        miMueble.programarDespacho("2026-05-15");

        System.out.println("\n-------------------------------------------------");
        Muebleria miMueble2 = new Muebleria("Mesa de Comedor 6 Sillas", "Madera Roble", 299990.0);
        miMueble2.mostrarInfo();
        System.out.println("Precio final (con IVA): $" + miMueble2.calcularPrecioFinal());
        System.out.println("Costo de envío: $" + miMueble2.calcularCostoEnvio("Concepción"));
        miMueble2.programarDespacho("2026-05-18");


    }

}