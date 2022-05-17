package huffman;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;

public class Compresor {
	public static void compresion(File f) throws IOException {
		File file = f;
		String path = file.getAbsolutePath();
		String pathNoExtension = path.substring(0, path.lastIndexOf('.'));
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
			Codificacion.escritura(arrCodfSinUltimo, lastSecuence, pathNoExtension+".huffman");
		}else {
			Codificacion.escritura(arrCodf, "",pathNoExtension+".huffman");
		}
		//Guardar diccionario

		File dicSave = new File(pathNoExtension+".huffman.dic");
		ObjectOutputStream dictSaver = new ObjectOutputStream(new FileOutputStream(dicSave));
		dictSaver.writeObject(diccionario);
		dictSaver.close();
	}

	public static void desComprension(File f) throws FileNotFoundException, IOException, ClassNotFoundException {

		File file = f;
		String rutaArchivo = file.getAbsolutePath();
		String pathNoExtension = rutaArchivo.substring(0, rutaArchivo.lastIndexOf('.'));

		File dictSaver = new File(pathNoExtension+".huffman.dic");
		ObjectInputStream diccionario = new ObjectInputStream(new FileInputStream(dictSaver));
		Map<Character,String> dictFinal = (Map<Character, String>) diccionario.readObject();
		String codificado = Codificacion.leerFileUTF8(file);
		String[] arrCodificado = codificado.split(";");
		diccionario.close();
		String mensajedef = Codificacion.Decodificar2(arrCodificado[0], dictFinal,arrCodificado[arrCodificado.length-2]);
		File file2 = new File(pathNoExtension+".descomprimido.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file2));
		writer.write(mensajedef);
		writer.close();
	}
}