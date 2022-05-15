package huffman;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Codificacion {
	public static String secuenciasBytes(Map<Character, String> map, String lectura) {
		//Fase 1, traduccion
		// Se recorre cada uno de los caracteres del archivo leido y a partir de ellos se van a 
		// traducir a secuencias binarios con el mapa
		StringBuilder textPas1 = new StringBuilder(); // StringBuilder que contiene la secuencia original en binarios
		for (int i = 0; i < lectura.length(); i++) {
			textPas1.append(map.get(lectura.charAt(i)));
		}
		StringBuilder textPas2 = new StringBuilder();
		
		//Fase 2, creacion de sub cadenas, todas deben empezar por 1 para que se pueda crear un caracter
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

	public static void escritura(String[] arr, String lastSecuence, String ruta) throws IOException {
		File f = new File(ruta); // declaramos e inicializamos un archivo nuevo para rellenar luego con integers
		BufferedWriter flujo = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8));
		for (int i = 0; i < arr.length; i++) {
			short value = Short.parseShort(arr[i], 2);
			flujo.write(value);
		}
		//ESCRITURA DE LA ULTIMA SECUENCIA EN BINARIO
		flujo.write(";");
		flujo.write(lastSecuence);
		flujo.write(";");
		flujo.close();
	}
	
	public static String Decodificar2(String s, Map<Character,String> map, String lasBinaryDecode) {//la raíz del árbol
		s = decodeUTF8Binario(s);
		s = s+lasBinaryDecode;
		Map<String, Character> inverseMap = new HashMap<>();
        map.forEach((key, value) -> inverseMap.put(value, key));
		StringBuilder result = new StringBuilder();
		StringBuilder sb= new StringBuilder();
        for(char c: s.toCharArray()) {
        	sb.append(c);
        	if(inverseMap.containsKey(sb.toString())) {
        		char a = inverseMap.get(sb.toString());
        		
        		result.append(a);
        		sb = new StringBuilder();
        	}
        }
        return result.toString();
    }
	
	public static String decodeUTF8Binario(String s) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			String rr =Integer.toBinaryString((short) s.charAt(i));
			if(rr.length() < 15 ) { //Debemos rellenar con ceros a la izquierda
				while(rr.length()!=15) {
					rr = "0"+rr;
				}
			}
			result.append(rr);
		}
		return result.toString();
	}
	
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
