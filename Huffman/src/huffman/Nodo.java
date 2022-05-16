package huffman;

public class Nodo <E extends Comparable<E>> implements Comparable<Nodo<E>>{

	// LLAVE: El caracter asociado al nodo en particular (en este cado puede ser un simbolo o null si equivale a una suma)

	// Declaracion de variables de la clase
	protected int llegada; // Valor asociado a el orden
	private static int llegadaCont = 0; // Contador que aumenta a medida en que se crean nuevos nodos
	protected E llave; // En nuestro caso es el objeto de tipo diccionario que tiene simbolo y valor
	protected Nodo<E> hijoIzquierda;
	protected Nodo<E> hijoDerecha;
	protected Nodo<E> nodoPadre;
	
	// Constructores de la clase
	// CONSTRUCTOR 1 (Pasando la llave del nodo)
	public Nodo(E llave) {
		this.llave = llave;
		this.hijoIzquierda = null;
		this.hijoDerecha = null;
		this.nodoPadre = null;
		this.llegada = ++llegadaCont;
	}
		
	// CONSTRUCTOR 2 (Pasando todos los datos asociados al nodo)
	public Nodo(E llave, Nodo<E> hijoIzquierda, Nodo<E> hijoDerecha, Nodo<E> nodoPadre) {
		this.llegada = ++llegadaCont;
		this.llave = llave;
		this.hijoIzquierda = hijoIzquierda;
		this.hijoDerecha = hijoDerecha;
		this.nodoPadre = nodoPadre;
	}
	
	// Getters y Setters para cada una de las variables asociadas
	// LLEGADA
	public int getLlegada() {
		return llegada;
	}
	// LLAVE
	public E getLlave() {
		return llave;
	}
	
	public void setLlave(E llave) {
		this.llave = llave;
	}
	// HIJO IZQUIERDA
	public Nodo<E> getHijoIzquierda() {
		return hijoIzquierda;
	}

	public void setHijoIzquierda(Nodo<E> hijoIzquierda) {
		this.hijoIzquierda = hijoIzquierda;
	}
	// HIJO DERECHA
	public Nodo<E> getHijoDerecha() {
		return hijoDerecha;
	}

	public void setHijoDerecha(Nodo<E> hijoDerecha) {
		this.hijoDerecha = hijoDerecha;
	}
	// NODO PADRE
	public Nodo<E> getNodoPadre() {
		return nodoPadre;
	}

	public void setNodoPadre(Nodo<E> nodoPadre) {
		this.nodoPadre = nodoPadre;
	}
	
	@Override
	public String toString() {
		return "Nodo [ llave =" + llave + "]";
	}

	@Override
	public int compareTo(Nodo<E> o) {
		// Si las llaves en términos de frecuencias son iguales, se comparan por orden de llega, sino por frecuencias
		if(this.llave.compareTo(o.llave) == 0) {
			return this.llegada - o.llegada;
		}
		else {
			return this.llave.compareTo(o.llave);
		}
	}
}
