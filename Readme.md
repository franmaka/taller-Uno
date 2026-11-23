Sistema de Gestión de Mueblería

Integrantes

Francisco Fuentealba Flores

Victor Serrano Recabarren

Jorge Bozo Araya

Josue Gedeon

Descripción

Proyecto desarrollado en Java para aplicar conceptos de Programación Orientada a Objetos (POO) en un sistema básico de gestión de mueblería.

El proyecto demuestra los conceptos de herencia, polimorfismo, interfaces y las relaciones de composición, agregación, asociación y dependencia.

Además, incorpora una interfaz para el manejo de impuestos y una implementación del IVA correspondiente a Chile (19%).

Clases principales

Persona: Clase abstracta con los atributos comunes nombre y rut, y el método abstracto mostrarRol().

Cliente: Hereda de Persona y agrega el atributo direccionEntrega.

Repartidor: Hereda de Persona y agrega el atributo vehiculo.

Mueble: Representa un mueble y permite trabajar con el cálculo de precios e impuestos.

Vendible: Define las operaciones para calcular el precio final y aplicar descuentos.

Despachable: Define las operaciones para calcular el costo de envío y programar un despacho.

Pedido: Representa una compra. Mantiene un cliente, contiene un detalle y puede tener un repartidor asignado.

DetallePedido: Contiene el tipo de mueble, cantidad y precio unitario de un pedido.

ServicioDespacho: Implementa la interfaz Despachable y proporciona las operaciones de cálculo y programación del despacho.

ImpuestoAplicable: Interfaz que define los métodos obtenerTasa() y calcularImpuesto().

IvaChile: Clase que implementa la interfaz ImpuestoAplicable y calcula el IVA chileno utilizando una tasa del 19%.

Relaciones entre clases implementadas

1. Composición: Pedido – DetallePedido

La clase Pedido crea su propio objeto DetallePedido dentro del constructor. El detalle forma parte del pedido y su creación queda bajo la responsabilidad de esta clase. Esto representa una relación de composición.

2. Agregación: Pedido – Cliente

El objeto Cliente se crea externamente en Main y luego se entrega al constructor de Pedido. El pedido mantiene una referencia al cliente, pero este puede existir independientemente del pedido. Esto representa una relación de agregación.

3. Asociación: Pedido – Repartidor

El objeto Repartidor se crea fuera del pedido y se vincula posteriormente mediante el método asignarRepartidor(). Ambos objetos mantienen su identidad y pueden existir independientemente. Esto representa una relación de asociación.

4. Dependencia: Pedido – Despachable / ServicioDespacho

El método confirmarDespacho() recibe un objeto de tipo Despachable como parámetro y lo utiliza para calcular el costo del envío y programar la fecha. La clase Pedido no almacena este servicio como atributo, sino que lo utiliza temporalmente. Esto representa una relación de dependencia.

Manejo de impuestos

Se incorpora la interfaz ImpuestoAplicable, que establece los métodos necesarios para obtener la tasa del impuesto y calcular el impuesto correspondiente.

La clase IvaChile implementa esta interfaz y utiliza una tasa de IVA del 19%, correspondiente al IVA general de Chile.

Esto permite aplicar el concepto de interfaces y facilita la incorporación de nuevas implementaciones de impuestos.

Cómo ejecutar el proyecto

Desde la terminal, ubicada en la carpeta que contiene los archivos .java, ejecutar:

Compilar el proyecto:

javac *.java

Ejecutar el programa:

java Main

La demostración de las cuatro relaciones se encuentra al final del método main() de Main.java.

Observación

Las relaciones se demuestran mediante la creación de objetos y llamadas a métodos ejecutadas desde Main.

Los cambios del proyecto se registran en el repositorio Git del grupo mediante commits descriptivos, permitiendo mantener la trazabilidad del desarrollo.
