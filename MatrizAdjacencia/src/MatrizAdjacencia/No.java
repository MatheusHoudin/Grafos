/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatrizAdjacencia;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mathe
 */
public class No {
    private int valor;
    private int tempoInicial;
    private int tempoFinal;
    private int cor;
    private int ultIndiceLido;
    private int numIncidencias;
    private boolean removivel;

    private List<No> adjascentes;
    
    public No(){
        super();
        this.adjascentes = new ArrayList<>();
    }
    
    public No(int valor){
        this();
        this.valor = valor;
    }

    public boolean isRemovivel() {
        return removivel;
    }

    public void setRemovivel(boolean flag) {
        this.removivel = flag;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getTempoInicial() {
        return tempoInicial;
    }

    public void setTempoInicial(int tempoInicial) {
        this.tempoInicial = tempoInicial;
    }

    public int getTempoFinal() {
        return tempoFinal;
    }

    public void setTempoFinal(int tempoFinal) {
        this.tempoFinal = tempoFinal;
    }

    public int getCor() {
        return cor;
    }

    public int getUltIndiceLido() {
        return ultIndiceLido;
    }

    public void setUltIndiceLido(int ultIndiceLido) {
        this.ultIndiceLido = ultIndiceLido;
    }
    

    public void setCor(int cor) {
        this.cor = cor;
    }

    public List<No> getAdjascentes() {
        return adjascentes;
    }

    public int getNumIncidencias() {
        return numIncidencias;
    }

    public void setNumIncidencias() {
        this.numIncidencias++;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return valor; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Elemento:"+this.getValor()+" Inicio:"+this.getTempoInicial()+" Fim:"
                +this.getTempoFinal()+" Cor:"+this.getCor()+" Ultimo Indice lido:"+this.getUltIndiceLido();  //To change body of generated methods, choose Tools | Templates.
    }
    
    public void mostrarAdjacentes(){
        System.out.println("No: "+this.toString());
        adjascentes.forEach((adjascente) -> {
            System.out.println(adjascente);
        });
    }
}
