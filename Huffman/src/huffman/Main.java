package huffman;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
public class Main {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		compresion("prueba.txt");
		desComprension("prueba.txt.huffman");
	}
	
	public static void compresion(String rutaArchivo) throws IOException {
		File file = new File(rutaArchivo);
		StringBuilder lectura = new StringBuilder(Codificacion.leerFileUTF8(file));
		lectura.deleteCharAt(lectura.length()-1);
		
		PriorityQueue<Diccionario> cola = CreadorArbol.reconocimientoCaracteres(lectura.toString());
		Nodo<Diccionario> nodoPruebaNodo = CreadorArbol.construccion(cola);
		Map<Character,String> diccionario = GenDictionary.GenDic(nodoPruebaNodo);
		
		String Codf = Codificacion.secuenciasBytes(diccionario, lectura.toString());
		String[] arrCodf = Codf.split(";");
		
		if(arrCodf[arrCodf.length-1].charAt(0) == '0') {
			//La ultima secuencia que por defecto puede ser de 16 bits si empieza por 0 el
			//Codigo tendra problemas dado que java reconoce un 0001 como 1, por eso es necesario guardar la ultima secuencia
			String[] arrCodfSinUltimo = Arrays.copyOf(arrCodf, arrCodf.length-1);
			String lastSecuence = arrCodf[arrCodf.length-1];
			Codificacion.escritura(arrCodfSinUltimo, lastSecuence, rutaArchivo+".huffman");
		}else {
			Codificacion.escritura(arrCodf, "",rutaArchivo+".huffman");
		}
		//Guardar diccionario
		File dicSave = new File(rutaArchivo+".huffman.dic");
		ObjectOutputStream dictSaver = new ObjectOutputStream(new FileOutputStream(dicSave));
		dictSaver.writeObject(diccionario);
		dictSaver.close();
	}
	
	public static void desComprension(String rutaArchivo) throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(rutaArchivo);
		File dictSaver = new File(rutaArchivo+".dic");
		ObjectInputStream diccionario = new ObjectInputStream(new FileInputStream(dictSaver));
		Map<Character,String> dictFinal = (Map<Character, String>) diccionario.readObject();
		String codificado = Codificacion.leerFileUTF8(file);
		String[] arrCodificado = codificado.split(";");
		//SAQUEMOS EL ULTIMO CARACTER QUE ES SIN DECODIFICAR
		//ELIMINEMOS DEL string el ultimo salto de linea
		//codificado.deleteCharAt(codificado.length()-1);
		diccionario.close();
		String mensajedef = Codificacion.Decodificar2(arrCodificado[0], dictFinal,arrCodificado[arrCodificado.length-2]);
		System.out.println(mensajedef);
	}

}
