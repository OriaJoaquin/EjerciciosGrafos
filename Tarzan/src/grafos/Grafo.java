package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public abstract class Grafo {
	private int numVertices;
	private int numAristas;
	private double[][] matAdyacencia;
	private boolean bandera;

	private static final int COMIENZO_ARRAY = 1;

	public static final double MAXIMO_COSTO = 9999;

	public Grafo(String ruta) {
		this.leerGrafo(ruta);
	}

	public Grafo(int cantVertices) {
		this.matAdyacencia = new double[cantVertices + 1][cantVertices + 1];
		this.numVertices = cantVertices;
		this.bandera = false;
	}

	public abstract void insertarArista(int i, int j, double costo);

	public int getNumVertices() {
		return numVertices;
	}

	public int getNumAristas() {
		return numAristas;
	}

	public double[][] getMatAdyacencia() {
		return matAdyacencia;
	}

	public double getPesoArista(int i, int j) {
		return this.matAdyacencia[i][j];
	}

	private Grafo leerGrafo(String ruta) {
		try {
			Scanner sc = new Scanner(new File(ruta));
			this.numVertices = sc.nextInt();
			this.numAristas = sc.nextInt();

			final int FINAL_ARRAY = this.numVertices + 1;

			this.matAdyacencia = new double[FINAL_ARRAY][FINAL_ARRAY];

			int verticeOrigen, verticeDestino, costo;

			while (sc.hasNextLine()) {
				verticeOrigen = sc.nextInt();
				verticeDestino = sc.nextInt();
				costo = sc.nextInt();

				this.insertarArista(verticeOrigen, verticeDestino, costo);
			}

			for (int i = COMIENZO_ARRAY; i < FINAL_ARRAY; i++) {
				for (int j = COMIENZO_ARRAY; j < FINAL_ARRAY; j++) {
					if (j != i && this.matAdyacencia[i][j] == 0)
						this.matAdyacencia[i][j] = MAXIMO_COSTO;
				}
			}

			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return this;
	}

	public int[] dijkstra(int fuente) {
		final int FINAL_ARRAY = this.numVertices + 1;

		double[] distancia = new double[FINAL_ARRAY];
		int[] padre = new int[FINAL_ARRAY];
		boolean[] visto = new boolean[FINAL_ARRAY];

		PriorityQueue<Integer> cola = new PriorityQueue<Integer>();

		for (int i = COMIENZO_ARRAY; i < FINAL_ARRAY; i++) {
			distancia[i] = 100.0;
			padre[i] = 0;
			visto[i] = false;
		}

		distancia[fuente] = 0;

		cola.add(fuente);

		while (!cola.isEmpty()) {
			int vertice = cola.poll();

			visto[vertice] = true;

			for (int v : getAdyacentes(vertice)) {
				if (!visto[v] && distancia[v] > distancia[vertice] + this.getPesoArista(vertice, v)) {
					distancia[v] = distancia[vertice] + this.getPesoArista(vertice, v);
					padre[v] = vertice;
					cola.add(v);
				}
			}
		}

		int k;
		for (k = COMIENZO_ARRAY; k < FINAL_ARRAY; k++) {
			System.out.println("Distancia a " + k + " es " + distancia[k]);
		}

		System.out.println("--------------------------------------------");

		for (k = COMIENZO_ARRAY; k < FINAL_ARRAY; k++) {
			System.out.println("Padre de " + k + " es " + padre[k]);
		}

		// System.out.println("bandera: " + bandera);

		return padre;

	}

	public LinkedList<Integer> getAdyacentes(int nodo) {
		LinkedList<Integer> vecinos = new LinkedList<Integer>();

		for (int i = COMIENZO_ARRAY; i < this.numVertices + 1; i++) {
			if (this.matAdyacencia[nodo][i] <= 50)
				vecinos.add(i);
		}

		if (vecinos.size() > 0)
			return vecinos;
		
		if (bandera==false) {
			int nodoMasCerca= getAdyacenteMasCercano(nodo);
			double dist = this.matAdyacencia[nodo][nodoMasCerca];
			if(dist<=100.0) {
				bandera=true;
				vecinos.add(nodoMasCerca);
			}		
		}
		
		return vecinos;
	}
	
	public Integer getAdyacenteMasCercano(int nodo){
		double distMin = MAXIMO_COSTO;
		int nodoMin = 0;
				
		for (int i = COMIENZO_ARRAY; i < this.numVertices + 1; i++) {
			if (this.matAdyacencia[nodo][i] <= distMin) {
				nodoMin=i;
				distMin=this.matAdyacencia[nodo][i];
			}
				
		}
		
		return nodoMin;
	}
	
	public LinkedList<Arista> getAristasInicidentes(int v) {
		final int FINAL_ARRAY = this.numVertices + 1;
		LinkedList<Arista> aristas = new LinkedList<Arista>();

		for (int i = COMIENZO_ARRAY; i < FINAL_ARRAY; i++) {
			if (this.matAdyacencia[v][i] != 0.0) {
				aristas.add(new Arista(v, i, this.matAdyacencia[v][i]));
			}
		}

		return aristas;
	}

	public double[][] floyd() {
		final int FINAL_ARRAY = this.numVertices + 1;
		double[][] distancias = this.matAdyacencia;// Arrays.copyOf(this.matAdyacencia, FINAL_ARRAY);

		for (int k = COMIENZO_ARRAY; k < FINAL_ARRAY; k++) {
			for (int i = COMIENZO_ARRAY; i < FINAL_ARRAY; i++) {
				for (int j = COMIENZO_ARRAY; j < FINAL_ARRAY; j++) {
					distancias[i][j] = Math.min(distancias[i][j], distancias[i][k] + distancias[k][j]);
				}
			}
		}

		for (int i = COMIENZO_ARRAY; i < FINAL_ARRAY; i++) {
			System.out.println("Nodo: " + i);
			for (int j = COMIENZO_ARRAY; j < FINAL_ARRAY; j++) {
				System.out.println("distancia a " + j + " es " + distancias[i][j]);
			}
			System.out.println("------------------------------");
		}

		return distancias;
	}

	public void bfs(int fuente) {
		final int FINAL_ARRAY = this.numVertices + 1;

		boolean[] visitado = new boolean[FINAL_ARRAY];
		double[] distancia = new double[FINAL_ARRAY];
		double[] padre = new double[FINAL_ARRAY];

		for (int i = COMIENZO_ARRAY; i < FINAL_ARRAY; i++) {
			distancia[i] = MAXIMO_COSTO;
		}

		visitado[fuente] = true;
		distancia[fuente] = 0;
		padre[fuente] = 0;

		Queue<Integer> cola = new LinkedList<Integer>();

		cola.add(fuente);

		while (!cola.isEmpty()) {
			int vertice = cola.poll();

			LinkedList<Integer> adyacentes = this.getAdyacentes(vertice);

			for (int v : adyacentes) {
				if (!visitado[v]) {
					visitado[v] = true;
					distancia[v] = distancia[vertice] + 1;
					padre[v] = vertice;
					cola.add(v);
				}
			}
		}

		int k;
		for (k = COMIENZO_ARRAY; k < FINAL_ARRAY; k++) {
			System.out.println("Distancia a " + k + " es " + distancia[k]);
		}

		System.out.println("--------------------------------------------");

		for (k = COMIENZO_ARRAY; k < FINAL_ARRAY; k++) {
			System.out.println("Padre de " + k + " es " + padre[k]);
		}
	}
}
