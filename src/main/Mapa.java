package main;

public enum Mapa {

    TAMANHO_BLOCO(48),
    QTDE_BLOCOS_HORIZONTAL(16),
    QTDE_BLOCOS_VERTICAL(12);

    private final int posicao;

    Mapa(int posicao) {
        this.posicao = posicao;
    }

    public int getPosicao() {
        return posicao;
    }
}
