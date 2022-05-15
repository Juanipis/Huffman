package huffman;

import java.util.*;

public class ArbolB<T extends Comparable<T>> {
	
	protected Nodo<T> raiz;

	public ArbolB (Nodo<T> raiz) {
		this.raiz = raiz;
	}
	
	public Nodo<T> getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo<T> raiz) {
		this.raiz = raiz;
		
	}
	
	public ArrayList<T> preOrden(){
		return preOrden(raiz, new ArrayList<T>());
	}
	
	public ArrayList<T> preOrden(Nodo<T> n, ArrayList<T> lista){
		if (n != null) {
			lista.add(n.getLlave());
			preOrden(n.getHijoIzquierda(), lista);
			preOrden(n.getHijoDerecha(), lista);	
		}
		return lista;
	}
	
	public ArrayList<T> postOrden(){
		return postOrden(raiz, new ArrayList<T>());
	}
	
	public ArrayList<T> postOrden(Nodo<T> n, ArrayList<T> lista){
		if (n != null) {
			postOrden(n.getHijoIzquierda(), lista);
			postOrden(n.getHijoDerecha(), lista);
			lista.add(n.getLlave());
		}
		return lista;
	}
	
	
	
	public ArrayList<T> inorden(){
		return inorden(raiz, new ArrayList<T>());
	}
	
	public ArrayList<T> inorden(Nodo<T> n, ArrayList<T> lista){
		if (n != null) {
			inorden(n.getHijoIzquierda(), lista);
			lista.add(n.getLlave());
			inorden(n.getHijoDerecha(), lista);
		}
		return lista;
	}
	
	
	
	public static void main(String[] args) {
		
		ArrayList<Integer> inorden = new ArrayList<Integer>();
		inorden.add(2); inorden.add(6); inorden.add(15); inorden.add(7); inorden.add(3); inorden.add(21); inorden.add(8); inorden.add(20); inorden.add(11); inorden.add(1); inorden.add(36); inorden.add(10); inorden.add(4); inorden.add(9); 
		ArrayList<Integer> preorden = new ArrayList<Integer>();
		preorden.add(20); preorden.add(15); preorden.add(2); preorden.add(6); preorden.add(3); preorden.add(7); preorden.add(8); preorden.add(21); preorden.add(36); preorden.add(11); preorden.add(1); preorden.add(10); preorden.add(9); preorden.add(4);		

	}
	
}
