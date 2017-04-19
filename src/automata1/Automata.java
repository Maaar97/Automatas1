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
public class Automata {
    Estados_K estados;
    Alfabeto alfabeto;

    /**
     * Constructor del automata, genera su conjunto de estados y su alfabeto (ambos vacíos)
     */
    public Automata() {
        estados = new Estados_K();
        alfabeto = new Alfabeto();
    }
    
    /**
     * busca el estado inicial del autómata (no necesariamente el primer estado ingresado)
     * @return el estado inicial del autómata
     * @throws ExcepcionNoHayInicial 
     */
    public Estado_q getInicial() throws ExcepcionNoHayInicial{
        Estado_q iterador = estados.getInicio().getEstSig();
        while((iterador.isInic()!=true)&&(iterador!=estados.getFin())){
            iterador=iterador.getEstSig();
        }
        if(iterador==estados.getFin())
            throw new ExcepcionNoHayInicial("No se ha encontrado un estado inicial");
        return iterador;
    }
    
    
    
    
    public boolean verificarInicial(Estado_q nuevo){
        Estado_q iterador = estados.getInicio().getEstSig();
        if(estados.getTamaño()>0){
            while(iterador != estados.getFin()){
                if((iterador.isInic()==true)&&(nuevo.isInic()==true)){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 
     * @return el conjunto de estados del autómata (completo)
     */
    public Estados_K getEstados() {
        return estados;
    }

    /**
     * 
     * @return el alfabeto que maneja el autómata
     */
    public Alfabeto getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(Alfabeto alfabeto) {
        this.alfabeto = alfabeto;
    }

    public void setEstados(Estados_K estados) {
        this.estados = estados;
    }
    
    
    /**
     * añade un nuevo elemento al alfabeto del autómata
     * @param caracter
     * @throws ExcepcionCaracterYaExiste 
     */
    public void agregaCar(char caracter) throws ExcepcionCaracterYaExiste{
        alfabeto.AgregarCar(caracter);
    }
    
    /**
     * agrega un nuevo estado al conjunto de estados del autómata
     * @param estado
     * @throws ExcepcionEstadoYaExiste 
     */
    public void agregaEst(Estado_q estado) throws ExcepcionEstadoYaExiste{
        estados.AgregarEst(estado);
    }
    
    /**
     * crea los vectores de transiciones de cada estado del autómata
     */
    public void iniciaVec(){
        Estado_q iterador = estados.getInicio().getEstSig();
        while(iterador!=estados.getFin()){
            iterador.InicVec(alfabeto.getTamaño());
            iterador = iterador.getEstSig();
        }
        System.out.print("Vectores Inicializados\n");
    }
    
    /**
     * Agrega una transicion a un estado específico
     * dependiendo de la letra se añade en el vector de transiciones
     * @param estado
     * @param posi
     * @param transi
     * @throws ExcepcionEstadoNoExiste 
     */
    public void llenarTransis(Estado_q estado, short posi, Transiçao transi) throws ExcepcionEstadoNoExiste{
        estado.ingresarTrans(transi, posi);
    }
    
    /**
     * busca en el estado especificado si existe una transicion ingresada con la letra recibida
     * @param estado
     * @param posicion
     * @return nulo si esa transicion no existe, la transición si existiese
     * @throws ExcepcionEstadoNoExiste
     * @throws ExcepcionCaracterNoExiste 
     */
    public Transiçao buscarTrans(Estado_q estado, short posicion) throws ExcepcionEstadoNoExiste, ExcepcionCaracterNoExiste{
        Transiçao transis = estado.getTransiçao(posicion);
        if(transis!=null){
            return transis; 
        }
        return null;
    }
    
    /**
     * comprueba si todas las transiciones de todos los estados han sido ingresadas
     * @return verdadero, si no falta ninguna transicion por añadir; falso si falta al menos 1
     */
    public boolean verificarTransiçaos(){
        Estado_q iterador = estados.getInicio().getEstSig();
        while(iterador!=estados.getFin()){
            if(iterador.vectorLleno()==false)
                return false;
            iterador = iterador.getEstSig();
        }
        return true;
    }
    
    /**
     * muestra todos los estados del conjunto con sus respectivas transiciones
     * @return 
     */
    public String mostrarTransiciones(){
        String transis = estados.estadosTransicion();
        return transis;
    }
    
    /**
     * Búsqueda de un estado según el nombre ingresado
     * @param est
     * @return el estado que se desee buscar
     * @throws ExcepcionEstadoNoExiste 
     */
    public Estado_q getEstado(String est) throws ExcepcionEstadoNoExiste{
        Estado_q iterador = estados.getInicio().getEstSig();
        while(iterador!=estados.getFin()){
            if(iterador.getNombre().equals(est)){
                return iterador;
            }
            iterador = iterador.getEstSig();
        }
        if((iterador==estados.getFin())){
            throw new ExcepcionEstadoNoExiste("El estado que busca no existe, ingrese uno diferente");
        }
        return estados.getFin();
    }
    
    /**
     * Búsqueda de un estado a partir de la posición ingresada
     * @param posi
     * @return estado del autómata
     * @throws ExcepcionEstadoNoExiste 
     */
    public Estado_q getEstado(short posi) throws ExcepcionEstadoNoExiste{
        Estado_q iterador = estados.getInicio().getEstSig();
        short cont = 0;
        while(iterador!=estados.getFin()){
            if(cont == posi){
                return iterador;
            }
            cont++;
            iterador = iterador.getEstSig();
        }
        if(cont>=estados.getTamaño()){
            throw new ExcepcionEstadoNoExiste("El estado que busca no existe, ingrese uno diferente");
        }
        return estados.getFin();
    }
    
    /**
     * Búsqueda de un estado según el nombre especificado
     * @param nombre
     * @return posición del estado a buscar
     * @throws ExcepcionEstadoNoExiste 
     */
    public short posiEst(String nombre) throws ExcepcionEstadoNoExiste{
        short contador = 0;
        Estado_q iterador = estados.getInicio().getEstSig();
        while(iterador!=estados.getFin()){
            if(iterador.getNombre().equals(nombre)){
                return contador;
            }
            contador++;
            iterador = iterador.getEstSig();
        }
        if((iterador==estados.getFin())){
            throw new ExcepcionEstadoNoExiste("El estado que busca no existe, ingrese uno diferente");
        }
        return contador;
    }
    
    /**
     * Muestra todos los estados almacenados en el autómata
     * @return 
     */
    public String mostrarEstados(){
        String ests = estados.toString();
        return ests;
    }
    
    /**
     * Muestra el alfabeto del autómata
     * @return 
     */
    public String mostrarAlfabeto(){
        String alfa = alfabeto.toString();
        return alfa;
    }
    
    /**
     * Función que busca el elemento del alfabeto especificado
     * @param letra
     * @return elemento del alfabeto deseado
     * @throws ExcepcionCaracterNoExiste 
     */
    public Caracter getCaracter(char letra) throws ExcepcionCaracterNoExiste{
        Caracter iterador = alfabeto.getInicio().getCarSig();
        while((iterador.getCarSig()!=alfabeto.getFin())&&(letra!=iterador.getCaracter())){
            iterador = iterador.getCarSig();
        }
        if((letra!=iterador.getCaracter())||(iterador==alfabeto.getFin())){
            throw new ExcepcionCaracterNoExiste("El caracter que busca no existe, ingrese uno diferente");
        }
        return iterador;
    }
    
    /**
     * Función para obtener el elemento del alfabeto en la posición especificada
     * @param posi
     * @return elemento del alfabeto en la posición deseada
     * @throws ExcepcionCaracterNoExiste 
     */
    public Caracter getCaracter(short posi) throws ExcepcionCaracterNoExiste{
        Caracter iterador = alfabeto.getInicio().getCarSig();
        short cont = 0;
        while(iterador.getCarSig()!=alfabeto.getFin()){
            if(cont == posi)
                return iterador;
            cont++;
            iterador = iterador.getCarSig();
        }
        if(cont >= alfabeto.getTamaño()){
            throw new ExcepcionCaracterNoExiste("El caracter que busca no existe, ingrese uno diferente");
        }
        return alfabeto.getFin();
    }
    
    /**
     * Función para obtener la posición en la lista de una letra en específico
     * @param letra
     * @return posición en la lista de la letra deseada
     * @throws ExcepcionCaracterNoExiste 
     */
    public short posiCar(char letra) throws ExcepcionCaracterNoExiste{
        short contador = 0;
        Caracter iterador = alfabeto.getInicio().getCarSig();
        while(iterador.getCarSig()!=alfabeto.getFin()){
            if(iterador.getCaracter()==letra){
                return contador;
            }
            contador++;
            iterador = iterador.getCarSig();
        }
        if((letra!=iterador.getCaracter())||(iterador==alfabeto.getFin())){
            throw new ExcepcionCaracterNoExiste("El caracter que busca no existe, ingrese uno diferente");
        }
        return contador;
    }    
}
