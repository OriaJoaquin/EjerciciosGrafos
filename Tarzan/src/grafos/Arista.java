package grafos;

public class Arista {
	private int nodoOrigen;
	private int nodoDestino;
	private double costo;
	
	
	public int getNodoOrigen() {
		return nodoOrigen;
	}
	public void setNodoOrigen(int nodoOrigen) {
		this.nodoOrigen = nodoOrigen;
	}
	public int getNodoDestino() {
		return nodoDestino;
	}
	public void setNodoDestino(int nodoDestino) {
		this.nodoDestino = nodoDestino;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	public Arista(int nodoOrigen, int nodoDestino, double costo) {
		this.nodoOrigen = nodoOrigen;
		this.nodoDestino = nodoDestino;
		this.costo = costo;
	}
	
	
}
