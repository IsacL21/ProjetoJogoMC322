package main;

public class Load {
	
	private boolean bool;
	private int inteiro;
	
	public boolean isBool() {
		return bool;
	}
	public void setBool(boolean bool) {
		this.bool = bool;
	}
	public int getInteiro() {
		return inteiro;
	}
	public void setInteiro(int inteiro) {
		this.inteiro = inteiro;
	}
	
	public Load(boolean bool, int inteiro) {
		this.bool = bool;
		this.inteiro = inteiro;
	}

}
