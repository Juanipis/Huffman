package huffman;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class CreadorArbol  {
	public static Nodo<FrecuenciasCaracteres> construccion(PriorityQueue<FrecuenciasCaracteres> tablaFrecuencias){
		PriorityQueue<Nodo<FrecuenciasCaracteres>> tablaNodos = new PriorityQueue<>();
		while(!tablaFrecuencias.isEmpty()) {
			tablaNodos.add(new Nodo<FrecuenciasCaracteres>(tablaFrecuencias.poll()));
		}
		while(tablaNodos.size() != 1) {
			Nodo<FrecuenciasCaracteres> nodo1 = tablaNodos.poll();
			Nodo<FrecuenciasCaracteres> nodo2 = tablaNodos.poll();
			Nodo<FrecuenciasCaracteres> nodoPadre = new Nodo<FrecuenciasCaracteres>(new FrecuenciasCaracteres(nodo1.getLlave().getValor() + nodo2.getLlave().getValor()));
			nodoPadre.setHijoIzquierda(nodo1);
			nodoPadre.setHijoDerecha(nodo2);
			tablaNodos.add(nodoPadre);
		}
		return tablaNodos.poll();
	}

	public static PriorityQueue<FrecuenciasCaracteres> reconocimientoCaracteres (String s) { // Se supone que el archivo .txt ya fue abierto en otro metodo y tenemos el string de la lectura
		char[] caracteresMensaje = s.toCharArray(); // Se convierte el string en un arreglo de caracteres
		ArrayList<FrecuenciasCaracteres> caracteres = new ArrayList<>(); // Se crea un arreglo de diccionarios (objetos que contienen el caracter y su frecuencia)
		for (int i = 0; i < caracteresMensaje.length; i++) { // Se recorre el arreglo de caracteres y se agrega a la lista de diccionarios sin repetir e inicializados en frecuencia 0
			boolean encontrado = false;
			for (int j = 0; j < caracteres.size(); j++) {
				if (caracteres.get(j).getSimbolo() == caracteresMensaje[i])
					encontrado = true;
			}
			if (!encontrado)
				caracteres.add(new FrecuenciasCaracteres(caracteresMensaje[i], 0) );
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

		PriorityQueue<FrecuenciasCaracteres> tablaFrecuencias = new PriorityQueue<>(); // Se crea una cola de prioridad de diccionarios
		for (int i = 0; i < caracteres.size(); i++)  // Se recorre el arreglo de diccionarios y se agrega a la cola de prioridad
			tablaFrecuencias.add(caracteres.get(i));


		// TEST
		//System.out.println(tablaFrecuencias);

		return tablaFrecuencias;
	}

}

class FrecuenciasCaracteres implements Comparable<FrecuenciasCaracteres>{
	char simbolo;
	int valor;

	public FrecuenciasCaracteres(int valor) {
		this.valor = valor;
	}
	public FrecuenciasCaracteres(char simbolo, int valor) {
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
	public int compareTo(FrecuenciasCaracteres o) {
		return (this.valor - o.getValor());
	}

}