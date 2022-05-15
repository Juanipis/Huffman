package huffman;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class CreadorArbol  {
	
	// Metodo para el reconocimiento de caracteres del mensaje y sus frecuencias
	public static PriorityQueue<Diccionario> reconocimientoCaracteres (String s) { // El archivo .txt ya fue abierto en otro metodo y tenemos el string de la lectura
		char[] caracteresMensaje = s.toCharArray(); // Se convierte el string en un arreglo de caracteres
		ArrayList<Diccionario> caracteres = new ArrayList<>(); // Se crea un arreglo de diccionarios (objetos que contienen el caracter y su frecuencia)
		for (int i = 0; i < caracteresMensaje.length; i++) { // Se recorre el arreglo de caracteres y se agrega a la lista de diccionarios sin repetir e inicializados en frecuencia 0
			boolean encontrado = false;
			for (int j = 0; j < caracteres.size(); j++) {
				if (caracteres.get(j).getSimbolo() == caracteresMensaje[i])
					encontrado = true;
			}
			if (!encontrado)
				caracteres.add(new Diccionario(caracteresMensaje[i], 0) );
		}
		for (int i = 0; i < caracteresMensaje.length; i++) { // Se recorre el arreglo de caracteres y se suma 1 a la frecuencia de cada caracter
			for (int j = 0; j < caracteres.size(); j++) {
				if (caracteres.get(j).getSimbolo() == caracteresMensaje[i])
					caracteres.get(j).setValor(caracteres.get(j).getValor() + 1);
			}
		}
		PriorityQueue<Diccionario> tablaFrecuencias = new PriorityQueue<>(); // Se crea una cola de prioridad de diccionarios
		for (int i = 0; i < caracteres.size(); i++)  // Se recorre el arreglo de diccionarios y se agrega a la cola de prioridad
			tablaFrecuencias.add(caracteres.get(i));
		return tablaFrecuencias;
	}
	
	// Método de construccion de arbol en base a la tabla de frecuencias
	public static Nodo<Diccionario> construccion(PriorityQueue<Diccionario> tablaFrecuencias){ // Se recibe la tabla de frecuencias de menor a mayor
		PriorityQueue<Nodo<Diccionario>> tablaNodos = new PriorityQueue<>(); // Se crea la cola de prioridad de los nodos que va a tener el arbol 
		while(!tablaFrecuencias.isEmpty()) { // Conversión de tabla de frecuencias a la de nodos
			tablaNodos.add(new Nodo<Diccionario>(tablaFrecuencias.poll()));
		}
		// Se sacan de a dos elementos de la cola, a lo cuales se les asignará un padre que resulta de la suma de sus frecuencias de repetición
		// y este se vuelve a ingresar a la cola de prioridad (tabla nodos)
		// en el return se va a sacar el último elemento que corresponde a la raiz del arbol resultante
		while(tablaNodos.size() != 1) {
			Nodo<Diccionario> nodo1 = tablaNodos.poll();
			Nodo<Diccionario> nodo2 = tablaNodos.poll();
			Nodo<Diccionario> nodoPadre = new Nodo<Diccionario>(new Diccionario(nodo1.getLlave().getValor() + nodo2.getLlave().getValor()));
			nodoPadre.setHijoIzquierda(nodo1);
			nodoPadre.setHijoDerecha(nodo2);
			tablaNodos.add(nodoPadre);
		}
		return tablaNodos.poll();
	}
}

// Clase que contiene el simbolo correspondiente y su frecuencia de aparición
class Diccionario implements Comparable<Diccionario>{
	char simbolo;
	int valor;

	public Diccionario(int valor) {
		this.valor = valor;
	}
	public Diccionario(char simbolo, int valor) {
		this.simbolo = simbolo;
		this.valor = valor;
	}
	// Get de valor y simbolo
	public int getValor() {
		return valor;
	}

	public char getSimbolo() {
		return simbolo;
	}
	// Set de valor
	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Diccionario [simbolo=" + simbolo + ", valor=" + valor + "]";
	}
	// Método compareTo que ayuda a comparar las frecuencias para darlas de forma ascendente
	@Override
	public int compareTo(Diccionario o) {
		return (this.valor - o.getValor());
	}

}