package huffman;

import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		PriorityQueue<Diccionario> colaPrueba = new PriorityQueue<>();
		Diccionario d1 = new Diccionario('A',3);
		Diccionario d2 = new Diccionario('B',4);
		Diccionario d3 = new Diccionario('C',2);
		Diccionario d4 = new Diccionario('D',1);
		colaPrueba.add(d1);
		colaPrueba.add(d2);
		colaPrueba.add(d3);
		colaPrueba.add(d4);
		Nodo<Diccionario> nodoPruebaNodo = CreadorArbol.construccion(colaPrueba);
		System.out.println(nodoPruebaNodo.getHijoDerecha());
	}

}
