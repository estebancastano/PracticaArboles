/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaarboles;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Esteban Castaño
 */
public class PracticaArboles {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
   
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String numero = "";
        String sc = "";
        ArbolAVL aAVL = new ArbolAVL();
        NodoNario r = new NodoNario("");
        ArbolNarioListaGeneralizada arbolLG = new ArbolNarioListaGeneralizada(r);
        NodoNario raiz = arbolLG.getRaiz();
        System.out.println(raiz.getDato());
        while (!sc.equals("6")) {            
           sc = JOptionPane.showInputDialog(null, "Ingrese la opción deseada:\n"
                + "1. Identificar propietario de número \n"
                + "2. Imprimir contactos de una linea\n"
                + "3. Ingresar un nuevo contacto en el nivel 1\n"
                + "4. Ingresar un nuevo contacto en el nivel 2 o 3\n"
                + "5. Ingresar un contacto al identificador de llamadas\n"
                + "6. Guardar y salir");
            try {
                switch(sc){
                    case "1":
                        numero = JOptionPane.showInputDialog(null , "Ingrese el número de la persona que desea buscar:");
                        aAVL.NombreSegunNumero(numero);
                        break;
                    case "2":
                        numero = JOptionPane.showInputDialog(null, "Ingrese el número de la linea de la que desea saber los contactos:");
                        JOptionPane.showMessageDialog(null, arbolLG.imprimirContactos(raiz, numero));
                        
                        break;
                    case "3":
                        
                        numero = JOptionPane.showInputDialog(null, "Ingrese el nuevo contacto a agregar en el nivel 1");
                        arbolLG.insertarNuevoHijo((String) raiz.getDato(), numero);
                        JOptionPane.showMessageDialog(null, "Contacto agregado con éxito");
                        break;
                    case "4":
                        String numeroPadre = JOptionPane.showInputDialog(null, "Ingrese de quién es contacto: ");
                        numero = JOptionPane.showInputDialog(null, "Digíte el número que desea agregar");
                        
                        arbolLG.insertarNuevoHijo(numeroPadre, numero);
                        JOptionPane.showMessageDialog(null, "Contacto agregado con éxito");
                        break;
                    case "5":
                        
                        String nuevoContacto = "";
                        nuevoContacto = JOptionPane.showInputDialog(null,"Ingrese el nuevo contacto que desea ingresar(Ej = 3114567890:Pepe Perez) : ");
                        aAVL.insertarDato(nuevoContacto);
                            JOptionPane.showMessageDialog(null, "Contacto agregado con éxito");
                            
                        break;
                    case "6":
                         arbolLG.generarArchivoTextoLG();
                         aAVL.generarArchivoTextoAVL();
                         
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Ha ingresado una opción incorrecta");
                        
                }
            } catch (Exception e) {
                if(sc == null){
                  JOptionPane.showMessageDialog(null, "Programa finalizado");
                  System.exit(0);
                };
            }
        }
        
        
    }
}
    

