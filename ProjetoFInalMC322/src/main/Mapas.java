package main;

public enum Mapas {
	MAPA_1(0, "/rooms/room1"),
	MAPA_2(1, "/rooms/room2"),
	MAPA_3(2, "/rooms/room3");
	
	private final int index;
    private final String adress;
    
    private Mapas(int index, String adress) {
        this.index = index;
        this.adress = adress;
    }

	public int getIndex() {
		return index;
	}

	public String getAdress() {
		return adress;
	}
    
    
}
