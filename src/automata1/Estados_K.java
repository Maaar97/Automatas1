/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata1;

import Excepciones.*;

/**
 *
 * @author user
 */
public class Estados_K {
    private Estado_q inicio, fin;
    private short tamaño;

    /**
     * Constructor del conjunto de estados, valores vacíos
     */
    public Estados_K() {
        this.inicio = new Estado_q();
        this.fin = new Estado_q();
        inicio.setEstSig(fin);        
        fin.setEstAnt(inicio);
        tamaño = 0;
    }
    
    /**
     * Agrega un nuevo estado al conjunto del autómata
     * @param estado
     * @throws ExcepcionEstadoYaExiste 
     */
    public void AgregarEst(Estado_q estado) throws ExcepcionEstadoYaExiste{
        if(tamaño==0){
            estado.setEstAnt(inicio);
            estado.setEstSig(fin);
            inicio.setEstSig(estado);
            fin.setEstAnt(estado);
            tamaño++;
        }
        else{
            Estado_q iterador = inicio.getEstSig();
            while((iterador.getEstSig()!=fin)&&(estado.getNombre()!=iterador.getNombre())){
                iterador = iterador.getEstSig();
            }
            if(estado.getNombre().equals(iterador.getNombre())){
                throw new ExcepcionEstadoYaExiste("El estado ingresado ya existe, ingrese uno diferente");
            }
            iterador.setEstSig(estado);
            estado.setEstAnt(iterador);
            estado.setEstSig(fin);
            fin.setEstAnt(estado);
            tamaño++;
        }
    }
    
    /**
     * 
     * @return el límite próximo al primer elemento ingresado al conjunto
     */
    public Estado_q getInicio() {
        return inicio;
    }

    /**
     * 
     * @return el límite próximo al último elemento ingresado al conjunto
     */
    public Estado_q getFin() {
        return fin;
    }
        
    /**
     * 
     * @return la cantidad de estados ingresados en el conjunto
     */
    public short getTamaño() {
        return tamaño;
    }
    
    /**
     * Muestra, en el orden ingresado, los estados del conjunto
     * @return 
     */
    @Override
    public String toString(){
        String Estados = "";
        Estado_q iterador = inicio.getEstSig();
        while(iterador!=fin){
            if(iterador.getEstSig()!=fin)
                Estados += iterador.toString() + " - ";
            else
                Estados += iterador.toString();
            iterador = iterador.getEstSig();
        }
        return Estados;
    }
    
    /**
     * Muestra cada estado del conjunto con sus transiciones
     * @return 
     */
    public String estadosTransicion(){
        String transis = "";
        Estado_q iterador = inicio.getEstSig();
        while(iterador != fin){
            if(iterador.getEstSig() != fin)
                transis += iterador.estTrans() + "\n";
            else
                transis += iterador.estTrans();
            iterador = iterador.getEstSig();
        }
        return transis;
    }
}
