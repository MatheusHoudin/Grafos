/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatrizAdjacencia;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author mathe
 */
public class MatAdjacencia {
    private TratadorArquivo tratadorArquivo;
    private int[][] matAdjacencia;
    private List<No> listaNo;
    private No noInicial = null;
    private Stack<No> pilhaNo;
    private Queue<No> filaNo;
    
    private int timer;
    
    public MatAdjacencia(){
        this.setTratadorArquivo(new TratadorArquivo());
        this.setListaNo(this.tratadorArquivo.criarListaNo());
        this.setMatAdjacencia(new int[listaNo.size()][listaNo.size()]);
        this.criarMatrizAdjacencia();
        this.setNoInicial(this.pegarNoComMaiorAdjacencia());
        this.setPilhaNo(new Stack<>());
        this.setFilaNo(new ArrayDeque<>());
    }

    public Queue<No> getFilaNo() {
        return filaNo;
    }

    public void setFilaNo(Queue<No> filaNo) {
        this.filaNo = filaNo;
    }

    public Stack<No> getPilhaNo() {
        return pilhaNo;
    }

    public void setPilhaNo(Stack<No> pilhaNo) {
        this.pilhaNo = pilhaNo;
    }

    public void setNoInicial(No noInicial) {
        this.noInicial = noInicial;
    }

    public No getNoInicial() {
        return noInicial;
    }

    private void criarMatrizAdjacencia(){
        for(No no:listaNo){
            for(No adjacente:no.getAdjascentes()){
                matAdjacencia[no.getValor()][adjacente.getValor()] = 1;
            }
        }
    }
    
    public void mostrarMatrizAdjacencias(){
        for(int i = 0;i<matAdjacencia.length;i++){
            System.out.print("No: "+i+" - ");
            for(int j = 0;j<matAdjacencia.length;j++){
                System.out.print("[ "+matAdjacencia[i][j]+" ]");
            }
            System.out.println("");
        }
    }
    
    private No pegarNoComMaiorAdjacencia(){
        No no = this.getListaNo().get(0);
        for(No noAux:this.getListaNo()){
            if(noAux.getNumIncidencias()>no.getNumIncidencias()){
                no = noAux;
            }
        }
        
        return no;
    }
    public TratadorArquivo getTratadorArquivo() {
        return tratadorArquivo;
    }

    public void setTratadorArquivo(TratadorArquivo tratadorArquivo) {
        this.tratadorArquivo = tratadorArquivo;
    }

    public int[][] getMatAdjacencia() {
        return matAdjacencia;
    }

    public void setMatAdjacencia(int[][] matAdjacencia) {
        this.matAdjacencia = matAdjacencia;
    }

    public List<No> getListaNo() {
        return listaNo;
    }

    public void setListaNo(List<No> listaNo) {
        this.listaNo = listaNo;
    }
    
    public void percorrerMatrizAdjacencia(){
        this.DFS();
    }
    
    public void DFS(){
        this.atualizarDadosCinza(this.noInicial);
        int i = this.noInicial.getValor();
        while(true){
            for(int j = this.getPilhaNo().lastElement().getUltIndiceLido();j<this.getMatAdjacencia().length;j++){
                if(this.getMatAdjacencia()[i][j] == 1){
                   No noColuna = this.pegarNoPorIndice(j);
                   if(noColuna.getCor()==Cor.BRANCO){
                       atualizarIndiceColunaERemovivel(false, j);
                       this.atualizarDadosCinza(noColuna);
                       i = noColuna.getValor();
                       break;
                   }else{
                       atualizarIndiceColunaERemovivel(true, j);
                   }
                }else{
                    atualizarIndiceColunaERemovivel(true, j);
                }
            }
            
            if(!this.getPilhaNo().isEmpty() && this.getPilhaNo().lastElement().isRemovivel()){
                No noRemovido = this.atualizarDadosPreto();
//                System.out.println("---------------------------------------------------------------");
//                System.out.println("REMOVIDO");
//                System.out.println(noRemovido);
//                System.out.println("---------------------------------------------------------------");
                if(!this.getPilhaNo().isEmpty()){
                    i = this.getPilhaNo().lastElement().getValor();
                }
            }
            if(this.getPilhaNo().isEmpty()){
                No proxNoBranco = this.obterProxNoBranco();
                if(proxNoBranco == null){
                    System.out.println("------------FIM FO ALGORITMO----------");
                    break;
                }else{
                    this.atualizarDadosCinza(proxNoBranco);
                    i = proxNoBranco.getValor();
                }
            }
        }
    }
    
    private void atualizarIndiceColunaERemovivel(boolean removivel,int indiceColuna){
        this.getPilhaNo().lastElement().setRemovivel(removivel);
        this.getPilhaNo().lastElement().setUltIndiceLido(indiceColuna);
    }
    
    private void atualizarDadosCinza(No no){
        this.timer++;
        no.setTempoInicial(timer);
        no.setCor(Cor.CINZA);
        this.getPilhaNo().push(no);
    }
    
    private No atualizarDadosPreto(){
        No noRemovido = this.getPilhaNo().pop();
        this.timer++;
        noRemovido.setTempoFinal(timer);
        noRemovido.setCor(Cor.PRETO);
        this.getFilaNo().add(noRemovido);
        return noRemovido;
        
    }
    
    private No pegarNoPorIndice(int indice){
        return this.getListaNo().get(indice);
    }
    
    private No obterProxNoBranco(){
        for(No no:this.getListaNo()){
            if(no.getCor()==Cor.BRANCO){
                return no;
            }
        }
        return null;
    }
    
    public void gravarResultado(){
        for(No no:this.getListaNo()){
            String resultado = no.getValor()+": "+"("+no.getTempoInicial()+"/"+no.getTempoFinal()+")";
            //TratadorArquivo.printarNoArquivo(resultado);
            System.out.println(resultado);
        }
    }
    
    public void tamListas(){
        for(No no:this.getListaNo()){
            System.out.println("No:"+no.getValor()+" N:"+no.getNumIncidencias());
        }
    }
    
}
