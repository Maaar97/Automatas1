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
public class Estado_q {
    private String nombre;
    private boolean isFinal, isInic;
    private Transiçao[] transiçaos;
    private short tamaño;
    private Estado_q estSig, estAnt;

    /**
     * Constructor de un estado con valores vacíos
     */
    public Estado_q() {
        this.nombre = "";
        this.isFinal = false;
        this.isInic = false;
        estAnt = null;
        estSig = null;
    }

    /**
     * Constructor de un estado con información detallada
     * @param nombre
     * @param isInic
     * @param isFinal 
     */
    public Estado_q(String nombre, boolean isInic, boolean isFinal) {
        this.nombre = nombre;
        this.isFinal = isFinal;
        this.isInic = isInic;
        estAnt = null;
        estSig = null;
    }
    
    /**
     * Crea el vector de transiciones del vector del mismo tamaño del alfabeto
     * @param tamaño 
     */
    public void InicVec(short tamaño){
        transiçaos = new Transiçao[tamaño];
        this.tamaño = tamaño;
    }
    
    /**
     * Añade una transicion en una posicion especifica acorde a una letra del alfabeto
     * @param transi
     * @param pos 
     */
    public void ingresarTrans(Transiçao transi, short pos){
        if(pos < tamaño){
            transiçaos[pos] = transi;
        }
    }
    
    /**
     * muestra las transiciones guardadas en este estado
     */
    public void verVector(){
        for(short i =0; i<transiçaos.length;i++){
            System.out.print(transiçaos[i]+ " ") ;
        }
    }

    /**
     * 
     * @return el nombre del estado
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @return verdadero si el estado es un estado final, falso si no
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     * 
     * @return verdadero si el estado es un estado inicial, falso si no
     */
    public boolean isInic() {
        return isInic;
    }

    /**
     * 
     * @return todas las transiciones posibles de este estado
     */
    public Transiçao[] getTransiçaos() {
        return transiçaos;
    }
    
    /**
     * busca una transicion en una posicion especifica (acorde a una letra del alfabeto)
     * @param posiçao
     * @return la transicion con esa letra si la hubiere, si no, valor nulo
     */
    public Transiçao getTransiçao(short posiçao) {
        if(posiçao < transiçaos.length)
            return transiçaos[posiçao];
        else
            return null;
    }

    public Estado_q getTransiçaosE(int pos) {
        return transiçaos[pos].getDestino();
    }
    /**
     * Verifica que las transiciones con cada una de las letras del alfabeto hayan sido ingresadas
     * @return verdadero si todas las transiciones fueron ingresadas, falso en caso contrario
     */
    public boolean vectorLleno(){
        for(short i = 0; i < transiçaos.length; i++){
            if(transiçaos[i]==null){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Obtiene el estado ingresado después del presente (Implementado en Estados_K)
     * @return 
     */
    public Estado_q getEstSig() {
        return estSig;
    }

    /**
     * Obtiene el estado ingresado antes del presente (Implementado en Estados_K)
     * @return 
     */
    public Estado_q getEstAnt() {
        return estAnt;
    }

    /**
     * Modifica el estado siguiente (implementado en Estados_K)
     * @param estSig 
     */
    public void setEstSig(Estado_q estSig) {
        this.estSig = estSig;
    }

    /**
     * modifica el estado anterior (implementado en Estados_K)
     * @param estAnt 
     */
    public void setEstAnt(Estado_q estAnt) {
        this.estAnt = estAnt;
    }
    
    /**
     * Muestra el nombre del estado y, dependiendo del caso, si es inicial, final o no
     * @return 
     */
    @Override
    public String toString() {
        String Estado = nombre;
        if(isInic == true)
            Estado += " (Inicial) ";
        if(isFinal == true)
            Estado += " (Final) ";
        return Estado;
    }
    
    
    
    
    public String toStringi(Estado_q estado) {
        
        return "Estado Inicial: "+estado.getNombre();
    }
    /**
     * Muestra las transiciones de un estado
     * @return 
     */
    public String estTrans(){
        String Estado = "";
        if(transiçaos!=null){
            for(short i = 0; i < tamaño; i++){
                if(transiçaos[i]!=null)
                    Estado += nombre + transiçaos[i].toString() + "     ";
                else
                    Estado += "NULO ";
            }
        }
        else
            Estado += "\nNULO";
        return Estado;
    }
}
