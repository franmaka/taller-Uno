
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
| Mueble.java | Representa un producto (precio, descuentos, impuesto); delega el envío en un servicio Despachable. |
| Vendible.java | Define las operaciones de venta, precios y descuentos. |
| Despachable.java | Define las operaciones de cálculo y programación de despachos. |
| Pedido.java | Calcula los montos de una compra y coordina su despacho. |
| ComprobantePedido.java | Presenta por consola el resumen de un pedido confirmado (separado de Pedido en S2). |
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

Los setters que se invocan desde un constructor
(setDireccionEntrega(), setVehiculo() y setPrecio()) se declaran
final, de modo que una subclase no pueda sobrescribirlos y omitir
la validación durante la construcción del objeto.

### Atributos de instancia final

Se utilizan atributos final para impedir la reasignación
de valores o referencias después de su inicialización.

Algunos ejemplos son:

- Persona: rut.
- Mueble: tipo, material, impuestoAplicable y servicioDespacho.
- Pedido: numero, cliente, detalle e impuestoAplicable.
- DetallePedido: tipoMueble, cantidad y precioUnitario.
- ComprobantePedido: todos sus atributos (clase inmutable).

Una referencia final no convierte automáticamente al objeto
referenciado en inmutable.

### Constantes de clase

Se utilizan constantes private static final para representar
valores que no deben modificarse durante la ejecución.

| Clase | Constante | Valor |
|---|---|---|
| Mueble | DESCUENTO_MAXIMO | 50.0 |
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

Pedido también crea un ComprobantePedido como variable local
dentro de confirmarDespacho() para imprimir el resumen; tampoco
lo guarda como atributo.

### 5. Asociación: Mueble - Despachable

Desde S2, Mueble mantiene como atributo un servicio Despachable
creado externamente en Main.java. El mismo servicio se comparte
entre varios muebles y pedidos, por lo que no existe una relación
parte–todo.

Evidencia: atributo servicioDespacho y constructor de Mueble.java.

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
| Pedido | Calcular los montos del pedido y coordinar su despacho. |
| ComprobantePedido | Presentar el resumen del pedido por consola. |
| Mueble | Representar el producto: precio, descuentos e impuesto. |

Esta separación evita concentrar todas las operaciones
del sistema en una única clase.

La auditoría de S2 detectó dos responsabilidades mezcladas y
se refactorizaron (ver Correcciones 3 y 4 más abajo):

1. Pedido calculaba, coordinaba e imprimía el resumen.
   La impresión se separó en ComprobantePedido.
2. Mueble definía su propia tarifa de envío, duplicando la
   regla de ServicioDespacho. Ahora la delega en el servicio.

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

## Correcciones: Cátedra 4 y auditoría de Solemne 2

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

### Corrección 3 (auditoría S2): SRP en Pedido

Problema identificado:

Pedido.confirmarDespacho() calculaba los montos, coordinaba los
servicios y además daba formato al resumen en consola (siete
instrucciones println/printf). Pedido tenía así dos razones para
cambiar: las reglas del pedido y la forma de presentarlo.

Decisión técnica:

Extraer la presentación a una clase nueva e inmutable,
ComprobantePedido. Pedido sigue calculando los montos y
coordinando los servicios.

Modificación realizada:

- Nueva clase ComprobantePedido con el método imprimir().
- confirmarDespacho() crea el comprobante como variable local
  (relación de dependencia) y llama a imprimir().

Verificación:

Se compiló con `javac -Xlint:all *.java` y se ejecutó `java Main`.
El resumen de los pedidos 1001 y 1002 es idéntico al de la
versión anterior (mismos subtotales, totales y envío).

### Corrección 4 (auditoría S2): tarifa de envío duplicada en Mueble

Problema identificado:

Mueble y ServicioDespacho declaraban cada uno su propia constante
COSTO_ENVIO = 5000.0. La misma regla de negocio estaba en dos
clases: un cambio de tarifa obligaba a modificar ambas y podía
dejar dos costos de envío distintos en el sistema.

Decisión técnica:

Mueble mantiene el contrato Despachable (requisito del capítulo 5),
pero delega el cálculo y la programación del envío en un
Despachable inyectado por constructor (DIP).

Modificación realizada:

- Se eliminó Mueble.COSTO_ENVIO.
- Nuevo atributo `private final Despachable servicioDespacho`
  y nuevo parámetro en el constructor de Mueble.
- calcularCostoEnvio() y programarDespacho() delegan en el servicio.
- Main.java crea un único ServicioDespacho y lo entrega a los
  muebles y a los pedidos.

Verificación:

El costo de envío mostrado por Mueble y por los pedidos proviene
ahora de la misma constante (ServicioDespacho.COSTO_ENVIO) y sigue
siendo $5.000. La única diferencia en la salida es el texto de
programación del despacho del sofá, que ahora lo genera
ServicioDespacho.

### Corrección 5 (auditoría S2): setters invocados desde constructores

Problema identificado:

`javac -Xlint:all` advertía *possible 'this' escape* en Cliente,
Repartidor y Mueble: sus constructores llaman a setters que una
subclase podría sobrescribir y así omitir la validación.

Decisión técnica y modificación:

Declarar final setDireccionEntrega(), setVehiculo() y setPrecio().

Verificación:

La compilación con `-Xlint:all` termina sin advertencias.

## Tabla de cumplimiento de la Solemne 2

| Capítulo | Indicador | Ubicación en el código | Evidencia / cambio respecto de C4 |
|---|---|---|---|
| 4 | Todos los atributos de instancia son private | Todas las clases; p. ej. [Pedido.java L20-35](https://github.com/franmaka/taller-Uno/blob/main/Pedido.java#L20), [Mueble.java L29-43](https://github.com/franmaka/taller-Uno/blob/main/Mueble.java#L29) | Ninguna clase declara atributos public; el estado se modifica solo con métodos validados. |
| 4 | ≥ 5 setters validados | [Persona.setNombre()](https://github.com/franmaka/taller-Uno/blob/main/Persona.java#L47), [Cliente.setDireccionEntrega()](https://github.com/franmaka/taller-Uno/blob/main/Cliente.java#L29), [Repartidor.setVehiculo()](https://github.com/franmaka/taller-Uno/blob/main/Repartidor.java#L25), [Mueble.setPrecio()](https://github.com/franmaka/taller-Uno/blob/main/Mueble.java#L118), [Pedido.setRepartidor()](https://github.com/franmaka/taller-Uno/blob/main/Pedido.java#L76) | Lanzan IllegalArgumentException ante null, vacío o precio negativo/NaN. **S2:** los tres que usa el constructor ahora son final (Corrección 5). |
| 4 | ≥ 3 atributos final de instancia | [Persona.rut](https://github.com/franmaka/taller-Uno/blob/main/Persona.java#L15), [Pedido.numero/cliente/detalle](https://github.com/franmaka/taller-Uno/blob/main/Pedido.java#L20), [DetallePedido](https://github.com/franmaka/taller-Uno/blob/main/DetallePedido.java#L20) | Datos que no cambian tras la creación (RUT, número de pedido, detalle). |
| 4 | ≥ 2 constantes private static final | [ServicioDespacho.COSTO_ENVIO](https://github.com/franmaka/taller-Uno/blob/main/ServicioDespacho.java#L20), [Mueble.DESCUENTO_MAXIMO](https://github.com/franmaka/taller-Uno/blob/main/Mueble.java#L29), [IvaChile.TASA_IVA](https://github.com/franmaka/taller-Uno/blob/main/IvaChile.java#L3), [SinImpuesto.TASA_IMPUESTO](https://github.com/franmaka/taller-Uno/blob/main/SinImpuesto.java#L17) | **S2:** se eliminó la constante duplicada Mueble.COSTO_ENVIO (Corrección 4). |
| 4 | Getters sin exponer estado mutable | [Pedido.getCliente()](https://github.com/franmaka/taller-Uno/blob/main/Pedido.java#L183), [Pedido.getRepartidor()](https://github.com/franmaka/taller-Uno/blob/main/Pedido.java#L199), [Pedido.getDetalle()](https://github.com/franmaka/taller-Uno/blob/main/Pedido.java#L217) | Copias defensivas de Cliente y Repartidor; DetallePedido es inmutable. Prueba en [Main.java L274-324](https://github.com/franmaka/taller-Uno/blob/main/Main.java#L274). |
| 5 | Clase abstracta con método abstracto | [Persona](https://github.com/franmaka/taller-Uno/blob/main/Persona.java#L11) → [mostrarRol()](https://github.com/franmaka/taller-Uno/blob/main/Persona.java#L61) | Cliente y Repartidor implementan mostrarRol(). |
| 5 | Javadoc que fundamenta la abstracción | [Persona.java L1-10](https://github.com/franmaka/taller-Uno/blob/main/Persona.java#L1) | Una persona genérica no cumple un rol concreto en la mueblería. |
| 5 | Dos interfaces con ≥ 2 métodos | [Vendible](https://github.com/franmaka/taller-Uno/blob/main/Vendible.java#L1) (3), [Despachable](https://github.com/franmaka/taller-Uno/blob/main/Despachable.java#L1) (2), [ImpuestoAplicable](https://github.com/franmaka/taller-Uno/blob/main/ImpuestoAplicable.java#L1) (2) | Contratos de venta, despacho e impuesto. |
| 5 | Clase que implementa ambas interfaces | [Mueble implements Vendible, Despachable](https://github.com/franmaka/taller-Uno/blob/main/Mueble.java#L26) | **S2:** Despachable se cumple por delegación en el servicio de despacho. |
| 5 | Polimorfismo con referencia de tipo interfaz | [Main.java L98-130](https://github.com/franmaka/taller-Uno/blob/main/Main.java#L98); arreglo Persona en [L22-72](https://github.com/franmaka/taller-Uno/blob/main/Main.java#L22) | `Vendible productoVendible = miMueble;` invoca la implementación de Mueble. |
| 6 | Composición | [Pedido(): new DetallePedido](https://github.com/franmaka/taller-Uno/blob/main/Pedido.java#L67) | Main solo entrega datos; Pedido crea su detalle ({L('Main.java',169,'Main L169')}). |
| 6 | Agregación | [Pedido.cliente](https://github.com/franmaka/taller-Uno/blob/main/Pedido.java#L24) | Cliente creado en {L('Main.java',141,'Main L141')} y recibido por el constructor. |
| 6 | Asociación | [Pedido.repartidor](https://github.com/franmaka/taller-Uno/blob/main/Pedido.java#L35), [Mueble.servicioDespacho](https://github.com/franmaka/taller-Uno/blob/main/Mueble.java#L43) | Mismo repartidor asignado a dos pedidos ({L('Main.java',186,'Main L186-187')}). **S2:** nueva asociación Mueble → Despachable. |
| 6 | Dependencia | [Pedido.confirmarDespacho()](https://github.com/franmaka/taller-Uno/blob/main/Pedido.java#L113), [new ComprobantePedido](https://github.com/franmaka/taller-Uno/blob/main/Pedido.java#L148) | Servicios recibidos como parámetro y comprobante como variable local; ninguno se guarda. |
| 7 | SRP | [ComprobantePedido](https://github.com/franmaka/taller-Uno/blob/main/ComprobantePedido.java#L18), [Pedido.confirmarDespacho()](https://github.com/franmaka/taller-Uno/blob/main/Pedido.java#L113), [Mueble.calcularCostoEnvio()](https://github.com/franmaka/taller-Uno/blob/main/Mueble.java#L192) | **S2 (refactorización):** impresión separada de Pedido y tarifa de envío centralizada (Correcciones 3 y 4). |
| 7 | OCP | [SinImpuesto implements ImpuestoAplicable](https://github.com/franmaka/taller-Uno/blob/main/SinImpuesto.java#L15); demo en [Main L226-264](https://github.com/franmaka/taller-Uno/blob/main/Main.java#L226) | Nueva política sin modificar Mueble, Pedido ni ImpuestoAplicable: $119.000 vs $100.000. |
| 7 | DIP | [Pedido.impuestoAplicable](https://github.com/franmaka/taller-Uno/blob/main/Pedido.java#L31), [Mueble.impuestoAplicable](https://github.com/franmaka/taller-Uno/blob/main/Mueble.java#L37), [Mueble.servicioDespacho](https://github.com/franmaka/taller-Uno/blob/main/Mueble.java#L43) | Dependen de interfaces inyectadas por constructor. Cambio respecto de C4: Pedido dependía de Muebleria.getIva(). |

> Los enlaces apuntan a la rama `main`. Para un enlace permanente,
> abrir el archivo en GitHub y presionar `y` (fija el commit en la URL).

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
