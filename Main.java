public class Main {
    public static void main(String[] args) {
        // Crear un array del tipo clase padre (Persona)
        Persona[] personas = new Persona[8];

        // Llenar el array con objetos de las clases hijas
        personas[0] = new Cliente("Carlos gedeon", "15.423.111-9", "Av. Pedro de Valdivia 456");
        personas[1] = new Cliente("Francisca Reyes Altamirano", "13.660.444-8", "Calle 3 Nro. 753");
        personas[2] = new Cliente("Edgard Schumann", "10.320.198-2", "Baquedano 475");
        personas[3] = new Cliente("Luis Mella", "9.123.456-2", "Gariela Mistral 03");
        personas[4] = new Repartidor("Gonzalo Pérez", "12.876.543-2", "Camión Ford F-150");
        personas[5] = new Repartidor("Eduardo Gómez", "12.876.555-6", "Camioneta Toyota Hilux");
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
    }
}