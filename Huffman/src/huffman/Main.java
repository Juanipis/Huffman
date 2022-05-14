package huffman;

import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		PriorityQueue<FrecuenciasCaracteres> colaPrueba = new PriorityQueue<>();
		FrecuenciasCaracteres d1 = new FrecuenciasCaracteres('A',3);
		FrecuenciasCaracteres d2 = new FrecuenciasCaracteres('B',4);
		FrecuenciasCaracteres d3 = new FrecuenciasCaracteres('C',2);
		FrecuenciasCaracteres d4 = new FrecuenciasCaracteres('D',1);
		colaPrueba.add(d1);
		colaPrueba.add(d2);
		colaPrueba.add(d3);
		colaPrueba.add(d4);
		Nodo<FrecuenciasCaracteres> nodoPruebaNodo = CreadorArbol.construccion(colaPrueba);
		System.out.println(nodoPruebaNodo.getHijoDerecha());

		// Test metodo reconocimientoCaracteres

//		String msg = "abBb cC";
//		CreadorArbol creador = new CreadorArbol();
//		System.out.print(creador.reconocimientoCaracteres(msg));
	}

}
