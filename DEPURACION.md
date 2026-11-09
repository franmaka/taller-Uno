# Registro de depuración – Cátedra 4

## Incidente: salto de línea mostrado como texto literal

- **Archivo afectado:** `Main.java`.
- **Problema observado:** al ejecutar el programa, algunos títulos aparecían precedidos por los caracteres `\n` en vez de comenzar en una línea nueva.
- **Cómo se detectó:** al ejecutar `java Main`, se observó que la salida mostraba literalmente `\n=== DEMOSTRACIÓN...`.
- **Decisión tomada:** corregir la secuencia de escape en las instrucciones `System.out.println`, manteniendo el salto de línea dentro de la cadena.
- **Modificación realizada:** se reemplazó la secuencia doblemente escapada por `\n` como escape Java en los mensajes correspondientes de `Main.java` y `Pedido.java`.
- **Verificación:** se recompilaron todos los archivos con `javac *.java` y se ejecutó `java Main`. La compilación terminó sin errores y los títulos se mostraron en líneas separadas.
- **Commit relacionado:** [completar después de registrar este cambio en Git; agregar el hash o enlace real del commit].

## Evidencia de verificación

Comandos ejecutados:

```bash
javac *.java
java Main
```

Resultado: compilación exitosa y demostración del pedido con cliente, detalle, repartidor y servicio de despacho.
