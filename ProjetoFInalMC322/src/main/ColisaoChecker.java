package main;

public class ColisaoChecker {
    
    //Propriedades
    Engine engine;

    //Construtor
    public ColisaoChecker(Engine engine) {
        this.engine = engine;
    }

    //Getters e Setters
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    //Métodos
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
            case "up":
                personagemTopRow = (personagemTopY - personagem.getVelocidade())/engine.getGamePanel().getTamanhoBloco();
                tileNum1 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemLeftCol];
                tileNum2 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemRightCol];
                if (engine.getMapBuilder().getBlocos()[tileNum1].getColidivel() == true || engine.getMapBuilder().getBlocos()[tileNum2].getColidivel() == true) {
                    personagem.setColisao(true);
                }
                break;
            case "down":
                personagemBottomRow = (personagemBottomY + personagem.getVelocidade())/engine.getGamePanel().getTamanhoBloco();
                tileNum1 = engine.getMapBuilder().getMapa()[personagemBottomRow][personagemLeftCol];
                tileNum2 = engine.getMapBuilder().getMapa()[personagemBottomRow][personagemRightCol];
                if (engine.getMapBuilder().getBlocos()[tileNum1].getColidivel() == true || engine.getMapBuilder().getBlocos()[tileNum2].getColidivel() == true) {
                    personagem.setColisao(true);
                }
                break;
            case "left":
                personagemLeftCol = (personagemLeftX - personagem.getVelocidade())/engine.getGamePanel().getTamanhoBloco();
                tileNum1 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemLeftCol];
                tileNum2 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemLeftCol];
                if (engine.getMapBuilder().getBlocos()[tileNum1].getColidivel() == true || engine.getMapBuilder().getBlocos()[tileNum2].getColidivel() == true) {
                    personagem.setColisao(true);
                }
                break;
            case "right":
                personagemRightCol = (personagemRightX + personagem.getVelocidade())/engine.getGamePanel().getTamanhoBloco();
                tileNum1 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemRightCol];
                tileNum2 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemRightCol];
                if (engine.getMapBuilder().getBlocos()[tileNum1].getColidivel() == true || engine.getMapBuilder().getBlocos()[tileNum2].getColidivel() == true) {
                    personagem.setColisao(true);
                }
                break;
        }
    }
    
}
