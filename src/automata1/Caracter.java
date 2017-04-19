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
public class Caracter {
    private char caracter;
    private Caracter CarSig, CarAnt;

    /**
     * Constructor del caracter, valores nulos
     */
    public Caracter() {
        this.caracter = '\0';
        this.CarSig = null;
        this.CarAnt = null;
    }

    /**
     * Constructor del caracter, informacion detallada
     * @param caracter
     * @param siguiente
     * @param anterior 
     */
    public Caracter(char caracter, Caracter siguiente, Caracter anterior) {
        this.caracter = caracter;
        this.CarSig = siguiente;
        this.CarAnt = anterior;
    }

    /**
     * 
     * @return el caracter almacenado
     */
    public char getCaracter() {
        return caracter;
    }

    /**
     * 
     * @return el caracter siguiente (Implementado en Alfabeto)
     */
    public Caracter getCarSig() {
        return CarSig;
    }

    /**
     * 
     * @return el caracter anterior (Implementado en Alfabeto)
     */
    public Caracter getCarAnt() {
        return CarAnt;
    }

    /**
     * modifica el caracter almacenado
     * @param caracter 
     */
    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }
    
    /**
     * modifica el caracter siguiente (Implementado en Alfabeto)
     * @param CarSig 
     */
    public void setCarSig(Caracter CarSig) {
        this.CarSig = CarSig;
    }

    /**
     * modifica el caracter anterior (Implementado en Alfabeto)
     * @param CarAnt 
     */
    public void setCarAnt(Caracter CarAnt) {
        this.CarAnt = CarAnt;
    }

    /**
     * 
     * @return el caracter
     */
    @Override
    public String toString() {
        return caracter + "";
    }
    
}
