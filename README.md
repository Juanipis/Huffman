# 💾 Codificador huffman
Este programa es capaz de comprimir texto plano utilizando la codificación Huffman. El algoritmo de Huffman es un método de compresión de datos que utiliza la frecuencia de aparición de cada símbolo en el texto para generar un código binario que represente dicho símbolo de forma más compacta.

##  Integrantes 👷
* 👨‍💻 Pablo Mesa Hernández - Programador
* 👨‍💻 Juan José Giraldo Giraldo - Programador
* 👨‍💻 Juan Pablo Díaz Correa - Programador lider
* 👨‍💻 Natalia Naranjo Rodríguez - Programadora
* 👨‍💻 Rafael Arango Hinestrosa - Programador
* 👨‍💻 Simón González Montoya - Programador


## Construido con 🛠️
* [Eclipse 2021-12 ](https://www.eclipse.org/downloads/packages/release/2021-12) - El IDE utilizado por defecto
* JavaSE-11

## Cómo funciona la codificación Huffman?
El algoritmo de Huffman se basa en la frecuencia de aparición de cada símbolo en el texto a comprimir. Los símbolos más frecuentes son representados por códigos binarios más cortos, mientras que los menos frecuentes son representados por códigos más largos.

El algoritmo comienza calculando la frecuencia de cada símbolo en el texto y construyendo un árbol de frecuencias. Luego, se combinan los nodos con las dos frecuencias más bajas en un nuevo nodo padre, cuya frecuencia es igual a la suma de las frecuencias de los dos nodos hijos. Este proceso se repite hasta que todos los nodos estén combinados en un único nodo raíz.

El siguiente paso es asignar un código binario a cada símbolo, recorriendo el árbol de frecuencias desde la raíz hasta cada hoja. Se asigna un '0' cuando se va hacia la izquierda y un '1' cuando se va hacia la derecha. El código de cada símbolo es el camino que se sigue desde la raíz hasta la hoja correspondiente.

Finalmente, se utiliza la tabla de códigos generada por el algoritmo de Huffman para comprimir el texto original. Cada símbolo es reemplazado por su código binario correspondiente, generando un archivo más compacto.
