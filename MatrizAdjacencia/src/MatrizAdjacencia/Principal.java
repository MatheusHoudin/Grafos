/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatrizAdjacencia;

/**
 *
 * @author mathe
 */
public class Principal {
    public static void main(String[] args) {
        MatAdjacencia matrizAdjacencia = new MatAdjacencia();
        System.out.println(matrizAdjacencia.getMatAdjacencia().length);
//        for(No no: matrizAdjacencia.getListaNo()){
//            no.mostrarAdjacentes();
//        }
        
        //matrizAdjacencia.mostrarMatrizAdjacencias();
        System.out.println(matrizAdjacencia.getNoInicial().getValor());
        System.out.println("--------------------");
        long tempoInicial = System.currentTimeMillis();
        matrizAdjacencia.DFS();
        long tempoFinal = System.currentTimeMillis();
        matrizAdjacencia.gravarResultado();
        System.out.println("-------------------------------------------------------------");
        matrizAdjacencia.tamListas();
        System.out.println("-------------------------------------------------------------");
        System.out.println("TEMPO");
        System.out.println(tempoFinal-tempoInicial);
        //matrizAdjacencia.gravarResultado();
        //System.out.println("FINALIZADO");
    }
}
