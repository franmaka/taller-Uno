# Registro de depuración – Cátedra 4

## Incidente: salto de línea mostrado como texto literal

- **Archivo afectado:** `Main.java` y `Pedido.java`.
- **Problema observado:** Al ejecutar el programa, algunos títulos aparecían precedidos por los caracteres `\n` en vez de comenzar en una línea nueva.
- **Cómo se detectó:** Al ejecutar `java Main`, se observó que la salida mostraba literalmente `\n=== DEMOSTRACIÓN...`.
- **Decisión tomada:** Corregir la secuencia de escape en las instrucciones `System.out.println`, manteniendo el salto de línea dentro de la cadena.
- **Modificación realizada:** Se reemplazó la secuencia doblemente escapada por `\n` como escape Java en los mensajes correspondientes de `Main.java` y `Pedido.java`.
- **Verificación:** Se recompilaron todos los archivos con `javac *.java` y se ejecutó `java Main`. La compilación terminó sin errores y los títulos se mostraron en líneas separadas.
- **Commit relacionado:** `b7f47ff` – Actualizacion Catedra 4.

## Evidencia de verificación

Comandos ejecutados:

```bash
javac *.java
java Main
```

**Resultado:** Compilación exitosa y demostración del pedido con cliente, detalle, repartidor y servicio de despacho.

## Conclusión

La incidencia fue corregida satisfactoriamente. Se comprobó que el programa compila y ejecuta correctamente, mostrando los títulos y mensajes en el formato esperado.