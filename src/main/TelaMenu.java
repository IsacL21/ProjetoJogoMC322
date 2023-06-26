package main;

import javax.swing.LayoutStyle;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.EtchedBorder;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.io.File;
import java.awt.Graphics;
import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;


public class TelaMenu extends JFrame
{
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private int windowWidthSize = 768;
    private int windowHeightSize = 576;
    private JTextField SaveName;
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                catch (InstantiationException e2) {
                    e2.printStackTrace();
                }
                catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                }
                catch (UnsupportedLookAndFeelException e4) {
                    e4.printStackTrace();
                }
                try {
                    final TelaMenu frame = new TelaMenu();
                    frame.setVisible(true);
                }
                catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
        });
    }
    
    protected void paintComponent(final Graphics g) {
    }
    
    public TelaMenu() {

        this.setTitle("Game");
        
        //Define a fonte customizada
        Font fontCustomizada = null;
        try {
            fontCustomizada = Font.createFont(0, new File("res/fonts/Merkur.ttf")).deriveFont(130.0f);
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(0, new File("res/fonts/Merkur.ttf")));
        }
        catch (IOException | FontFormatException ex) {
            ex.printStackTrace();
        }
        
        //Inicia o frame
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, windowWidthSize, windowHeightSize);
        (this.contentPane = new JPanel()).setBorder(new EtchedBorder(1, null, null));
        this.contentPane.setBackground(new Color(255, 255, 255));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(new BorderLayout(0, 0));
        
        //Inicia o painel geral
        final JPanel panel = new JPanel();
        this.contentPane.add(panel);
        panel.setLayout(new BorderLayout(0, 0));
        
        //Inicia o painel de card
        final JPanel cardPanel = new JPanel();
        panel.add(cardPanel);
        cardPanel.setLayout(new CardLayout(0, 0));
        
        //Inicia painel do menu (dentro do card painel)
        final JPanel MenuPanel = new JPanel();
        MenuPanel.setBorder(UIManager.getBorder("Button.border"));
        cardPanel.add(MenuPanel, "MenuPanel");
        
        //Inicia o painel que ficara o botao do novo jogo
        final JPanel newGamePanel = new JPanel();
        newGamePanel.setBorder(UIManager.getBorder("Button.border"));
        newGamePanel.setLayout(new BorderLayout(0, 0));
        
        //Botao novo jogo.
        final JButton botaoNovoJogo = new JButton("New game");
        newGamePanel.add(botaoNovoJogo);
        botaoNovoJogo.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final CardLayout cardLayout = (CardLayout)cardPanel.getLayout();
                cardLayout.show(cardPanel, "NewGamePanelScreen");
            }
        });
        botaoNovoJogo.setBackground(new Color(192, 192, 192));
        botaoNovoJogo.setFont(new Font("Minecraft Evenings", 0, 30));
        
        //Inicia o painel do botao de logar os saves
        final JPanel loadPanel = new JPanel();
        loadPanel.setBorder(UIManager.getBorder("Button.border"));
        loadPanel.setLayout(new BorderLayout(0, 0));
        final JButton botaoLoadGame = new JButton("Load game");
        loadPanel.add(botaoLoadGame, "Center");
        botaoLoadGame.setBackground(new Color(192, 192, 192));
        botaoLoadGame.setFont(new Font("Minecraft Evenings", 0, 30));
        botaoLoadGame.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final CardLayout cardLayout = (CardLayout)cardPanel.getLayout();
                cardLayout.show(cardPanel, "LoadGamePanelScreen");
            }
        });
        
        //Inicia o painel que fica o nome do jogo (apenas uma string)
        final JPanel gameNamePanel = new JPanel();
        gameNamePanel.setLayout(new BorderLayout(0, 0));
        
        //Inicia o painel do botao de saida
        final JPanel exitPanel = new JPanel();
        exitPanel.setBorder(UIManager.getBorder("Button.border"));
        final GroupLayout gl_MenuPanel = new GroupLayout(MenuPanel);
        gl_MenuPanel.setHorizontalGroup(gl_MenuPanel.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(gl_MenuPanel.createSequentialGroup().addGap(205).addComponent(loadPanel, -1, 323, 32767).addGap(109).addComponent(exitPanel, -2, -1, -2).addContainerGap()).addGroup(gl_MenuPanel.createSequentialGroup().addGap(5).addComponent(gameNamePanel, -1, 722, 32767).addGap(5)).addGroup(gl_MenuPanel.createSequentialGroup().addGap(177).addComponent(newGamePanel, -1, 394, 32767).addGap(161)));
        gl_MenuPanel.setVerticalGroup(gl_MenuPanel.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(gl_MenuPanel.createSequentialGroup().addGap(52).addComponent(gameNamePanel, -2, 137, -2).addGap(114).addComponent(newGamePanel, -2, 107, -2).addGap(27).addGroup(gl_MenuPanel.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(exitPanel, -2, -1, -2).addComponent(loadPanel, -2, -1, -2)).addGap(169)));      
        exitPanel.setLayout(new BorderLayout(0, 0));
        
        //Inicia o botao exit (que fecha o programa)
        final JButton botaoExit = new JButton("Exit");
        exitPanel.add(botaoExit);
        botaoExit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            	System.exit(0);
            }
        });
        botaoExit.setBackground(new Color(192, 192, 192));
        botaoExit.setFont(new Font("Minecraft Evenings", 0, 15));

        //Inicia o label que ficara a string do nome do jogo
        final JLabel LabelNomeJogo = new JLabel("GAME NAME");
        LabelNomeJogo.setHorizontalTextPosition(0);
        gameNamePanel.add(LabelNomeJogo);
        LabelNomeJogo.setForeground(new Color(0, 0, 0));
        LabelNomeJogo.setHorizontalAlignment(0);
        LabelNomeJogo.setFont(fontCustomizada);
        MenuPanel.setLayout(gl_MenuPanel);
        
        //Inicia o painel que ficara a tela de escolher o save
        final JPanel LoadGamePanelScreen = new JPanel();
        cardPanel.add(LoadGamePanelScreen, "LoadGamePanelScreen");
        
        //Botao que retornara ao menu inicial
        final JPanel ReturnButtonLoad = new JPanel();
        ReturnButtonLoad.setBorder(UIManager.getBorder("Button.border"));
        ReturnButtonLoad.setFont(new Font("Minecraft Evenings", 0, 15));
        
        JPanel painelBotoesSaves = new JPanel();
        
        //Incia o painel de entrar em algum save
        final GroupLayout gl_LoadGamePanelScreen = new GroupLayout(LoadGamePanelScreen);
        gl_LoadGamePanelScreen.setHorizontalGroup(
        	gl_LoadGamePanelScreen.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_LoadGamePanelScreen.createSequentialGroup()
        			.addGroup(gl_LoadGamePanelScreen.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_LoadGamePanelScreen.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(ReturnButtonLoad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_LoadGamePanelScreen.createSequentialGroup()
        					.addGap(35)
        					.addComponent(painelBotoesSaves, GroupLayout.PREFERRED_SIZE, 683, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(34, Short.MAX_VALUE))
        );
        gl_LoadGamePanelScreen.setVerticalGroup(
        	gl_LoadGamePanelScreen.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_LoadGamePanelScreen.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(ReturnButtonLoad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(1)
        			.addComponent(painelBotoesSaves, GroupLayout.PREFERRED_SIZE, 431, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(53, Short.MAX_VALUE))
        );
        


        
        //Lista de saves
        JPanel painelSaves = new JPanel();
        painelSaves.setLayout(new BoxLayout(painelSaves, BoxLayout.Y_AXIS));
        ArrayList<String> listanomes = new ArrayList<>();
        listanomes.add("nome1");
        listanomes.add("nome2");
        for (String nome: listanomes) {
            JPanel painelBotao = new JPanel();
            painelBotao.setPreferredSize(new Dimension(400, 50)); // Define o tamanho do painel
            painelBotao.setMaximumSize(new Dimension(400, 50)); // Define o tamanho máximo do painel
            painelBotao.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o painel horizontalmente
            
            JButton botaovar = new JButton(nome);
            botaovar.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o botão dentro do painel

            botaovar.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    Main.main(false, listanomes.indexOf(nome));
                }
            });
            
            painelBotao.add(botaovar); // Adiciona o botão ao painel
            painelSaves.add(painelBotao); // Adiciona o painel ao painelSaves
        }
        
        painelBotoesSaves.add(painelSaves, BorderLayout.CENTER);
        
        //Botao de retornar ao menu inicial
        final JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final CardLayout cardLayout = (CardLayout)cardPanel.getLayout();
                cardLayout.show(cardPanel, "MenuPanel");
            }
        });
        returnButton.setFont(new Font("Minecraft Evenings", 0, 15));
        ReturnButtonLoad.add(returnButton, "Center");
        LoadGamePanelScreen.setLayout(gl_LoadGamePanelScreen);
        
        //Painel de iniciar um novo jogo (onde se coloca o nome)
        final JPanel NewGamePanelScreen = new JPanel();
        cardPanel.add(NewGamePanelScreen, "NewGamePanelScreen");
        
        //Painel do botao de retornar ao menu
        final JPanel returnPanel = new JPanel();
        returnPanel.setBorder(UIManager.getBorder("Button.border"));
        
        //Painel onde fica o label do nome do save 
        final JPanel inputTextPanel = new JPanel();
        
        //Painel onde fica um texto explicando o painel acima para o usuario
        final JPanel stringNamePanel = new JPanel();
        stringNamePanel.setBorder(UIManager.getBorder("TitledBorder.border"));
        
        //Painel onde fica o botao de iniciar o jogo
        final JPanel PlayGameButton = new JPanel();
        PlayGameButton.setBorder(UIManager.getBorder("Button.border"));
        
        //Texto que aparece quando ocorre um erro no nome
        final JPanel TextWhenNameError = new JPanel();
        TextWhenNameError.setVisible(false);
        
        //Tela de iniciar o novo jogo
        final GroupLayout gl_NewGamePanelScreen = new GroupLayout(NewGamePanelScreen);
        gl_NewGamePanelScreen.setHorizontalGroup(gl_NewGamePanelScreen.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(gl_NewGamePanelScreen.createSequentialGroup().addGroup(gl_NewGamePanelScreen.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(gl_NewGamePanelScreen.createSequentialGroup().addContainerGap().addComponent(returnPanel, -2, -1, -2)).addGroup(gl_NewGamePanelScreen.createSequentialGroup().addGap(183).addGroup(gl_NewGamePanelScreen.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(inputTextPanel, -1, 383, 32767).addComponent(stringNamePanel, -2, -1, -2).addGroup(gl_NewGamePanelScreen.createSequentialGroup().addComponent(TextWhenNameError, -1, -1, 32767).addGap(316))))).addGap(182)).addGroup(gl_NewGamePanelScreen.createSequentialGroup().addGap(333).addComponent(PlayGameButton, -1, -1, 32767).addGap(316)));
        gl_NewGamePanelScreen.setVerticalGroup(gl_NewGamePanelScreen.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(gl_NewGamePanelScreen.createSequentialGroup().addContainerGap().addComponent(returnPanel, -2, -1, -2).addGap(165).addComponent(stringNamePanel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(inputTextPanel, -2, 42, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(TextWhenNameError, -2, 11, -2).addGap(18).addComponent(PlayGameButton, -2, -1, -2).addContainerGap(164, 32767)));
        TextWhenNameError.setLayout(new BorderLayout(0, 0));
        
        //String que aparece quando o nome esta incorreto
        final JLabel StringNameError = new JLabel("Write a name.");
        StringNameError.setHorizontalAlignment(0);
        StringNameError.setForeground(Color.RED);
        StringNameError.setFont(new Font("Arial", 0, 11));
        TextWhenNameError.add(StringNameError, "North");
        PlayGameButton.setLayout(new BorderLayout(0, 0));
        
        //Botao que inicia o jogo quando apertado
        final JButton IniciarJogo = new JButton("PLAY");
        IniciarJogo.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (SaveName.getText().isBlank()) {
                    TextWhenNameError.setVisible(true);
                } else
                {
                	Main.main(true, 0);
                }
            }
        });
        IniciarJogo.setFont(new Font("Minecraft Evenings", 0, 20));
        PlayGameButton.add(IniciarJogo);
        stringNamePanel.setLayout(new BorderLayout(0, 0));
        
        //Texto que explica o que se coloca na caixa de texto
        final JLabel lblNewLabel = new JLabel("Game Name:");
        lblNewLabel.setFont(new Font("Minecraft Evenings", 0, 15));
        stringNamePanel.add(lblNewLabel, "Center");
        inputTextPanel.setLayout(new BorderLayout(0, 0));
        (this.SaveName = new JTextField()).setFont(new Font("Arial", 0, 20));
        inputTextPanel.add(this.SaveName, "Center");
        this.SaveName.setColumns(10);
        returnPanel.setLayout(new BorderLayout(0, 0));
        final JButton botaoReturnMenu = new JButton("Return");
        
        
        botaoReturnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final CardLayout cardLayout = (CardLayout)cardPanel.getLayout();
                cardLayout.show(cardPanel, "MenuPanel");
            }
        });
        botaoReturnMenu.setFont(new Font("Minecraft Evenings", 0, 15));
        returnPanel.add(botaoReturnMenu, "Center");
        NewGamePanelScreen.setLayout(gl_NewGamePanelScreen);
    }
}