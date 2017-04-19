/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata1;

import Excepciones.ExcepcionNoHayInicial;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hugolucas
 */
public class MatrizComparaciones {
    
    private Estado_q est1;
    private Estado_q est2;
    private boolean revisado;
    MatrizComparaciones[][] matrix;
    
    public MatrizComparaciones() {
        revisado=false;
        est1=null;
        est2=null;
    }

    
    
    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
    }

    public boolean isRevisado() {
        return revisado;
    }
    
    public void crearMatriz(short filas, short columnas){
        matrix = new MatrizComparaciones[filas][columnas];
        System.out.println("Matriz creada (Y)");
    }
    
    //MatrizComparaciones matrizA[][]=new MatrizComparaciones[11][11];
    //MatrizComparaciones matrizB[][]=new MatrizComparaciones[11][11];
    
    //esto es el tamaño del alfabeto o vector transiciones
    
    //el tamaño que va a tener la fila de pareja de estados
     
    public Estado_q getEst1() {
        return est1;
    }

    public Estado_q getEst2() {
        return est2;
    }

    public void setEst1(Estado_q est1) {
        this.est1 = est1;
    }

    public void setEst2(Estado_q est2) {
        this.est2 = est2;
    }
     
    
    
    
   /*public void insertarMA(Estado_q est1, Estado_q est2){
        for(int i=0;i<=4;i++){
            for(int j=0;j<=4;j++){
                
            }
        }
    }
   
   public void insertarMB(Estado_q est1, Estado_q est2){
        for(int i=0;i<=4;i++){
            for(int j=0;j<=4;j++){
                
            }
        }
    }*/
    
    public void inicialesMatrix(Automata automata1, Automata automata2){
        
       // matrix = new MatrizComparaciones[11][11];
        
        Estado_q estadoinic1;
        Estado_q estadoinic2;
        try {
            estadoinic1=automata1.getInicial();
            estadoinic2 = automata2.getInicial();
            
            System.out.print("Estado inicial, Automata 1: " + estadoinic1.toString());
            System.out.print("Estado inicial, Automata 2: " + estadoinic2.toString());
        
        matrix[0][0].setEst1(estadoinic1);
        matrix[0][0].setEst2(estadoinic2);
        //el tamaño del alfabeto, para poder recorrer la matriz en el siguiente metodo
        short tam=automata1.getAlfabeto().getTamaño();
        
         
        compararMatrix(matrix[0][0].getEst1(),matrix[0][0].getEst2(),0,tam,2);
        
        } catch (ExcepcionNoHayInicial ex) {
            Logger.getLogger(MatrizComparaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public void compararMatrix(Estado_q es1, Estado_q es2, int filest,short tam,int tamañoEsta){
        //comparando si son estados fainales o indistinguibles
        if(es1.isFinal()==es2.isFinal()){
            //contador para vecto de alfabeto o transiciones
            int con=0;
            //si son inidistinguibles los estados, se insertan en la matriz sus estados destinos respecto a transiciones
            for(int cont=1;cont<=(int)tam;cont++){
                matrix[filest][cont].setEst1(es1.getTransiçaosE(con));
                matrix[filest][cont].setEst2(es2.getTransiçaosE(con));               
                con++;
            }
            //comparamos en la misma fila y distintas columnas, si hay alguna pareja igual a la original, para revisarla
                //pareja original es la de la columna 0, de donde se obtuvieron los estados de las columnas insertadas
                for(int k=1;k<=(int)tam;k++){
                    if((matrix[filest][k].getEst1().getNombre()==es1.getNombre())&&(matrix[filest][k].getEst2().getNombre()
                            ==es2.getNombre())){
                        matrix[filest][k].setRevisado(true);
                    }
                }
            //se setea verdadera la revision de la posicion donde estabamos
            matrix[filest][0].setRevisado(true);
            
            //terminado de insertar todos los estados es una fila(for de arriba), se lee la matrix a buscar nuevos estados sin revisar
            //para colocarlos una posicion mas abajo
            for(int fil=0;fil<=tamañoEsta;fil++){
              for(int col=0;col<=(int)tam;col++){
                  
                  //se busca en la matriz alguna pareja de estados que sea igual a la original para marcarla revisada
                  if((matrix[fil][col]!=null)&&(matrix[fil][col].getEst1().getNombre()==es1.getNombre())&&
                          (matrix[fil][col].getEst2().getNombre())==es2.getNombre()){
                      matrix[fil][col].setRevisado(true);
                  }
                  
                  //si la casilla no esta revisada
                  if((matrix[fil][col].isRevisado()==false)&&(matrix[fil][col]!=null)){
                      
                      //vamos a buscar si no esta esta posicion ya verificada
                      for(int row=0;row<=tamañoEsta;row++){
                        for(int cols=0;cols<=(int)tam;cols++){
                            if((matrix[fil][col].getEst1().getNombre()==matrix[row][cols].getEst1().getNombre())&&
                                    (matrix[fil][col].getEst2().getNombre()==matrix[row][cols].getEst2().getNombre())
                                    &&(matrix[row][cols].isRevisado()==true)){
                                matrix[fil][col].setRevisado(true);
                            }
                        }
                      }
                     if((matrix[fil][col].isRevisado()==false)&&(matrix[fil][col]!=null)){
                      
                        //si la siguiente fila esta nula
                        if(matrix[fil+1][0]==null){
                            //se inerta la casilla con sus estados en una fila abajo
                            matrix[fil+1][0].setEst1(matrix[fil][col].getEst1());
                            matrix[fil+1][0].setEst2(matrix[fil][col].getEst2());
                            tamañoEsta++;
                            //se vuelve a llamar al metodo, para volver a verificar desde la columna e estados si son inidistinguibles
                            compararMatrix(matrix[fil][col].getEst1(),matrix[fil][col].getEst2(),filest+1,tam,tamañoEsta);

                        }
                        else
                        {
                            matrix[col][0].setEst1(matrix[fil][col].getEst1());
                            matrix[col][0].setEst2(matrix[fil][col].getEst2());
                             //se vuelve a llamar al metodo, para volver a verificar desde la columna e estados si son inidistinguibles
                            compararMatrix(matrix[fil][col].getEst1(),matrix[fil][col].getEst2(),filest+1,tam,tamañoEsta);

                        }
                     }
                  }
                }  
            }
            System.out.print("SI son equivalentes");
            
        }
        else
            System.out.print("NO son equivalentes");
        
        
    }

  
    
    
    
//    public static void main(String[] args){
//        MatrizComparaciones m = new MatrizComparaciones();
//        int alfabeto=2;
//        
//         for(int i=0;i<=4;i++){
//            for(int j=0;j<=4;j++){
//                
//           
//            }
//        }
//      
//    }
    
}
