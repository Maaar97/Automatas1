/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata1;

/**
 *
 * @author user
 */
public class NodoComp {
    
    private NodoComp anterior;
    private NodoComp siguiente;
    private Estado_q est1;
    private Estado_q est2;
    private boolean revisado=false;

    //constructor vacio
    public NodoComp() {
    }

    public boolean isRevisado() {
        return revisado;
    }

    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
    }

    public NodoComp(NodoComp anterior, NodoComp siguiente, Estado_q est1, Estado_q est2, boolean revisado) {
        this.anterior = anterior;
        this.siguiente = siguiente;
        this.est1 = est1;
        this.est2 = est2;
        this.revisado=revisado;
    }
    
    
    

    public NodoComp getAnterior() {
        return anterior;
    }

    public NodoComp getSiguiente() {
        return siguiente;
    }

    public Estado_q getEst1() {
        return est1;
    }

    public Estado_q getEst2() {
        return est2;
    }

    public void setAnterior(NodoComp anterior) {
        this.anterior = anterior;
    }

    public void setSiguiente(NodoComp siguiente) {
        this.siguiente = siguiente;
    }

    public void setEst1(Estado_q est1) {
        this.est1 = est1;
    }

    public void setEst2(Estado_q est2) {
        this.est2 = est2;
    }
    
    
    
    
    
}
