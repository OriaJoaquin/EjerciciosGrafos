package resolucion;

import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		EntradaSalida es = new EntradaSalida();
		
		HashMap<Integer,Arbol>  arboles = es.leerArchivo("tarzan.in");
		
		Mapa mapa = new Mapa(arboles);
		
		mapa.encontrarCaminoMasCorto();
	} 
}
