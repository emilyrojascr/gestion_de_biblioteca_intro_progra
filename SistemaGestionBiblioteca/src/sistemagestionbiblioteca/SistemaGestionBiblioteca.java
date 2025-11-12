/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemagestionbiblioteca;

/**
 *
 * @author Emily
 */
public class SistemaGestionBiblioteca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Catalogo catalogo= new Catalogo();
        catalogo.mostrarCatalogo();
        System.out.println("\n");
        catalogo.agregarLibro();
        System.out.println("\n");
        catalogo.mostrarCatalogo();
        
    }
    
}
