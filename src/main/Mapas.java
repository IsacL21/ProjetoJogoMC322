package main;

public enum Mapas {

	//Constantes
	MAPA_1(0, "/rooms/room1"),
	MAPA_2(1, "/rooms/room2"),
	MAPA_3(2, "/rooms/room3");

	//Propriedades
	private final int index;
    private final String adress;
    
	//Construtor
    private Mapas(int index, String adress) {
        this.index = index;
        this.adress = adress;
    }

	//Getters
	public int getIndex() {
		return index;
	}

	public String getAdress() {
		return adress;
	}
}
