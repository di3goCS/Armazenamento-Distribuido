/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import util.FilaDeComputadores;
import util.Tree;

/**
 *
 * @author casa
 */
public class ss {
       private Tree registro;
    private FilaDeComputadores computadores;

    public ss() {
        registro = new Tree();
        computadores = new FilaDeComputadores();
    }

    public Tree getRegistro() {
        return this.registro;
    }

    public FilaDeComputadores getComputadores() {
        return this.computadores;
    }

    /*public void importarImagens(String nomeArquivo) throws FileNotFoundException {
        FileReader arquivo = new FileReader(nomeArquivo);
        try (BufferedReader ler = new BufferedReader(arquivo)) {
            Imagem img;
            String nome;
            String linha = ler.readLine();
            while (linha != null) {
                    nome= linha;
                    linha = ler.readLine();
                    Double tamanho = Double.parseDouble(linha);
                    img = new Imagem(nome, tamanho);
                    registro.add(img);
                    this.addImagemAoComputador(img);
                    linha = ler.readLine();
            }
            ler.close();
        } catch (IOException ioe) {
            java.lang.System.out.println("Arquivo não encontrado.Tente novamente");
        }
    }*/
    public void addImagemAoComputador(Imagem img) throws NullPointerException, FileNotFoundException{
        //FAZER EXCEÇÃO CASO NÃO HAJA COMPUTADORES
        try{
            registro.add(img);
            computadores.get(0).addImagem(img);
            Computador removido = computadores.dequeue();
            computadores.addComputador(removido);
        }
        catch(NullPointerException ex){
            importarComputadores("files//computadores.ascii");
        }
 }  
        
    
    public int importarImagens(String nome_arq) throws IOException {
        BufferedReader br = null;
        int contador = 0;
        try{		
            br = new BufferedReader(new FileReader(nome_arq));
            while(br.ready()){
                String linha = br.readLine();
                String[] parts = linha.split(" ");
                String nome = parts[0];
                double tamanho = Double.parseDouble(parts[1]);
                Imagem nova = new Imagem(nome, tamanho);
                
                this.addImagemAoComputador(nova);
                contador++;
            }
            br.close();
        }
        catch(IOException ioe){
            java.lang.System.out.println("Arquivo não encontrado.Tente novamente");
        }
        catch(NullPointerException ex){
            importarComputadores("files//computadores.ascii");
        }
        finally{
            if (br != null)
                br.close();
            return contador;
        }
    }

   /* public int importarComputadores(String nome_arq) throws IOException {
        BufferedReader br = null;
        int contador = 0;
        try{		
            br = new BufferedReader(new FileReader(nome_arq));
            while(br.ready()){
                String linha = br.readLine();
                String[] parts = linha.split(" ");
                String nome = parts[0];
                Double capacidade = Double.parseDouble(parts[1]);
                Computador novo = new Computador(nome, capacidade);
                computadores.add(novo);
                contador++;
            }
            br.close();
        }
        catch(IOException ioe){
            java.lang.System.out.println("Arquivo não encontrado. Tente novamente");
        }
        finally{
            if(br != null)
                br.close();
            return contador;
        }
    }*/
     public void importarComputadores(String nomeArquivo) throws FileNotFoundException {
        FileReader arquivo = new FileReader(nomeArquivo);
        try {
            BufferedReader ler = new BufferedReader(arquivo);
            Computador computador;
            String nome;
            String linha = ler.readLine();
            while (linha != null) {
                    nome= linha;
                    linha = ler.readLine();
                    Double capacidade = Double.parseDouble(linha);
                    computador= new Computador(nome,capacidade);
                    computadores.add(computador);
                    linha = ler.readLine();
            }
            ler.close();
        } catch (IOException excecao) {
            java.lang.System.out.println("Arquivo não encontrado. Tente novament");
        }
    }


}
