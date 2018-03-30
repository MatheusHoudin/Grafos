/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatrizAdjacencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathe
 */


/*
Arquivo precisa seguir a seguinte regra
X - > Número de vertices total
A B ->
C D -> Adjascencias dos vertices, sendo que estas letras são números
R T ->

*/
public class TratadorArquivo {
    private File arquivo;
    private File arquivoSaida;
    private Scanner leitor;
    private FileWriter fw;
    private static BufferedWriter bw;
    
    public TratadorArquivo(){
        arquivo = new File("C:/Users/mathe/OneDrive/Documentos/NetBeansProjects/MatrizAdjacencia/src/Entrada.txt");
        arquivoSaida = new File("C:/Users/mathe/OneDrive/Documentos/NetBeansProjects/MatrizAdjacencia/src/Saida.txt");
        
        try {
            leitor = new Scanner(arquivo);
            fw = new FileWriter(arquivoSaida);
            bw = new BufferedWriter(fw);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TratadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TratadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<No> criarListaNo(){
        List<No> lista = null;
        if(leitor.hasNextInt()){
            int nVertices = leitor.nextInt();
            lista = criarLista(nVertices);
            
            while(leitor.hasNextInt()){
                int valor1 = leitor.nextInt();
                int valor2 = leitor.nextInt();
                
                No no1 = recuperarNoPeloValor(valor1, lista);
                No no2 = recuperarNoPeloValor(valor2, lista);
               
                
                try {
                    no1.getAdjascentes().add(no2);
                    no2.setNumIncidencias();
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        
        return lista;
    }
    
    private List<No> criarLista(int nVertices){
        List<No> lista = new ArrayList<>();
        for (int i = 0; i < nVertices; i++) {
            No no = new No(i);
            lista.add(no);
        }
        return lista;
    }
    
    private No recuperarNoPeloValor(int valor,List<No> lista){
        if(!lista.isEmpty()){
            for (No no : lista) {
                if(no.getValor()==valor){
                    return no;
                }
            }
        }
        return null;
    }
    
    public static void printarNoArquivo(String saida){
        try {
            bw.append(saida + "\n");
        } catch (IOException ex) {
            Logger.getLogger(TratadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
