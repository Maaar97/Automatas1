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
public class Alfabeto {
    private Caracter inicio, fin;
    private short tamaño;

    /**
     * Constructor del alfabeto, valores vacíos
     */
    public Alfabeto() {
        this.inicio = new Caracter();
        inicio.setCarSig(fin);
        this.fin = new Caracter();
        fin.setCarAnt(inicio);
        this.tamaño = 0;
    }
    
    /**
     * Agrega un nuevo elemento al alfabeto, a menos que sea un elemento que ya esté ingresado
     * @param letra
     * @throws ExcepcionCaracterYaExiste 
     */
    public void AgregarCar(char letra) throws ExcepcionCaracterYaExiste{
        if(tamaño==0){
            Caracter nuevo = new Caracter(letra, fin, inicio);
            inicio.setCarSig(nuevo);
            fin.setCarAnt(nuevo);
            tamaño++;
        }
        else{
            Caracter iterador = inicio.getCarSig();
            while((iterador.getCarSig()!=fin)&&(letra!=iterador.getCaracter())){
                iterador = iterador.getCarSig();
            }
            if(letra==iterador.getCaracter()){
                throw new ExcepcionCaracterYaExiste("El caracter ingresado ya existe, ingrese uno diferente");
            }
            Caracter nuevo = new Caracter(letra, fin, iterador);
            iterador.setCarSig(nuevo);
            fin.setCarAnt(nuevo);
            tamaño++;
        }
    }

    /**
     * 
     * @return el límite próximoa al primer elemento ingresado al alfabeto
     */
    public Caracter getInicio() {
        return inicio;
    }

    /**
     * 
     * @return el límite próximoa al último elemento ingresado al alfabeto
     */
    public Caracter getFin() {
        return fin;
    }

    /**
     * 
     * @return el tamaño del alfabeto
     */
    public short getTamaño() {
        return tamaño;
    }
    
    /**
     * busca un elemento del alfabeto acorde a una posición específica
     * @param posicion
     * @return el elemento del alfabeto (letra) guardado en la posición requerida
     * @throws ExcepcionCaracterNoExiste 
     */
    public Caracter getChar(short posicion) throws ExcepcionCaracterNoExiste{
        if(posicion >= tamaño)
            throw new ExcepcionCaracterNoExiste("Posicion mayor al tamaño del alfabeto, ingrese uno diferente");
        Caracter iterador = inicio.getCarSig();
        for(short cont = 0; cont < posicion; cont++){
            iterador = iterador.getCarSig();
        }
        return iterador;
    }
    
    public char getChar2(short posicion) throws ExcepcionCaracterNoExiste{
        if(posicion >= tamaño)
            throw new ExcepcionCaracterNoExiste("Posicion mayor al tamaño del alfabeto, ingrese uno diferente");
       Caracter iterador = inicio.getCarSig();
        for(short cont = 0; cont < posicion; cont++){
            iterador = iterador.getCarSig();
        }
        return iterador.getCaracter();
    }
    
    /**
     * muestra cada elemento guardado en el alfabeto
     * @return 
     */
    @Override
    public String toString(){
        Caracter iterador = inicio.getCarSig();
        String caracteres = "";
        while(iterador!=fin){
            if(iterador.getCarSig()!=fin)
                caracteres += iterador.getCaracter() + "-";
            else
                caracteres += iterador.getCaracter();
            iterador = iterador.getCarSig();
        }
        return caracteres;
    }
}
