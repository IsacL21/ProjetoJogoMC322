package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import arquivos.Arquivos;

public class Main {

	public static void main(boolean b, String nome, Arquivos arquivosResource) {
		
		
		//inicia a janela
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Jogo 2D");
		window.setLocationRelativeTo(null);
		
		int mapa_atual = Save.define_mapa(b, nome);
		//cria engine
		Engine engine = new Engine(mapa_atual);
		
		//adiciona e ajusta a janela ao gamepanel
		
		window.add(engine.getGamePanel());
		window.pack();
		
		//inicia looping do jogo
		engine.startGameThread();
			
		window.setVisible(true);
		
		Save.salvar(mapa_atual, nome);
		
		window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                
            	Save.salvar(mapa_atual, nome);
            	
                System.out.println("A janela foi fechada.");
                
                System.exit(0);
            }
        });
		
	
	}

}
