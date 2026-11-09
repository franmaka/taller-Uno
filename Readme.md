# Sistema de Gestión de Mueblería

## Integrantes
- Francisco Fuentealba Flores
- Victor Serrano Recabarren
- Jorge Bozo Araya
- Josue Gedeon

## Descripción
Proyecto Java para aplicar conceptos de Programación Orientada a Objetos en un sistema básico de mueblería. El proyecto demuestra herencia, polimorfismo, interfaces y las relaciones de composición, agregación, asociación y dependencia.

## Clases principales
- **Persona:** clase abstracta con los atributos comunes `nombre` y `rut`, y el método abstracto `mostrarRol()`.
- **Cliente:** hereda de `Persona` y agrega `direccionEntrega`.
- **Repartidor:** hereda de `Persona` y agrega `vehiculo`.
- **Muebleria:** representa un mueble y aplica las interfaces `Vendible` y `Despachable`.
- **Vendible:** define las operaciones para calcular el precio final y aplicar descuentos.
- **Despachable:** define las operaciones para calcular el costo de envío y programar un despacho.
- **Pedido:** representa una compra. Mantiene un cliente, contiene un detalle y puede tener un repartidor asignado.
- **DetallePedido:** contiene el tipo de mueble, cantidad y precio unitario de un pedido.
- **ServicioDespacho:** implementa `Despachable` y entrega las operaciones de cálculo y programación del despacho.

## Relaciones entre clases implementadas

### 1. Composición: `Pedido` – `DetallePedido`
`Pedido` crea su propio objeto `DetallePedido` dentro del constructor. El detalle es parte del pedido y no se recibe como un objeto independiente desde `Main`. Esto representa una relación de composición.

### 2. Agregación: `Pedido` – `Cliente`
El objeto `Cliente` se crea en `Main` y luego se entrega al constructor de `Pedido`. El pedido mantiene una referencia al cliente, pero el cliente puede existir independientemente del pedido. Esto representa agregación.

### 3. Asociación: `Pedido` – `Repartidor`
El `Repartidor` se crea fuera del pedido y se vincula posteriormente mediante `asignarRepartidor()`. Ambos objetos mantienen su identidad y pueden existir independientemente. Esto representa asociación.

### 4. Dependencia: `Pedido` – `Despachable` / `ServicioDespacho`
El método `confirmarDespacho()` recibe un objeto de tipo `Despachable` como parámetro y lo utiliza para calcular el envío y programar la fecha. `Pedido` no guarda ese servicio como atributo; lo utiliza temporalmente. Esto representa dependencia.

## Cómo ejecutar
Desde la carpeta que contiene los archivos `.java`:

```bash
javac *.java
java Main
```

La demostración de las cuatro relaciones se encuentra al final del método `main()` de `Main.java`.

## Observación
Las relaciones se muestran mediante objetos y llamadas ejecutadas desde `Main`. Para mantener la trazabilidad exigida por la evaluación, los cambios deben registrarse en el repositorio Git del grupo con commits descriptivos.
