/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata1;
import Excepciones.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ListaComp {
    private NodoComp inicio;
    private NodoComp fin;
    private short tamaño=0;

    public short getTamaño() {
        return tamaño;
    }

    public void setTamaño(short tamaño) {
        this.tamaño = tamaño;
    }

    
           
           
    public void setInicio(NodoComp inicio) {
        this.inicio = inicio;
    }

    public void setFin(NodoComp fin) {
        this.fin = fin;
    }

    public NodoComp getInicio() {
        return inicio;
    }

    public NodoComp getFin() {
        return fin;
    }

    
    
    public ListaComp() {
        this.inicio = new NodoComp();
        this.fin = new NodoComp();
        inicio.setSiguiente(fin);        
        fin.setAnterior(inicio);
        this.tamaño=0;
        
    }
    
    public void insertar(NodoComp nuevo){
        nuevo.setAnterior(fin.getAnterior());
        fin.getAnterior().setSiguiente(nuevo);
        nuevo.setSiguiente(fin);
        fin.setAnterior(nuevo);
        
    }
    
    public void precomparacion(Automata automata1, Automata automata2){
        Estado_q estadoinic1 = new Estado_q();
        Estado_q estadoinic2 = new Estado_q(); 
        
         try {
            estadoinic1=automata1.getInicial();
            estadoinic2 = automata2.getInicial();
            
            NodoComp nuevo = new NodoComp(null,null,estadoinic1,estadoinic2, false);
            insertar(nuevo);
            System.out.print("Estado inicial, Automata 1: " + estadoinic1.toString());
            System.out.print("Estado inicial, Automata 2: " + estadoinic2.toString());
            short tam=automata1.getAlfabeto().getTamaño();
            comparacion(nuevo,tam);
            
        } catch (ExcepcionNoHayInicial ex) {
            Logger.getLogger(MatrizComparaciones.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public void comparacion(NodoComp nuevo,short tam){
        
        Estado_q es1=new Estado_q();
        Estado_q es2=new Estado_q();
        
        if(nuevo.getEst1().isFinal()== nuevo.getEst2().isFinal()){
            short cont=0;
            
            for(short i=0;i<tam;i++){
                
               es1=nuevo.getEst1().getTransiçao(i).getDestino();
               es2=nuevo.getEst2().getTransiçao(i).getDestino();
               
               NodoComp temp=new NodoComp(null, null, es1,es2,false);
               insertar(temp);
            }
            
            NodoComp iterador = inicio.getSiguiente();
            while(iterador!=fin){
//                if((iterador.getEst1().getNombre()!=nuevo.getEst1().getNombre())&&(iterador.getEst2().getNombre()!=nuevo.getEst2().getNombre())){
//                    iterador=iterador.getSiguiente();
//                }
                if((iterador.getEst1().getNombre()==nuevo.getEst1().getNombre())&&(iterador.getEst2().getNombre()==nuevo.getEst2().getNombre())){
                    iterador.setRevisado(true);
                }
                iterador=iterador.getSiguiente();
            }
            
            nuevo.setRevisado(true);
            
            NodoComp iterador2 = inicio.getSiguiente();
            while(iterador2!=fin){
                if(iterador2.isRevisado()==false){
                    NodoComp iterador3 = inicio.getSiguiente();
                     while(iterador3!=fin){
                         if((iterador3.isRevisado()==true) && (iterador3.getEst1().getNombre()==iterador2.getEst1().getNombre())
                                 &&(iterador3.getEst2().getNombre()==iterador2.getEst2().getNombre())){
                             iterador2.setRevisado(true);
                         }
                         iterador3=iterador3.getSiguiente();
                     }
                     if(iterador2.isRevisado()==false){
                        comparacion(iterador2, tam); 
                        break;
                     }
                     
                }
                else{
                    iterador2=iterador2.getSiguiente();
                }
            }
            
            
            System.out.print("son equivalentes");
            
        }
        else
            System.out.print("no son equivalentes");
        
        
    }
    
    
}
