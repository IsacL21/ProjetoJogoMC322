package main;

public class Porta extends Entity{

	private boolean trancado;
	
	public Porta(int x, int y, boolean colisivel, Engine engine, boolean trancado) {
		super(x, y, colisivel, engine);
		this.trancado = trancado;
	}

	public boolean getTrancado() {
		return trancado;
	}

	public void setTrancado(boolean trancado) {
		this.trancado = trancado;
	}
}
