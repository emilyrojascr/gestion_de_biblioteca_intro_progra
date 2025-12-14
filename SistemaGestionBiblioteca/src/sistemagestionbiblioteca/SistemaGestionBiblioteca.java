/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemagestionbiblioteca;

import javax.swing.JOptionPane;

/**
 *
 * @author Emily
 */
public class SistemaGestionBiblioteca {
    private static Catalogo catalogo= new Catalogo();
    private static GestionSocio gestionSocio = new GestionSocio();
    private static GestionPrestamo gestionPrestamo = new GestionPrestamo(gestionSocio, catalogo);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }
    
    public static void mostrarMenuPrincipal(){
        String[] opciones = new String[6];
        opciones[0] = "BIBLIOTECA";
        opciones[1] = "PRESTAMOS";
        opciones[2] = "CATALOGO";
        opciones[3] = "SOCIOS";
        opciones[4] = "REPORTES";
        opciones[5] = "SALIR";
        Integer opcion = obtenerOpcion(opciones, "Seleccione la opcion deseada", "Menu principal");
        
        switch (opcion) {
             case 0:
                JOptionPane.showMessageDialog(null, 
                        "Total socios Registrados: " + gestionSocio.cantidadSocios() + "\n" +
                        "Socios Activos: " + gestionSocio.sociosActivos() + "\n" +
                        "Socios Morosos: " + gestionSocio.sociosMorosos());
                mostrarMenuPrincipal();
                break;
             case 1:
                 //PRESTAMOS
                 gestionPrestamo.mostrarMenuPrestamo();
                mostrarMenuPrincipal();
                break;
             case 2:
                 //CATALOGO
                catalogo.mostrarMenuCatalogo();
                mostrarMenuPrincipal();
                break;
             case 3:
                 //SOCIOS
                 gestionSocio.mostrarMenuSocio();
                mostrarMenuPrincipal();
                break;
             case 4:
                 //REPORTES
                mostrarMenuPrincipal();
                break;
             case 5:
                break;
             default:
                 throw new AssertionError();
         }
    }
    
    public static Integer obtenerOpcion(String[] opciones, String message, String titulo){
        // Mostrar cuadro de opciones
        return JOptionPane.showOptionDialog(
                null, // parentComponent (null = centro de pantalla)
                message, // message
                titulo, // title
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null,
                opciones,
                opciones[0]
        );
     } 
    
}
