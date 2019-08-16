/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkk;

import java.io.IOException;
import model.Computador;
import model.ss;
import util.No;

/**
 *
 * @author casa
 */
public class NewClass {
    public static void main(String[] args) throws IOException{
        ss s= new ss();
        s.importarComputadores("files\\computadores.ascii");
          System.out.print("pc: ");
        No aux = s.getComputadores().getFirst();
        int i = 0;
        while (aux != null) {
            System.out.print(i + " chave: " + aux.getConteudo().getCapacidade());
            System.out.print("\tNome " + aux.getConteudo().getNome() + " \n");
            aux = aux.getNext();
            i++;
        }
        
        
        s.importarImagens("files\\imagens.ascii");
        System.out.println("Comptador : "+s.getComputadores().size());
        
        Computador cp= s.getRegistro().buscarImagem("aaf");
        Computador c= s.getRegistro().buscarImagem("adq");
        Computador t= s.getRegistro().buscarImagem("acw");
        Computador n= s.getRegistro().buscarImagem("ato");
        Computador kk= s.getRegistro().buscarImagem("bia");
        Computador x= s.getRegistro().buscarImagem("auw");
        
        System.out.println("\naaf- "+cp.getNome()); 
        System.out.print("\nadq- "+c.getNome());
        System.out.print("\n acw- "+t.getNome());
        System.out.print("\n ato- "+n.getNome());
        System.out.print("\n bia- "+kk.getNome());
        System.out.print("\n auw- "+x.getNome());
        
        
        System.out.print("pc: ");
        No au = s.getComputadores().getFirst();
        i = 0;
        while (au != null) {
            System.out.print(i + " chave: " + au.getConteudo().getEspacoDisponivel());
            System.out.print("\tNome " + au.getConteudo().getNome() + " \n");
            au = au.getNext();
            i++;
        }
        
        
    }
}
