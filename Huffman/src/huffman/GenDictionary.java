package huffman;

import java.util.HashMap;
import java.util.Map;

public class GenDictionary {
	// Método que crea el diccionario para codificar cada uno de los caracteres
	public static Map<Character,String> GenDic(Nodo<Diccionario> raiz) {
		Map<Character,String> im= new HashMap<>();
		String cod="";
		Look(raiz,cod,im);
		return im;
	}
	
	// Metodo que recorre de forma recursiva cada subarbol para recolectar el codigo correspondiente a cada caracter
	public static void Look(Nodo<Diccionario> raiz,String cod,Map<Character,String> map) {
		if(raiz.getHijoDerecha()==null && raiz.getHijoIzquierda()==null) {
			map.put(raiz.getLlave().getSimbolo(),cod);
		}
		else {
			Look(raiz.getHijoDerecha(),cod + "1", map); // Recursividad subarbol derecho se coloca un "1"
			Look(raiz.getHijoIzquierda(),cod + "0", map); // Recursividad subarbol izquierdo se coloca un "0"
		}
	}
}
