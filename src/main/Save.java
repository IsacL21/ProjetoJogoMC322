package main;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

public class Save {
	
	 public static void salvar(int numero, String nomeArquivo) {
		 String caminho = "res/save/";
		 String adress = caminho + nomeArquivo;
	        try {
	        	 FileWriter fileWriter = new FileWriter(adress);

	             // Cria um BufferedWriter a partir do FileWriter
	             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	             // Escreve o número inteiro no arquivo
	             bufferedWriter.write(Integer.toString(numero));

	             // Fecha o BufferedWriter (isso também fecha o FileWriter)
	             bufferedWriter.close();

	             System.out.println("Arquivo gerado com sucesso.");
	             
	        }
			catch (IOException e) {
	            System.out.println("Erro ao escrever o inteiro no arquivo: " + e.getMessage());
	        }
	    }
	 
	public static ArrayList<String> listar_saves() {
		ArrayList<String> lista_saves = new ArrayList<String>(); 
		Path diretorio = Paths.get("res/save");

	    // Verifica se é um diretório válido
	    if (Files.isDirectory(diretorio)) {
	        // Obtém o diretório e cria um fluxo para iterar sobre os arquivos
	        try (DirectoryStream<Path> stream = Files.newDirectoryStream(diretorio)) {
	            	
	                // Itera sobre os arquivos no diretório
	            for (Path arquivo : stream) {
	                	
	                if (Files.isRegularFile(arquivo)) {
	                    	
	                    // Obtém o nome do arquivo
	                    String nomeArquivo = arquivo.getFileName().toString();
	                    lista_saves.add(nomeArquivo);
	                }
	            }
	        }
			catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return lista_saves;
	 }
	 
	 public static int define_mapa(boolean new_game, String nome) {
		 // Seleciona o mapa a ser renderizado, com base no fato de ser um novo jogo ou não ou no save dado como parâmetro
		 int mapa = 0;
		 if (new_game == true) {
			 return mapa;
		 }
		 else {
			String caminho = "res/save/" + nome;
			try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
		        String linha = reader.readLine();		            
		        mapa = Integer.parseInt(linha);	             
		        }
				catch (IOException e) {
		            e.printStackTrace();
		        }
			return mapa;
		 }
	 }
}

