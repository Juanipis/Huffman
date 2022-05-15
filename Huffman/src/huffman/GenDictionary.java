package huffman;

import java.util.HashMap;
import java.util.Map;

//import huffman.Diccionario.DiccionarioBusqueda;

public class GenDictionary {

	public static void Look(Nodo<Diccionario> raiz,String cod,Map<Character,String> map) {
		
		if(raiz.getHijoDerecha()==null && raiz.getHijoIzquierda()==null) {
			map.put(raiz.getLlave().getSimbolo(),cod);
		}
		else {
			Look(raiz.getHijoDerecha(),cod + "1", map);
			Look(raiz.getHijoIzquierda(),cod + "0", map);
		}
	}
	public static Map<Character,String> GenDic(Nodo<Diccionario> raiz) {
		Map<Character,String> im= new HashMap<>();
		String cod="";
		Look(raiz,cod,im);
		return im;
	}
	
}
