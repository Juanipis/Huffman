package huffman;

public class Nodo <E extends Comparable<E>> implements Comparable<Nodo> {
	// FRECUENCIA: La cantidad de veces que se repite el elemento dentro del texto
	// LLAVE: El caracter asociado al nodo en particular (en este cado puede ser un simbolo o null si equivale a una suma)
	// Declaracion de variables de la clase
	protected int frecuencia;
	protected E llave;
	protected Nodo<E> hijoIzquierda;
	protected Nodo<E> hijoDerecha;
	protected Nodo<E> nodoPadre;
	
	// Constructores de la clase
	// CONSTRUCTOR 1 (Pasando la frecuencia del nodo)
	public Nodo(int frecuencia) {
		this.frecuencia = frecuencia;
		this.hijoIzquierda = null;
		this.hijoDerecha = null;
		this.nodoPadre = null;
	}

	// CONSTRUCTOR 2 (Pasando la frecuencia y la llave del nodo)
	public Nodo(int frecuencia, E llave) {
		this.frecuencia = frecuencia;
		this.llave = llave;
	}
	
	// CONSTRUCTOR 3 (Pasando todos los datos asociados al nodo)
	public Nodo(int frecuencia, E llave, Nodo<E> hijoIzquierda, Nodo<E> hijoDerecha, Nodo<E> nodoPadre) {
		this.frecuencia = frecuencia;
		this.llave = llave;
		this.hijoIzquierda = hijoIzquierda;
		this.hijoDerecha = hijoDerecha;
		this.nodoPadre = nodoPadre;
	}

	// Getters y Setters para cada una de las variables asociadas
	// FRECUENCIA
	public int getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
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
	public int compareTo(Nodo o) {
		return (this.frecuencia - o.getFrecuencia());
	}
}
