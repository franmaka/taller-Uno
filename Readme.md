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