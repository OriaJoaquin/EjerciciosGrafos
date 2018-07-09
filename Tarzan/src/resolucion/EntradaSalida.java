package resolucion;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class EntradaSalida {
	
	
	public HashMap<Integer, Arbol> leerArchivo(String ruta){
		Scanner sc;
		HashMap<Integer,Arbol> arboles = new HashMap<Integer,Arbol>();
		
		try {
			sc = new Scanner(new File(ruta));
			
			int count=1;
			
			while(sc.hasNextLine()) {
				Arbol a = new Arbol();
				
				a.setId(count);
				a.setX(sc.nextInt());
				a.setY(sc.nextInt());
				
				arboles.put(count,a);
				
				count++;
			}
			
//			for(Arbol a: arboles) {
//				System.out.println("X: " + a.getX() + " - Y: " + a.getY() + " - Id: " + a.getId());
//			}
			
			sc.close();
			
			return arboles;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
