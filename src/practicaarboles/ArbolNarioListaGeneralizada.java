/*
 * Copyright 2019 Carlos Alejandro Escobar Marulanda ealejandro101@gmail.com
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation 
 * files (the "Software"), to deal in the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or 
 * sell copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following 
 * conditions:
 * The above copyright notice and this permission notice shall 
 * be included in all copies or substantial portions of the 
 * Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR 
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package practicaarboles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.print.DocFlavor;

/**
 *
 * @author Alejandro Escobar
 */
public class ArbolNarioListaGeneralizada {

    NodoNario raiz;
    // Variables globales para identificar acciones
    final static boolean TRANSFORMAR = true;
    final static boolean NOTRANSFORMAR = false;

    /**
     * Otro constructor
     *
     * @param raiz
     */
    public ArbolNarioListaGeneralizada(NodoNario raiz) {
        this.raiz = raiz;
    }

    public ArbolNarioListaGeneralizada() {
    }

    /**
     * Es una forma de pintar el arbol
     */
    void mostrarPorListas() {
        Stack<NodoNario> migas = new Stack();
        migas.add(raiz);
        while (!migas.empty()) {
            NodoNario pr = migas.pop();
            while (pr != null) {
                if (pr.getSw() == 0) {
                    System.out.print(pr.getDato()); // Operar el recorrido
                } else {
                    NodoNario npr = (NodoNario) pr.getDato(); // Operar
                    migas.add(npr);
                }
                pr = pr.getLiga();
            }
        }
    }


    /**
     * Validar
     */
    public void imprimirXNivel() {
        NodoNario r = raiz;
        Queue<NodoNario> cola = new LinkedList<>();
        while (r != null) {
            if (r.getSw() == 1) {
                NodoNario realHijo = (NodoNario) r.getDato();
                System.out.print(realHijo.getDato()); // operación
                cola.add(realHijo.getLiga());
            } else {
                System.out.print(r.getDato()); // operación
            }
            r = r.getLiga();
            if (r == null && !cola.isEmpty()) {
                r = cola.remove();
            }
        }
    }

    public NodoNario getRaiz() {
        return raiz;
    }
    
    
    public void generarArchivoTextoLG() throws IOException{
	String ruta = "ContactosLG.txt";
        File archivo = new File(ruta);
        archivo.createNewFile();
        FileWriter escritor = new FileWriter (archivo);
        try (BufferedWriter escritorBuffer = new BufferedWriter(escritor)) {
            NodoNario r ;
            Stack <NodoNario> pila = new Stack();
            pila.add(raiz);
            while(!pila.empty()){
                r = pila.pop();
                while( r != null){
                    
                    if(r.getSw() == 0)
                    {
                        
                        escritorBuffer.write(r.getDato().toString());
                        
                        r = r.getLiga();
                    }else{
                        pila.add(r.getLiga());
                        r = (NodoNario) r.getDato();
                    }
                    
                    
                }
                
            }
        }
}
    
    // imprime los datos asociadios con el nodo si es que este tiene datos asociados
    public String imprimirContactos(NodoNario nodo, String dato) {
        NodoNario r = encontrarDato(nodo, dato);
        String contactos = "La lista de contactos es: \n";
        if (r.getSw() == 1) {
            NodoNario realCabeza = (NodoNario) r.getDato();
            r = realCabeza.getLiga();
            while (r != null) {
                NodoNario hijo = r;
                if(hijo.getSw() == 1){
                    NodoNario realHijo = (NodoNario) hijo.getDato();
                    contactos = contactos + "\n" + realHijo.getDato();
                }else{
                    contactos = contactos + "\n" + hijo.getDato();
                }
                r = r.getLiga();
            }
        }else if (r == raiz){
            r = r.getLiga();
            while (r != null) {
                NodoNario hijo = r;
                if(hijo.getSw() == 1){
                    NodoNario realHijo = (NodoNario) hijo.getDato();
                    contactos = contactos + "\n" + realHijo.getDato();
                }else{
                    contactos = contactos + "\n" + hijo.getDato();
                }
                r = r.getLiga();
            }
        }else {
            contactos = "El número: "+ dato +" no tiene contactos asociados"; // el nodo no tiene datos asociados
        }
        
        return contactos;
    }
    
    // se encarga de encontrar el nodo donde se van a buscar los datos asociados.
    public NodoNario encontrarDato(NodoNario nodo, String dato){
        NodoNario r = nodo;
        NodoNario aux = null;
        Queue<NodoNario> cola = new LinkedList<>();
        while (r != null) {
            if (r.getSw() == 1) {
                NodoNario realHijo = (NodoNario) r.getDato();
                cola.add(realHijo.getLiga());
                if(realHijo.getDato().equals(dato)){
                    aux = r;
                    break;
                }else{
                    aux = (NodoNario) r.getDato();
                }
            }else{
                aux = r;
            }
            r = r.getLiga();
            if (r == null && !cola.isEmpty()) {
                r = cola.remove();
            }
            if(aux.getDato().equals(dato)){
                r = null;
            }
        }
        return aux;
    }
    
    public void insertarNuevoHijo(String r, String dato) {
        NodoNario recorrido = raiz;
        Queue<NodoNario> miga = new LinkedList<>();
        while (recorrido != null) {
            if (recorrido.getSw() == 0) {
                if (recorrido.getDato().equals(r)) {
                    if (recorrido == raiz) {
                        insertarHijo(recorrido, dato, NOTRANSFORMAR);
                    } else {
                        insertarHijo(recorrido, dato, TRANSFORMAR);
                    }
                    break;
                }
            } else {
                NodoNario nreal = (NodoNario) recorrido.getDato();
                if (nreal.getDato().equals(r)) {
                    insertarHijo(nreal, dato, NOTRANSFORMAR);
                    break;
                } else {
                    miga.add(nreal.getLiga());
                }
            }
            recorrido = recorrido.getLiga();
            if (recorrido == null && !miga.isEmpty()) {
                recorrido = miga.remove();
            }
        }
    }

    /**
     *
     * @param r
     * @param dato
     * @param TRANSFORMAR
     */
    public void insertarHijo(NodoNario r, String dato, boolean TRANSFORMAR) {
        NodoNario nuevoHijo = new NodoNario(dato);
        if (TRANSFORMAR) {
            r.setSw(1);
            NodoNario nuevoPadre = new NodoNario(r.getDato());
            r.setDato(nuevoPadre);
            nuevoPadre.setLiga(nuevoHijo);
        } else {
            nuevoHijo.setLiga(r.getLiga());
            r.setLiga(nuevoHijo);
        }
    }
    
    public void insertarNuevoHijo(String nivel , String padre, String hijo){
     
    }
    
    public final void load() throws FileNotFoundException, IOException{
     BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\PracticaArboles\\src\\practicaarboles\\Prueba.txt"));
     String linea;
     String [] nPH;
        while ((linea = in.readLine()) != null) {            
            nPH = linea.split(":");
            insertarNuevoHijo(nPH[0], nPH[1], nPH[2]);
        }
    }
    
    
}
