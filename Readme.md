
# Sistema de Gestión de Mueblería

## Integrantes

- Francisco Fuentealba Flores
- Victor Serrano Recabarren
- Jorge Bozo Araya
- Josue Gedeon

## Descripción del proyecto

Proyecto desarrollado en Java para aplicar los conceptos de
Programación Orientada a Objetos (POO) en un sistema básico
de gestión de mueblería.

El sistema permite representar clientes, repartidores, muebles
y pedidos, calcular precios, aplicar descuentos e impuestos,
programar despachos y simular notificaciones a los clientes.

Esta versión corresponde a la Solemne 2 e incorpora mejoras
respecto de la Cátedra 4, considerando los siguientes contenidos:

- Capítulo 4: Encapsulamiento.
- Capítulo 5: Abstracción, interfaces y polimorfismo.
- Capítulo 6: Relaciones entre clases.
- Capítulo 7: Principios SOLID (SRP, OCP y DIP).

## Objetivo

Desarrollar un sistema orientado a objetos que permita aplicar
buenas prácticas de diseño, proteger el estado interno de los
objetos, reducir el acoplamiento entre clases y facilitar la
incorporación de nuevas funcionalidades.

## Estructura del proyecto

| Archivo | Responsabilidad |
|---|---|
| Main.java | Ejecuta las demostraciones y pruebas del sistema. |
| Persona.java | Clase abstracta que representa los datos comunes de las personas. |
| Cliente.java | Representa un cliente y su dirección de entrega. |
| Repartidor.java | Representa un repartidor y su vehículo asignado. |
| Mueble.java | Representa un producto y sus operaciones comerciales y de despacho. |
| Vendible.java | Define las operaciones de venta, precios y descuentos. |
| Despachable.java | Define las operaciones de cálculo y programación de despachos. |
| Pedido.java | Representa una compra y coordina su procesamiento. |
| DetallePedido.java | Representa los datos inmutables del detalle y calcula su subtotal. |
| ServicioDespacho.java | Calcula el costo de envío y programa despachos. |
| Notificable.java | Define el contrato para notificar a los clientes. |
| ServicioNotificacion.java | Simula el envío de notificaciones por correo electrónico. |
| ImpuestoAplicable.java | Define el contrato para calcular impuestos. |
| IvaChile.java | Implementa una política de impuesto del 19 %. |
| SinImpuesto.java | Implementa una política de impuesto del 0 % con fines académicos. |

## Capítulo 4: Encapsulamiento

El sistema protege el estado interno de los objetos mediante
atributos privados, validaciones, atributos finales, constantes
y copias defensivas.

### Atributos privados

Los atributos de instancia de las clases del dominio se
declaran mediante el modificador private.

El acceso y la modificación de estos atributos se realizan
mediante métodos públicos controlados.

### Setters con validación

El proyecto incorpora cinco setters validados:

| Clase | Método | Validación |
|---|---|---|
| Persona | setNombre() | El nombre no puede ser nulo ni estar vacío. |
| Cliente | setDireccionEntrega() | La dirección no puede ser nula ni estar vacía. |
| Repartidor | setVehiculo() | El vehículo no puede ser nulo ni estar vacío. |
| Mueble | setPrecio() | El precio debe ser finito y no negativo. |
| Pedido | setRepartidor() | El repartidor no puede ser nulo. |

Estas validaciones evitan que los objetos adquieran estados
inválidos mediante sus métodos de modificación.

### Atributos de instancia final

Se utilizan atributos final para impedir la reasignación
de valores o referencias después de su inicialización.

Algunos ejemplos son:

- Persona: rut.
- Mueble: tipo, material e impuestoAplicable.
- Pedido: numero, cliente, detalle e impuestoAplicable.
- DetallePedido: tipoMueble, cantidad y precioUnitario.

Una referencia final no convierte automáticamente al objeto
referenciado en inmutable.

### Constantes de clase

Se utilizan constantes private static final para representar
valores que no deben modificarse durante la ejecución.

| Clase | Constante | Valor |
|---|---|---|
| Mueble | DESCUENTO_MAXIMO | 50.0 |
| Mueble | COSTO_ENVIO | 5000.0 |
| ServicioDespacho | COSTO_ENVIO | 5000.0 |
| IvaChile | TASA_IVA | 0.19 |
| SinImpuesto | TASA_IMPUESTO | 0.0 |

### Copias defensivas e inmutabilidad

La clase Pedido protege sus referencias internas mediante
copias defensivas en los siguientes métodos:

- getCliente(): devuelve una nueva instancia de Cliente.
- getRepartidor(): devuelve una nueva instancia de Repartidor
  cuando existe un repartidor asignado.

Esto evita que los objetos originales sean modificados
directamente mediante los getters.

Internamente, Pedido conserva las referencias originales
recibidas para mantener las relaciones de agregación y
asociación.

La clase DetallePedido es inmutable porque:

- Se declara como una clase final.
- Sus atributos son privados y finales.
- No contiene métodos setters.
- Sus atributos contienen valores primitivos o inmutables.

Por lo tanto, getDetalle() puede devolver el objeto
DetallePedido sin necesidad de crear una copia defensiva.

Main.java incluye una prueba que modifica las copias del
cliente y del repartidor, verificando que los valores
originales permanezcan sin cambios.

## Capítulo 5: Abstracción, interfaces y polimorfismo

### Clase abstracta Persona

Persona representa las características comunes de los
clientes y repartidores.

Contiene los atributos nombre y rut, además del método
abstracto mostrarRol().

Se utiliza una clase abstracta porque una persona genérica
no representa por sí sola un rol concreto dentro del sistema.

Las clases Cliente y Repartidor heredan de Persona e
implementan su propio comportamiento mediante mostrarRol().

### Interfaces

El proyecto utiliza las siguientes interfaces:

#### Vendible

Define las operaciones comerciales de un producto:

- calcularPrecioFinal()
- aplicarDescuento(double porcentaje)
- getPrecio()

#### Despachable

Define las operaciones relacionadas con el despacho:

- calcularCostoEnvio(String comuna)
- programarDespacho(String fecha)

#### ImpuestoAplicable

Define las operaciones para el manejo de impuestos:

- obtenerTasa()
- calcularImpuesto(double monto)

#### Notificable

Define la operación para notificar a un cliente:

- enviarCorreo(Cliente cliente, String mensaje)

### Implementación de múltiples interfaces

La clase Mueble implementa simultáneamente las interfaces
Vendible y Despachable.

```java
public class Mueble implements Vendible, Despachable
```

Esto permite utilizar un mismo objeto mediante distintos
contratos de comportamiento.

### Polimorfismo

Main.java demuestra el polimorfismo mediante un arreglo
de referencias de tipo Persona que contiene objetos Cliente
y Repartidor.

Cada objeto ejecuta su propia implementación de mostrarRol().

También se demuestra el polimorfismo mediante referencias
declaradas como interfaces:

```java
Vendible productoVendible = miMueble;
Despachable productoDespachable = miMueble;
```

El mismo objeto Mueble puede utilizarse desde diferentes
abstracciones según la operación requerida.

## Capítulo 6: Relaciones entre clases

### 1. Composición: Pedido - DetallePedido

La clase Pedido crea internamente su objeto DetallePedido
dentro del constructor.

```java
this.detalle = new DetallePedido(
    tipoMueble, cantidad, precioUnitario);
```

El detalle forma parte del pedido y su creación queda bajo
la responsabilidad de la clase Pedido.

Evidencia: constructor de Pedido.java.

### 2. Agregación: Pedido - Cliente

El objeto Cliente se crea externamente en Main.java y
posteriormente se entrega al constructor de Pedido.

Pedido mantiene una referencia al cliente, pero este puede
existir independientemente del pedido.

Evidencia: atributo cliente y constructor de Pedido.java.

### 3. Asociación: Pedido - Repartidor

El objeto Repartidor se crea externamente y se vincula al
pedido mediante el método setRepartidor().

```java
pedido1.setRepartidor(repartidorPedido);
pedido2.setRepartidor(repartidorPedido);
```

Un mismo repartidor puede estar asociado a distintos
pedidos sin depender de ellos para existir.

Evidencia: setRepartidor() en Pedido.java y asignaciones
realizadas en Main.java.

### 4. Dependencia: Pedido - Despachable y Notificable

El método confirmarDespacho() recibe los servicios mediante
parámetros declarados como interfaces.

```java
public void confirmarDespacho(
    Despachable servicioDespacho,
    Notificable servicioNotificacion,
    String comuna,
    String fecha)
```

Pedido utiliza temporalmente estos servicios para calcular
el costo de envío, programar el despacho y notificar al
cliente.

Los servicios no se almacenan como atributos de instancia.

Evidencia: confirmarDespacho() en Pedido.java.

## Capítulo 7: Principios SOLID

### SRP - Principio de Responsabilidad Única

Single Responsibility Principle.

Una clase debe tener una responsabilidad específica y una
razón principal para cambiar.

El proyecto demuestra este principio mediante la separación
de responsabilidades en las siguientes clases:

| Clase | Responsabilidad |
|---|---|
| DetallePedido | Representar el detalle y calcular su subtotal. |
| ServicioDespacho | Gestionar las operaciones de despacho. |
| ServicioNotificacion | Simular las notificaciones al cliente. |
| IvaChile | Calcular el impuesto del 19 %. |
| SinImpuesto | Calcular el impuesto del 0 %. |

Esta separación evita concentrar todas las operaciones
del sistema en una única clase.

### OCP - Principio Abierto/Cerrado

Open/Closed Principle.

Las clases deben estar abiertas a la extensión, pero
cerradas a la modificación.

Se aplica mediante la interfaz ImpuestoAplicable, que permite
incorporar nuevas políticas de impuestos sin modificar las
clases Mueble ni Pedido.

Se dispone de dos implementaciones:

- IvaChile: política de impuesto del 19 %.
- SinImpuesto: política de impuesto del 0 %.

La clase SinImpuesto se incorpora como una extensión del
comportamiento existente con fines académicos.

Main.java demuestra este principio utilizando ambas
implementaciones sobre muebles con el mismo precio base.

Resultado de la demostración:

| Concepto | Valor |
|---|---:|
| Precio base | $100.000 |
| Precio con impuesto del 19 % | $119.000 |
| Precio con impuesto del 0 % | $100.000 |

### DIP - Principio de Inversión de Dependencias

Dependency Inversion Principle.

Los módulos deben depender de abstracciones y no
directamente de implementaciones concretas.

En la versión anterior del proyecto, Pedido dependía
directamente de la clase Muebleria para obtener el IVA.

Esta dependencia fue reemplazada por la interfaz
ImpuestoAplicable.

Actualmente, las clases Mueble y Pedido reciben una política
de impuesto mediante sus constructores.

```java
private final ImpuestoAplicable impuestoAplicable;
```

El cálculo del impuesto se realiza mediante el contrato
definido por la interfaz.

```java
public double calcularTotal() {
    double subtotal = detalle.calcularSubtotal();

    return subtotal
        + impuestoAplicable.calcularImpuesto(subtotal);
}
```

Además, Pedido utiliza las interfaces Despachable y
Notificable para coordinar los servicios externos.

Esto reduce el acoplamiento y facilita la extensión
y el mantenimiento del sistema.

## Correcciones solicitadas en Cátedra 4

### Corrección 1: Cambio de nombre de Muebleria a Mueble

Problema identificado:

La clase Muebleria representaba un producto y no el negocio
completo, por lo que su nombre no reflejaba correctamente
su responsabilidad.

Decisión técnica:

Renombrar la clase Muebleria a Mueble.

Modificación realizada:

- Cambio de nombre de la clase y del archivo Java.
- Actualización del constructor.
- Actualización de las referencias correspondientes.
- Actualización de Main.java y de la documentación.

Verificación:

El proyecto compila y permite crear objetos Mueble,
calcular precios, aplicar descuentos y programar despachos.

### Corrección 2: Aplicación del principio DIP

Problema identificado:

Pedido.calcularTotal() dependía directamente de
Muebleria.getIva(), generando acoplamiento con una
clase concreta.

Decisión técnica:

Introducir una abstracción mediante ImpuestoAplicable
y utilizar inyección de dependencias por constructor.

Modificación realizada:

- Incorporación de ImpuestoAplicable.
- Implementación de IvaChile.
- Actualización de Mueble y Pedido.
- Incorporación de SinImpuesto para demostrar OCP.
- Actualización de las demostraciones en Main.java.

Verificación:

El proyecto compila y calcula correctamente los totales
de los pedidos.

También se verifica que Mueble puede utilizar políticas
de impuestos del 19 % y del 0 % sin modificar su código.

## Tabla de cumplimiento de la Solemne 2

| Capítulo | Requisito | Ubicación en el código | Evidencia o mejora respecto de Cátedra 4 | Estado |
|---|---|---|---|---|
| 4 | Atributos privados | Clases del dominio | Protección del estado interno. | Cumple |
| 4 | Mínimo cinco setters validados | Persona, Cliente, Repartidor, Mueble y Pedido | Validaciones de datos y referencias. | Cumple |
| 4 | Mínimo tres atributos de instancia final | Persona, Mueble, Pedido y DetallePedido | Protección contra reasignaciones. | Cumple |
| 4 | Mínimo dos constantes private static final | Mueble, ServicioDespacho, IvaChile y SinImpuesto | Valores constantes para impuestos, descuentos y envíos. | Cumple |
| 4 | Copias defensivas | Pedido.getCliente() y Pedido.getRepartidor() | Protección de objetos mutables mediante getters. | Cumple |
| 4 | Inmutabilidad | DetallePedido.java | Clase final, atributos finales y ausencia de setters. | Cumple |
| 5 | Clase abstracta y método abstracto | Persona.java | Abstracción de los roles del sistema. | Cumple |
| 5 | Justificación de la abstracción | Javadoc de Persona.java | Documentación de la decisión de diseño. | Cumple |
| 5 | Dos interfaces con mínimo dos métodos | Vendible.java y Despachable.java | Contratos de comportamiento. | Cumple |
| 5 | Una clase implementa ambas interfaces | Mueble.java | Implementación de Vendible y Despachable. | Cumple |
| 5 | Polimorfismo | Main.java | Referencias Persona, Vendible y Despachable. | Cumple |
| 6 | Composición | Pedido y DetallePedido | Creación interna del detalle. | Cumple |
| 6 | Agregación | Pedido y Cliente | Incorporación de un cliente externo. | Cumple |
| 6 | Asociación | Pedido y Repartidor | Repartidor compartido entre pedidos. | Cumple |
| 6 | Dependencia | Pedido, Despachable y Notificable | Uso temporal de servicios mediante parámetros. | Cumple |
| 7 | SRP | DetallePedido y clases de servicios e impuestos | Separación de responsabilidades. | Cumple |
| 7 | OCP | ImpuestoAplicable, IvaChile y SinImpuesto | Nueva política sin modificar las clases consumidoras. | Cumple |
| 7 | DIP | Pedido.java y Mueble.java | Dependencia de abstracciones mediante inyección. | Cumple |

## Cómo ejecutar el proyecto

### Requisitos

- Java Development Kit (JDK).
- Visual Studio Code u otro editor de código.
- Git para el control de versiones.

### Compilar el proyecto

Desde la terminal, ubicada en la carpeta que contiene
los archivos .java, ejecutar:

```bash
javac *.java
```

### Ejecutar el programa

```bash
java Main
```

## Pruebas y resultados

El método main() de Main.java contiene demostraciones de:

1. Herencia y polimorfismo.
2. Implementación de interfaces.
3. Cálculo de precios e impuestos.
4. Aplicación de descuentos.
5. Composición, agregación, asociación y dependencia.
6. Procesamiento de pedidos.
7. Programación de despachos.
8. Simulación de notificaciones.
9. Principios SOLID.
10. Copias defensivas e inmutabilidad.

### Resultados de los pedidos

| Pedido | Subtotal | Total con IVA | Envío | Total a pagar |
|---|---:|---:|---:|---:|
| 1001 | $170.991,00 | $203.479,29 | $5.000,00 | $208.479,29 |
| 1002 | $249.990,00 | $297.488,10 | $5.000,00 | $302.488,10 |

### Verificación de copias defensivas

La prueba modifica las copias obtenidas mediante los getters
de Pedido y verifica que los valores originales permanezcan
sin cambios.

El resultado esperado de una prueba exitosa es:

```text
=== PRUEBA DE COPIAS DEFENSIVAS ===
Nombre de la copia: Cliente modificado
Nombre original: Francisco Fuentealba
Vehículo de la copia: Vehículo de prueba
Vehículo original: Camión Ford F-150
[OK] Las copias defensivas protegen los objetos originales mediante los getters.
```

## Control de versiones y trazabilidad

El proyecto utiliza Git y GitHub para mantener un registro
de las modificaciones realizadas durante su desarrollo.

Las correcciones solicitadas por el docente se documentan
mediante GitHub Issues, considerando el siguiente formato:

- Análisis del problema.
- Decisión técnica.
- Modificación realizada.
- Verificación.
- Commit asociado.

Los commits asociados a las correcciones permiten mantener
la trazabilidad entre los problemas identificados y las
soluciones implementadas.

Repositorio:

https://github.com/franmaka/taller-Uno

## Observaciones

El sistema tiene fines académicos y demuestra los contenidos
de Programación Orientada a Objetos correspondientes a
los capítulos 4, 5, 6 y 7.

Las notificaciones y la programación de despachos se
simulan mediante mensajes en la consola.

La política SinImpuesto se utiliza exclusivamente para
demostrar la extensibilidad del sistema y no representa
una exención tributaria real.

## Conclusión

Las mejoras realizadas respecto de Cátedra 4 permiten
fortalecer el encapsulamiento, reducir el acoplamiento,
separar responsabilidades y facilitar la extensión del
sistema.

El proyecto demuestra los conceptos de abstracción,
herencia, interfaces, polimorfismo, relaciones entre clases
y los principios SOLID SRP, OCP y DIP mediante ejemplos
ejecutables y documentados.

---

Proyecto académico - Programación Orientada a Objetos

Solemne 2 - 2026
