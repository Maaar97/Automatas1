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
public class Transiçao {
    private Estado_q destino;
    private Caracter trans;

    /**
     * Constructor de una transición, información detallada
     * @param destino
     * @param trans 
     */
    public Transiçao(Estado_q destino, Caracter trans) {
        this.destino = destino;
        this.trans = trans;
    }
    
    /**
     * constructor de una transición con valores nulos
     */
    public Transiçao() {
        this.destino = null;
        this.trans = null;
    }
    
    /**
     * 
     * @return el estado en donde termina la transicion
     */
    public Estado_q getDestino() {
        return destino;
    }

    /**
     * 
     * @return la letra con el que se realiza esta transicion
     */
    public Caracter getTrans() {
        return trans;
    }

    /**
     * modifica el estado en donde termina la transicion
     * @param destino 
     */
    public void setDestino(Estado_q destino) {
        this.destino = destino;
    }

    /**
     * modifica la letra con la que se realiza la transición
     * @param trans 
     */
    public void setTrans(Caracter trans) {
        this.trans = trans;
    }

    /**
     * Muestra la transicion realizada 
     * (letra del alfabeto con la que ocurre y estado en donde termina la transicion)
     * @return 
     */
    @Override
    public String toString() {
        if((trans!=null)&&(destino!=null))
            return "(" + trans.getCaracter() + ")->" + destino.getNombre();
        else if((trans==null)&&(destino==null))
            return "NULO";
        else
            return "";
    }
    
}
