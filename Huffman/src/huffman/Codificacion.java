package huffman;

import java.util.Map;
import java.util.*;
import java.io.*;

public class Codificacion {

	public static String secuenciasBytes(Map<Character, String> map, String lectura) {
		StringBuilder text = new StringBuilder();
		for (int i = 0; i < lectura.length(); i++) {
			text.append(map.get(lectura.charAt(i)));
		}
		return text.toString();
	}

	public static void escritura(String[] arr) throws IOException {
		FileInputStream Is;
		File f = new File("ArchivoOrigen"); // declaramos e inicializamos un archivo nuevo para rellenar luego con integers
		DataOutputStream flujo = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		for (int i = 0; i < arr.length; i++) {
			long value= Long.parseLong(arr[i], 2);
			flujo.writeLong(value);
			flujo.writeChar(',');
			
		}
		flujo.close();
	}
	
	public static String Decodificar2(String s, Map<Character,String> map) {//la raíz del árbol
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
}
