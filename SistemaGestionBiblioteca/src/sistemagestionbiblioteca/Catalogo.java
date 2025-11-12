/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestionbiblioteca;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Emily
 */
public class Catalogo {
    
 private List<Libro> listLibros= new ArrayList<>();
 
 public void mostrarCatalogo(){// primer metodo para mostrar todos los libros
     if (listLibros.size()==0) {
            System.out.println("No hay libros disponibles");
            return;
        }
        System.out.printf("| %-10s | %-30s | %-25s | %-12s | %-15s |\n",
                "ISBN", "Titulo", "Autor", "Genero", "Estado");
        for (Libro libro : listLibros) {
            System.out.printf("| %-10s | %-30s | %-25s | %-12s | %-15s |\n",
                    libro.getIsbn(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getGenero(),
                    libro.getEstadoLibro());
        }
        JOptionPane.showMessageDialog(null, "Catálogo completo mostrado en consola");
 }
 
 public void agregarLibro(){// validar el limite de 50 libros
     if (listLibros.size()>=50) {
         System.out.println("Limite maximo de libros (50) alcanzado");
        return;
     }
     String isbn=generarCodigoLibro();
     String titulo=obtenerString("Ingrese el titulo del libro");
     String autor=obtenerString("Ingrese el autor del libro");
     String editorial=obtenerString("Ingrese la editorial del libro");
     Integer annioPublicacion=obtenerAnnio("Ingrese el annio de publicacion del libro");
     if (annioPublicacion == null) {
         return;
     }
     Integer genero = obtenerGenero();
     
     Libro nuevoLibro = new Libro(isbn, titulo, autor, Genero.values()[genero], editorial, annioPublicacion,EstadoLibro.DISPONIBLE, 0);
     listLibros.add(nuevoLibro);
     JOptionPane.showMessageDialog(null, "Libro agregado correctamente");
     System.out.println("|  ISBN: "+ nuevoLibro.getIsbn());
     System.out.println("|  Titulo: "+ nuevoLibro.getTitulo());
     System.out.println("|  Autor: "+ nuevoLibro.getAutor());
     System.out.println("|  Genero: "+ nuevoLibro.getGenero());
     System.out.println("|  Editorial: "+ nuevoLibro.getEditorial());
     System.out.println("|  Annio: "+ nuevoLibro.getAnioPublicacion());
     System.out.println("|  Estado: "+ nuevoLibro.getEstadoLibro());
     System.out.println("|  Veces prestado: "+ nuevoLibro.getVecesPrestado());
     
     
 }
 
     public String obtenerString(String mensaje){
        return JOptionPane.showInputDialog(mensaje);
    }
     
     public Integer obtenerAnnio(String mensaje){
        try {
            Integer annio = Integer.valueOf(JOptionPane.showInputDialog(mensaje));
            
            if (annio<0) {
                String[] opciones = new String[2];
                opciones[0] = "Reintentar";
                opciones[1] = "Cancelar Ingreso";
                Integer opcion = obtenerOpcion(opciones, "Annio de publicación inválido. Debe ser un número mayor a 0", "Error");
                if (opcion == 0) {
                    return obtenerAnnio(mensaje);
                } else {
                    return null;
                } 
            }
            return annio;
        } catch (Exception e) {
            return obtenerAnnio(mensaje);
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
     
     public Integer obtenerGenero(){
        // Mostrar cuadro de opciones
        Genero[] opciones = Genero.values();
        return JOptionPane.showOptionDialog(
                null, // parentComponent (null = centro de pantalla)
                "Ingrese el genero del libro", // message
                "Genero", // title
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null,
                opciones,
                opciones[0]
        );
     } 

 //Constructor hace precarga de libros desde una lista json
    public Catalogo() {
      
        precargarDatos();
        
    }
    
     private String generarCodigoLibro() 
{
        return "LIB-"+((int)(Math.random() * 500)+1);
    }
     
      private int generarAnnioLibro() 
{
        return ((int)(Math.random() * (2023-1950+1))+1950); //multiplico para obtener el rango y luego le sumo el minimo para el punto de inicio
    }
      
      private EstadoLibro generarEstadoLibro() 
{
        return EstadoLibro.values()[((int)(Math.random() * 4))]; // obtiene los valores de la lista y genera un estado del enum con el indice del 1-4 aleatorio
    }
        private void precargarDatos(){
              Gson gson = new Gson();
        try (FileReader reader = new FileReader("src/sistemagestionbiblioteca/libros.json")) {
            List<Libro> libros = gson.fromJson(reader, new TypeToken<List<Libro>>(){}.getType());
            for (int i = 0; i < libros.size(); i++) { //recorre la lista de libros precarga para agregar los ID
            libros.get(i).setIsbn(generarCodigoLibro());
            libros.get(i).setAnioPublicacion(generarAnnioLibro());
            libros.get(i).setEstadoLibro(generarEstadoLibro());
            }
            listLibros.addAll(libros);
        } catch (Exception e) {
            System.out.println("No se pudo cargar los libros");
        }
        }
 
 
}
