package arquivos;

import java.util.ArrayList;

public class InputEntidadesMapa {
	final int xBlocoInicialPlayer;
	final int yBlocoInicialPlayer;
	ArrayList<ArquivoEntidade> listaZumbis= new ArrayList<ArquivoEntidade>();
	ArrayList<ArquivoEntidade> listaSlimes= new ArrayList<ArquivoEntidade>();
	ArrayList<ArquivoEntidade> listaPortas= new ArrayList<ArquivoEntidade>();
	ArrayList<ArquivoMorcego> listaMorcegos= new ArrayList<ArquivoMorcego>();
	ArrayList<ArquivoEntidade> listaChaves= new ArrayList<ArquivoEntidade>();
	ArrayList<ArquivoEntidade> listaPocoes= new ArrayList<ArquivoEntidade>();
	ArrayList<ArquivoBau> listaBaus= new ArrayList<ArquivoBau>();
	
	InputEntidadesMapa(int xBlocoInicialPlayer,int yBlocoInicialPlayer){
		this.xBlocoInicialPlayer = xBlocoInicialPlayer;
		this.yBlocoInicialPlayer = yBlocoInicialPlayer;
	}
	
	public int getXBlocoInicialPlayer() {
		return xBlocoInicialPlayer;
	}

	public int getYBlocoInicialPlayer() {
		return yBlocoInicialPlayer;
	}

	public ArrayList<ArquivoEntidade> getListaZumbis() {
		return listaZumbis;
	}

	public ArrayList<ArquivoEntidade> getListaSlimes() {
		return listaSlimes;
	}
	
	public ArrayList<ArquivoEntidade> getListaPortas() {
		return listaPortas;
	}

	public ArrayList<ArquivoMorcego> getListaMorcegos() {
		return listaMorcegos;
	}

	public ArrayList<ArquivoEntidade> getListaChaves() {
		return listaChaves;
	}

	public ArrayList<ArquivoEntidade> getListaPocoes() {
		return listaPocoes;
	}

	public ArrayList<ArquivoBau> getListaBaus() {
		return listaBaus;
	}
	
	public void addZumbi(int posicaoXBloco, int posicaoYBloco) {
		listaZumbis.add(new ArquivoEntidade(posicaoXBloco, posicaoYBloco));
	}
	
	public void addSlime(int posicaoXBloco, int posicaoYBloco) {
		listaSlimes.add(new ArquivoEntidade(posicaoXBloco, posicaoYBloco));
	}
	
	public void addPorta(int posicaoXBloco, int posicaoYBloco) {
		listaPortas.add(new ArquivoEntidade(posicaoXBloco, posicaoYBloco));
	}
	
	public void addChave(int posicaoXBloco, int posicaoYBloco) {
		listaChaves.add(new ArquivoEntidade(posicaoXBloco, posicaoYBloco));
	}
	
	public void addPocao(int posicaoXBloco, int posicaoYBloco) {
		listaPocoes.add(new ArquivoEntidade(posicaoXBloco, posicaoYBloco));
	}
	
	public void addMorcego(int posicaoXBloco, int posicaoYBloco, int posicaoXFinalBloco, int posicaoYFinalBloco) {
		listaMorcegos.add(new ArquivoMorcego(posicaoXBloco, posicaoYBloco, posicaoXFinalBloco, posicaoYFinalBloco));
	}
	
	public void addBau(int posicaoXBloco, int posicaoYBloco, String tipoItem) {
		listaBaus.add(new ArquivoBau(posicaoXBloco, posicaoYBloco, tipoItem));
	}
	
	

	public class ArquivoEntidade{
		final int posicaoXBloco;
		final int posicaoYBloco;
		
		ArquivoEntidade(int posicaoXBloco, int posicaoYBloco) {
			this.posicaoXBloco = posicaoXBloco;
			this.posicaoYBloco = posicaoYBloco;
		}

		public int getPosicaoXBloco() {
			return posicaoXBloco;
		}

		public int getPosicaoYBloco() {
			return posicaoYBloco;
		}
	}
	
	public class ArquivoBau extends ArquivoEntidade{
		final String tipoItem;
		ArquivoBau(int posicaoX, int posicaoY, String tipoItem){
			super(posicaoX, posicaoY);
			this.tipoItem = tipoItem;
		}
		public String getTipoItem() {
			return tipoItem;
		}
	}
	
	public class ArquivoMorcego extends ArquivoEntidade{
		final int posicaoXFinalBloco;
		final int posicaoYFinalBloco;
		
		ArquivoMorcego(int posicaoXBloco, int posicaoYBloco, int posicaoXFinalBloco, int posicaoYFinalBloco){
			super(posicaoXBloco, posicaoYBloco);
			this.posicaoXFinalBloco = posicaoXFinalBloco;
			this.posicaoYFinalBloco = posicaoYFinalBloco;
		}

		public int getPosicaoXFinalBloco() {
			return posicaoXFinalBloco;
		}

		public int getPosicaoYFinalBloco() {
			return posicaoYFinalBloco;
		}
		
	}
	
	
}
