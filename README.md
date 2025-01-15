# HowManyChars
Ejercicio lectura y escritura de archivos del curso de *JAVA*

### ENUNCIADO
Se tiene un fichero plano **repetir.txt**.<br>
Contiene un mínimo de una línea (y un máximo indeterminado) con la siguiente estructura:

`[ REPETICIONES ] ; [ ‘A’...’Z’ ]`

- El número de repeticiones que queremos que se escriba el carácter de la derecha.
- El número de repeticiones puede estar entre 1 y 100. Y siempre los números estarán bien escritos.
- Puede ser que veces se cuele un carácter que no esté entre la ‘A’ y la ‘Z’. Esta línea no se procesará y se incluirá una nueva línea en el fichero errores.txt indicando con un mensaje de lo sucedido.

### EJEMPLO
*repetir.txt*
```
5;X
9;?
12;F
```

*destino.txt*
```
XXXXX
FFFFFFFFFFFF
```

*error.txt*
```
Caracter no permitido en linea 2
```
