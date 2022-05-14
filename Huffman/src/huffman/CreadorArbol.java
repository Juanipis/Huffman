package huffman;

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
	
	
	
	@Override
	public String toString() {
		return "Diccionario [simbolo=" + simbolo + ", valor=" + valor + "]";
	}
	@Override
	public int compareTo(Diccionario o) {
		return (this.valor - o.getValor());
	}
}