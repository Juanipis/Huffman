package huffman;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Codificacion {
	public static String secuenciasBytes(Map<Character, String> map, String lectura) {
		//Fase 1, traduccion
		// Se recorre cada uno de los caracteres del archivo leido y a partir de ellos se van a 
		// traducir a secuencias binarias con el mapa
		StringBuilder textPas1 = new StringBuilder(); // StringBuilder que contiene la secuencia original en binarios
		for (int i = 0; i < lectura.length(); i++) {
			textPas1.append(map.get(lectura.charAt(i))); // Se meten las secuencias binarias correspondiente a cada caracter en un string builder
			// haciendo uso del mapa de genDictionary
		}
		StringBuilder textPas2 = new StringBuilder();
		//Fase 2, creacion de sub cadenas, todas deben empezar por 1 para que se pueda crear un caracter (en la descompresion)
		for (int i = 0; i < textPas1.length(); i++) { // Separacion de secuencias de 15 bits con un ";"
			if(i%15==0 && i!=0 && i!=1) {
				textPas2.append(";");
				textPas2.append(textPas1.charAt(i));
			}
			else {
				textPas2.append(textPas1.charAt(i));
			}
		}
		return textPas2.toString();
	}
	
	// Las secuencias binarias se pasan a numeros decimales asociados por medio de un base 2(eran binarios) 
	// el .write se encargara luego de pasar dichos decimales a caracteres especiales para demostrar la 
	// compresion del archivo en el compresor
	public static void escritura(String[] arr, String lastSecuence, String ruta) throws IOException {
		File f = new File(ruta); // declaramos e inicializamos un archivo nuevo para rellenar luego con integers
		BufferedWriter flujo = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8)); // Los caracteres especiales van a estar en UTF-8
		for (int i = 0; i < arr.length; i++) {
			short value = Short.parseShort(arr[i], 2);
			flujo.write(value); // Esto se encarga del UTF-8
		}
		//ESCRITURA DE LA ULTIMA SECUENCIA EN BINARIO 
		// Esta ultima en el caso de que empiece por cero, no se pasa como caracter especial sino que se pega
		// como binario tal cual en el archivo de salida
		flujo.write(";");
		flujo.write(lastSecuence);
		flujo.write(";");
		flujo.close();
	}
	
	// Metodo que servira descomprimir el archivo en UTF-8 al original
	// String esta en UTF-8
	public static String Decodificar2(String s, Map<Character,String> map, String lasBinaryDecode) {// Secuencia ultima en binario para evitar los problemas
		s = decodeUTF8Binario(s);
		s = s+lasBinaryDecode; // Se pega el residuo binario original al archivo ya en binario
		Map<String, Character> inverseMap = new HashMap<>(); // Mapa inverso, ya es binario y su caracter correspondiente
        map.forEach((key, value) -> inverseMap.put(value, key));
		StringBuilder result = new StringBuilder(); // StringBuilder con resultado descomprimido
		StringBuilder sb= new StringBuilder(); // StringBuilder temporal
        for(char c: s.toCharArray()) { // Binarios como arreglo de char
        	sb.append(c); // Se mete el temporal
        	if(inverseMap.containsKey(sb.toString())) {
        		char a = inverseMap.get(sb.toString()); // Se van metiendo binarios hasta que encuentre el caracter correspondiente
        		
        		result.append(a);
        		sb = new StringBuilder();
        	}
        }
        return result.toString();
    }
	
	public static String decodeUTF8Binario(String s) { // Se pasa el UTF-8
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			String rr =Integer.toBinaryString((short) s.charAt(i)); // De UTF- 8 a decimal y de decimal a binario
			if(rr.length() < 15 ) { //Debemos rellenar con ceros a la izquierda en todas las secuencias menores a 15 bits
				// para recuperar informacion
				while(rr.length()!=15) {
					rr = "0"+rr;
				}
			}
			result.append(rr);
		}
		return result.toString(); // Devuelve el String de los binarios
	}
	
	// Metodo que permite la lectura del archivo con los caracteres especiales resultantes
	public static String leerFileUTF8(File file) throws IOException {
		FileInputStream fileStream = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fileStream, "UTF-8"));
		String st;
		StringBuilder almacenador = new StringBuilder();
			while((st =br.readLine()) != null)
			//alm.concat(br.nextLine()+"\n");
			almacenador.append(st+System.lineSeparator());
		br.close();
		return almacenador.toString();
			
	}
}