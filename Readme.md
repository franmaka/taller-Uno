# Sistema de Gestión de Mueblería

## Integrantes del Grupo
- Integrante 1 (Francisco Fuentealba Flores)
- Integrante 2 (Victor Serrano Recabarren)
- Integrante 3 (Jorge Bozo Araya)
- Integrante 4 (Josue Gedeon)

## Descripción
Proyecto en Java enfocado en la aplicación del concepto de **Herencia** en Programación Orientada a Objetos para el sistema de una mueblería.

## Jerarquía de Clases
- **Persona (Clase Padre):** Define la estructura base con los atributos comunes (`nombre`, `rut`) y métodos compartidos como `toString()`.
- **Cliente (Clase Hija):** Hereda de `Persona` mediante `extends`. Representa al comprador, añade la propiedad de la Dirección de despacho y el método propio `realizarPedido()`.
- **`Repartidor` (Clase Hija):** Hereda de `Persona` mediante `extends`. Representa al encargado del despacho, añade el vehículo de transporte y el método propio `entregarMueble()`.

**¿Por qué esta jerarquía?**
Se aplica herencia porque tanto `Cliente` como `Repartidor` comparten atributos esenciales de una persona (nombre e identificación), evitando duplicación de código y permitiendo reutilizar constructores y métodos mediante la palabra reservada `super`.

## Abstracción y Métodos Abstractos
Se transformó la superclase `Persona` en una **clase abstracta** (`abstract class Persona`), definiendo la firma del método abstracto:
- `public abstract void mostrarRol()`

Al ser abstracto, se fuerza a que cada clase derivada proporcione obligatoriamente su propia implementación de este método de acuerdo a sus responsabilidades.

## Redefinición de Métodos (@Override)
Cada subclase redefine comportamientos clave utilizando la anotación `@Override`:
- **`Cliente`**: Implementa `mostrarRol()` imprimiendo el rol de comprador junto a su dirección de entrega. Además, extiende el método `toString()` reusando `super.toString()`.
- **`Repartidor`**: Implementa `mostrarRol()` imprimiendo la labor de despacho y el vehículo asignado. Asimismo, reescribe el método `toString()`.

## Polimorfismo
El polimorfismo se demuestra al almacenar diferentes instancias de clases hijas (`Cliente` y `Repartidor`) dentro de un único arreglo de tipo padre (`Persona[]`):
- Se recorre la estructura de datos mediante un ciclo `for`.
- En tiempo de ejecución, el sistema detecta de forma dinámica la clase real del objeto y ejecuta su respectiva versión de `mostrarRol()` y `toString()`, sin necesidad de verificar o castear el tipo explícitamente.