package huffman;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class CreadorArbol  {
	public static Nodo<Diccionario> construccion(PriorityQueue<Diccionario> tablaFrecuencias){
		PriorityQueue<Nodo<Diccionario>> tablaNodos = new PriorityQueue<>();
		while(!tablaFrecuencias.isEmpty()) {
			tablaNodos.add(new Nodo<Diccionario>(tablaFrecuencias.poll()));
		}
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

	public static PriorityQueue<Diccionario> reconocimientoCaracteres (String s) { // Se supone que el archivo .txt ya fue abierto en otro metodo y tenemos el string de la lectura
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

		// TEST
		//System.out.println(caracteres);

		for (int i = 0; i < caracteresMensaje.length; i++) { // Se recorre el arreglo de caracteres y se suma 1 a la frecuencia de cada caracter
			for (int j = 0; j < caracteres.size(); j++) {
				if (caracteres.get(j).getSimbolo() == caracteresMensaje[i])
					caracteres.get(j).setValor(caracteres.get(j).getValor() + 1);
			}
		}

		// TEST
		//System.out.println(caracteres);

		PriorityQueue<Diccionario> tablaFrecuencias = new PriorityQueue<>(); // Se crea una cola de prioridad de diccionarios
		for (int i = 0; i < caracteres.size(); i++)  // Se recorre el arreglo de diccionarios y se agrega a la cola de prioridad
			tablaFrecuencias.add(caracteres.get(i));


		// TEST
		//System.out.println(tablaFrecuencias);

		return tablaFrecuencias;
	}

}

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

	public int getValor() {
		return valor;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Diccionario [simbolo=" + simbolo + ", valor=" + valor + "]";
	}
	@Override
	public int compareTo(Diccionario o) {
		return (this.valor - o.getValor());
	}
}