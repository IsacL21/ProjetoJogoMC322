package main;

public class ColisaoChecker {
    Engine engine;

    public ColisaoChecker(Engine engine) {
        this.engine = engine;
    }

    public void checkColisao(Personagem personagem) {
        int personagemLeftX = personagem.getX() + personagem.getHitBox().x;
        int personagemRightX = personagem.getX() + personagem.getHitBox().x + personagem.getHitBox().width;
        int personagemTopY = personagem.getY() + personagem.getHitBox().y;
        int personagemBottomY = personagem.getY() + personagem.getHitBox().y + personagem.getHitBox().height;

        int personagemLeftCol = personagemLeftX/engine.getGamePanel().getTamanhoBloco();
        int personagemRightCol = personagemRightX/engine.getGamePanel().getTamanhoBloco();
        int personagemTopRow = personagemTopY/engine.getGamePanel().getTamanhoBloco();
        int personagemBottomRow = personagemBottomY/engine.getGamePanel().getTamanhoBloco();

        int tileNum1, tileNum2;
        switch(personagem.getDirecao()) {
            case "cima":
                personagemTopRow = (personagemTopY - personagem.getVelocidade())/engine.getGamePanel().getTamanhoBloco();
                tileNum1 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemLeftCol];
                tileNum2 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemRightCol];
                if (engine.getMapBuilder().getBlocos()[tileNum1].isColidivel() == true || engine.getMapBuilder().getBlocos()[tileNum2].isColidivel() == true) {
                    personagem.setColisao(true);
                }
                break;
            case "baixo":
                personagemBottomRow = (personagemBottomY + personagem.getVelocidade())/engine.getGamePanel().getTamanhoBloco();
                tileNum1 = engine.getMapBuilder().getMapa()[personagemBottomRow][personagemLeftCol];
                tileNum2 = engine.getMapBuilder().getMapa()[personagemBottomRow][personagemRightCol];
                if (engine.getMapBuilder().getBlocos()[tileNum1].isColidivel() == true || engine.getMapBuilder().getBlocos()[tileNum2].isColidivel() == true) {
                    personagem.setColisao(true);
                }
                break;
            case "esquerda":
                personagemLeftCol = (personagemLeftX - personagem.getVelocidade())/engine.getGamePanel().getTamanhoBloco();
                tileNum1 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemLeftCol];
                tileNum2 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemLeftCol];
                if (engine.getMapBuilder().getBlocos()[tileNum1].isColidivel() == true || engine.getMapBuilder().getBlocos()[tileNum2].isColidivel() == true) {
                    personagem.setColisao(true);
                }
                break;
            case "direita":
                personagemRightCol = (personagemRightX + personagem.getVelocidade())/engine.getGamePanel().getTamanhoBloco();
                tileNum1 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemRightCol];
                tileNum2 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemRightCol];
                if (engine.getMapBuilder().getBlocos()[tileNum1].isColidivel() == true || engine.getMapBuilder().getBlocos()[tileNum2].isColidivel() == true) {
                    personagem.setColisao(true);
                }
                break;
        }
    }
    
}
