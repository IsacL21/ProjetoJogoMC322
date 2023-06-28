package main;

public enum Mapa {

    //Constantes
    TAMANHO_BLOCO(48),
    QTDE_BLOCOS_HORIZONTAL(16),
    QTDE_BLOCOS_VERTICAL(12);

    //Propriedade
    private final int posicao;

    //Construtor
    Mapa(int posicao) {
        this.posicao = posicao;
    }

    //Getter
    public int getPosicao() {
        return posicao;
    }
}
