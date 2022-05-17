package huffman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class CreadorArbol {
	// Metodo de lectura y almacenamiento del archivo
	public static String leerFile(File file) throws IOException {
		// LEE EL ARCHIVO A EXAMINAR
		FileInputStream fileStream = new FileInputStream(file); // Flujo de entrada de datos
		BufferedReader br = new BufferedReader(new InputStreamReader(fileStream)); // Leer entrada de datos
		String st; // Texto temporal que se va a leer y copiar linea por linea en un StringBuilder
					// que se pasa a String denuevo
		StringBuilder almacenador = new StringBuilder();
		while ((st = br.readLine()) != null)
			almacenador.append(st + System.lineSeparator());
		return almacenador.toString();

	}
	
	// Metodo de reconocimiento de caracteres y generacion de tabla de frecuencias
	public static PriorityQueue<Diccionario> reconocimientoCaracteres(String s) { //El archivo.txt ya
																					// fue abierto en el metodo anterior y
																					// tenemos el string de la lectura
		char[] caracteresMensaje = s.toCharArray(); // Se convierte el string en un arreglo de caracteres
		ArrayList<Diccionario> caracteres = new ArrayList<>(); // Se crea un arreglo de diccionarios (objetos que
																// contienen el caracter y su frecuencia)
		for (int i = 0; i < caracteresMensaje.length; i++) { // Se recorre el arreglo de caracteres y se agrega a la
																// lista de diccionarios sin repetir e inicializados en
																// frecuencia 0
			// Se inicializa el encontrado en falso, que se encarga de que la primera vez siempre se
			// anada al ArrayList de tipo diccionario, en caso de que ya se encuentre, ponemos el encontrado en
			// true.
			boolean encontrado = false;
			for (int j = 0; j < caracteres.size(); j++) {
				if (caracteres.get(j).getSimbolo() == caracteresMensaje[i])
					encontrado = true;
			}
			if (!encontrado)
				caracteres.add(new Diccionario(caracteresMensaje[i], 0));
		}
		// Proceso de traspaso al identificar las frecuencias del original al ArrayList de tipo diccionario
		for (int i = 0; i < caracteresMensaje.length; i++) { // Se recorre el arreglo de caracteres y se suma 1 a la
																// frecuencia de cada caracter
			for (int j = 0; j < caracteres.size(); j++) {
				if (caracteres.get(j).getSimbolo() == caracteresMensaje[i])
					caracteres.get(j).setValor(caracteres.get(j).getValor() + 1);
			}
		}
		// Cola de prioridad que toma del ArrayList(tiene tnato simbolo como valor(frecuencia)
		PriorityQueue<Diccionario> tablaFrecuencias = new PriorityQueue<>(); // Se crea una cola de prioridad de
																				// diccionarios
		for (int i = 0; i < caracteres.size(); i++) // Se recorre el arreglo de diccionarios y se agrega a la cola de
													// prioridad
			tablaFrecuencias.add(caracteres.get(i));
		return tablaFrecuencias;
	}
	
	// Metodo de construccion de arbol en base a la tabla de frecuencias 
	public static Nodo<Diccionario> construccion(PriorityQueue<Diccionario> tablaFrecuencias) { // Se recibe la tabla de frecuencias de menor a mayor(ascedente)
		PriorityQueue<Nodo<Diccionario>> tablaNodos = new PriorityQueue<>(); //  // Se crea la cola de prioridad de los nodos que va a tener el arbol 
		while (!tablaFrecuencias.isEmpty()) { // // Conversi�n de tabla de frecuencias a la de nodos
			tablaNodos.add(new Nodo<Diccionario>(tablaFrecuencias.poll()));
		}
		// Se sacan de a dos elementos de la cola, a lo cuales se les asignara un padre que resulta de la suma de sus frecuencias de repeticion
		// y este se vuelve a ingresar a la cola de prioridad (tabla nodos)
		// en el return se va a sacar el ultimo elemento que corresponde a la raiz del arbol resultante
		while (tablaNodos.size() != 1) {
			Nodo<Diccionario> nodo1 = tablaNodos.poll();
			Nodo<Diccionario> nodo2 = tablaNodos.poll();
			Nodo<Diccionario> nodoPadre = new Nodo<Diccionario>(
					new Diccionario(nodo1.getLlave().getValor() + nodo2.getLlave().getValor()));
			nodoPadre.setHijoIzquierda(nodo1);
			nodoPadre.setHijoDerecha(nodo2);
			tablaNodos.add(nodoPadre);
		}
		return tablaNodos.poll();
	}
}

//Clase que contiene el simbolo correspondiente y su frecuencia de aparicion
class Diccionario implements Comparable<Diccionario> {
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
	// Metodo compareTo que ayuda a comparar las frecuencias para darlas de forma ascendente
	public int compareTo(Diccionario o) { // Es llamado por el compareTo de nodo
		return (this.valor - o.getValor());
	}

}