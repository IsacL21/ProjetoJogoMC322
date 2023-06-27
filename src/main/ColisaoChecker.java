package main;

import java.util.ArrayList;
import java.awt.Rectangle;

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
    public boolean checkColisao(Personagem personagem, ArrayList<Entity> listaInimigos) {
        
        int personagemLeftX = personagem.getHitBox().x;
        int personagemRightX = personagem.getHitBox().x + personagem.getHitBox().width;
        int personagemTopY = personagem.getHitBox().y;
        int personagemBottomY = personagem.getHitBox().y + personagem.getHitBox().height;

        int personagemLeftCol = personagemLeftX/engine.getGamePanel().getTamanhoBloco();
        int personagemRightCol = personagemRightX/engine.getGamePanel().getTamanhoBloco();
        int personagemTopRow = personagemTopY/engine.getGamePanel().getTamanhoBloco();
        int personagemBottomRow = personagemBottomY/engine.getGamePanel().getTamanhoBloco();

        int tileNum1 = 0, tileNum2 = 0;
        switch(personagem.getDirecao()) {
            case "cima":
                // personagemTopY -= personagem.getVelocidade();
                personagemTopRow = (personagemTopY - personagem.getVelocidade())/engine.getGamePanel().getTamanhoBloco();
                tileNum1 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemLeftCol];
                tileNum2 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemRightCol];
                break;
            case "baixo":
                // personagemBottomY += personagem.getVelocidade();
                personagemBottomRow = (personagemBottomY + personagem.getVelocidade())/engine.getGamePanel().getTamanhoBloco();
                tileNum1 = engine.getMapBuilder().getMapa()[personagemBottomRow][personagemLeftCol];
                tileNum2 = engine.getMapBuilder().getMapa()[personagemBottomRow][personagemRightCol];
                break;
            case "esquerda":
                // personagemLeftX -= personagem.getVelocidade();
                personagemLeftCol = (personagemLeftX - personagem.getVelocidade())/engine.getGamePanel().getTamanhoBloco();
                tileNum1 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemLeftCol];
                tileNum2 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemLeftCol];
                break;
            case "direita":
                // personagemRightX += personagem.getVelocidade();
                personagemRightCol = (personagemRightX + personagem.getVelocidade())/engine.getGamePanel().getTamanhoBloco();
                tileNum1 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemRightCol];
                tileNum2 = engine.getMapBuilder().getMapa()[personagemTopRow][personagemRightCol];
                break;
        }
        if (engine.getMapBuilder().getBlocos()[tileNum1].isColidivel() == true || engine.getMapBuilder().getBlocos()[tileNum2].isColidivel() == true) {
            return true;
        }
        // Rectangle hitBoxFutura = new Rectangle(personagemLeftX, personagemTopY, personagemRightX - personagemLeftX, personagemBottomY - personagemTopY);
        // for (Inimigo inimigo : listaInimigos) {
        //                     // System.out.println(personagemLeftX + "," + personagemTopY + "," + inimigo.getHitBox().x + ","  + inimigo.getHitBox().y);

        //     if (hitBoxFutura.intersects(inimigo.getHitBox())) {
        //         return true;
        //     }
        // }
        return false;
    }

    public boolean checkColisaoInimigos(Personagem personagem, ArrayList<Inimigo> listaInimigos) {
        switch(personagem.getDirecao()) {
                case "cima":
                    personagem.getHitBox().y -= personagem.getVelocidade();
                    break;
                case "baixo":
                    personagem.getHitBox().y += personagem.getVelocidade();
                    break;
                case "esquerda":
                    personagem.getHitBox().x -= personagem.getVelocidade();
                    break;
                case "direita":
                    personagem.getHitBox().x += personagem.getVelocidade();
                    break;
            }
        for (Inimigo inimigo : listaInimigos) {
            if (personagem.getHitBox().intersects(100, 100, 32, 32)) {
                System.out.println("colidindo");
                return true;
            }
        }
        return false;
    }
    
}
