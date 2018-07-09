package resolucion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import grafos.GrafoND;

public class Mapa {
	private GrafoND g;
	private HashMap<Integer, Arbol> arboles;

	public Mapa(HashMap<Integer, Arbol> arboles) {
		this.g = new GrafoND(arboles.size());
		this.arboles = arboles;
		agregarArbolesAlGrafo(arboles);
	}

	private void agregarArbolesAlGrafo(HashMap<Integer, Arbol> arboles) {
		for (int a : arboles.keySet()) {
			for (int b : arboles.keySet()) {
				if (a != b) {
					Arbol arbolA = arboles.get(a);
					Arbol arbolB = arboles.get(b);
					g.insertarArista(arbolA.getId(), arbolB.getId(), arbolA.calcularDistanciaA(arbolB));
				}
			}
		}
	}

	public void encontrarCaminoMasCorto() {
		int padres[] = g.dijkstra(1);
//		
//		boolean bandera = false;
//		boolean sePuede=true;
//		int idInicio=1;
//		int idFin=arboles.size();
//
//		List<Arbol> recorrido = new ArrayList<Arbol>();
//		
//		while(sePuede && idInicio != idFin) {
//			Arbol arbolActual = arboles.get(idFin);
//			Arbol arbolPadre = arboles.get(padres[idFin]);
//			
//			double distancia = arbolActual.calcularDistanciaA(arbolPadre);
//			
//			if(distancia <= 50.0) {
//				idFin = arbolPadre.getId();
//				recorrido.add(arbolActual);
//			}else {
//				if(bandera==false && distancia<=100.0) {
//					bandera=true;
//					idFin = arbolPadre.getId();
//					recorrido.add(arbolActual);
//				}else {
//					sePuede=false;
//				}
//			}
//		}
//		
//		if(sePuede) {
//			for(Arbol a: recorrido) {
//				System.out.println("X: " + a.getX() + " - Y: " + a.getY() + " - Id: " + a.getId());
//			}
//		}else
//			System.out.println("NO SE PUEDE");
	}

}
