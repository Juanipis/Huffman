package huffman;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.PriorityQueue;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileInputStream;
public class Main {
	
	

	public static void main(String[] args) throws IOException {
		File file = new File("Fichero.txt");
		String lectura = CreadorArbol.leerFile(file);
		PriorityQueue<Diccionario> cola = CreadorArbol.reconocimientoCaracteres(lectura);
		Nodo<Diccionario> nodoPruebaNodo = CreadorArbol.construccion(cola);
		ArbolB<Diccionario> arbol = new ArbolB<Diccionario>(nodoPruebaNodo);
		Map<Character,String> carlos = GenDictionary.GenDic(nodoPruebaNodo);
		String arrCodf = Codificacion.secuenciasBytes(carlos, lectura);
		String mensajedef = Codificacion.Decodificar2(arrCodf, carlos);
		System.out.println(mensajedef);
		
		System.out.println(":)");
		// Test metodo reconocimientoCaracteres

//		String msg = "abBb cC";
//		CreadorArbol creador = new CreadorArbol();
//		System.out.print(creador.reconocimientoCaracteres(msg));
	}

}
