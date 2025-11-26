/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestionbiblioteca;

import java.io.BufferedReader;
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
        mostrarMenuCatalogo();
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
     
     mostrarMenuCatalogo();     
 }
 
 public void editarLibro(){
     String isbn =obtenerString("Ingrese el isbn (codigo del libro)");
     Libro libroAEditar = null;
     for (Libro libro : listLibros) {
         if (libro.getIsbn().equalsIgnoreCase(isbn)) {
             libroAEditar = libro;
             break;
         }
     }
     if (libroAEditar == null) {
        String[] opciones = new String[2];
        opciones[0] = "Ingresar otro ISBN";
        opciones[1] = "Cancelar";
        Integer opcion = obtenerOpcion(opciones, "El libro con ISBN " + isbn + "no existe", "Error");
        if (opcion == 0) {
            editarLibro();
        }             
     } else {
        String[] opciones = new String[6];
        opciones[0] = "Titulo";
        opciones[1] = "Autor";
        opciones[2] = "Editorial";
        opciones[3] = "Annio Pub";
        opciones[4] = "Genero";
        opciones[5] = "Atras";
        Integer opcion = obtenerOpcion(opciones, libroAEditar.mostrar(), "Editar Libro");
         switch (opcion) {
             case 0:
                String titulo=obtenerString("Ingrese el titulo del libro");
                if (titulo != null) {
                    libroAEditar.setTitulo(titulo);
                    JOptionPane.showMessageDialog(null, "Titulo actualizado correctamente");
                }
                 mostrarMenuCatalogo();
                break;
             case 1:
                String autor=obtenerString("Ingrese el autor del libro");
                if (autor != null) {
                    libroAEditar.setAutor(autor);
                    JOptionPane.showMessageDialog(null, "Autor actualizado correctamente");
                }
                 mostrarMenuCatalogo();
                break;
             case 2:
                String editorial=obtenerString("Ingrese la editorial del libro");
                if (editorial != null) {
                    libroAEditar.setEditorial(editorial);
                    JOptionPane.showMessageDialog(null, "Editorial actualizado correctamente");
                }
                 mostrarMenuCatalogo();
                break;
             case 3:
                Integer annioPublicacion=obtenerAnnio("Ingrese el annio de publicacion del libro");
                if (annioPublicacion != null) {
                    libroAEditar.setAnioPublicacion(annioPublicacion);
                    JOptionPane.showMessageDialog(null, "Annio de publicacion actualizado correctamente");
                }
                 mostrarMenuCatalogo();
                break;
             case 4:
                Integer genero = obtenerGenero();
                if (genero != null) {
                    libroAEditar.setGenero(Genero.values()[genero]);
                    JOptionPane.showMessageDialog(null, "Genero de publicacion actualizado correctamente");
                }
                 mostrarMenuCatalogo();
                break;
             case 5:
                 mostrarMenuCatalogo();
                break;
             default:
                 throw new AssertionError();
         }
        
        libroAEditar.mostrar();
        
     }
 }
 
    public void cambiarEstadoLibro(){
        String isbn =obtenerString("Ingrese el isbn (codigo del libro)");
        Libro libroAEditar = null;
        for (Libro libro : listLibros) {
            if (libro.getIsbn().equalsIgnoreCase(isbn)) {
                libroAEditar = libro;
                break;
            }
        }
        if (libroAEditar == null) {
            String[] opciones = new String[2];
            opciones[0] = "Ingresar otro ISBN";
            opciones[1] = "Cancelar";
            Integer opcion = obtenerOpcion(opciones, "El libro con ISBN " + isbn + "no existe", "Error");
            if (opcion == 0) {
                cambiarEstadoLibro();
            }             
         } else {
            if (libroAEditar.getEstadoLibro().equals(EstadoLibro.PRESTADO)) {
                JOptionPane.showMessageDialog(null, "El libro " + libroAEditar.getTitulo() + " ( "+libroAEditar.getIsbn() +") está PRESTADO. No se puede cambiar su\n" +
"estado directamente aquí. Debe ser devuelto primero.");
            } else {
               String[] opcionesCambiarEstado = new String[4];
                opcionesCambiarEstado[0] = "DISPONIBLE";
                opcionesCambiarEstado[1] = "EN_REPARACION";
                opcionesCambiarEstado[2] = "EXTRAVIADO";
                opcionesCambiarEstado[3] = "Cancelar";
                Integer estado = obtenerOpcion(opcionesCambiarEstado, "El estado actual del libro es: " + libroAEditar.getEstadoLibro(), "Cambiar Estado");
                System.out.println(estado);
                if (estado < 3) {
                    libroAEditar.setEstadoLibro(EstadoLibro.values()[estado]);
                    JOptionPane.showMessageDialog(null, "Estado del libro " + libroAEditar.getTitulo() + " ( "+libroAEditar.getIsbn() +") cambiado a " + libroAEditar.getEstadoLibro());
                }
            }
        }
        mostrarMenuCatalogo();
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
     
    public void mostrarMenuCatalogo(){
       String[] opciones = new String[5];
        opciones[0] = "Mostrar Catálogo Completo";
        opciones[1] = "Agregar Nuevo Libro";
        opciones[2] = "Editar Información de Libro";
        opciones[3] = "Cambiar Estado de Libro (Ej: Extraviado, En Reparación)";
        opciones[4] = "Salir";
        Integer opcion = obtenerOpcion(opciones, "Menu Catalogo", "Catalogo");
        switch (opcion) {
             case 0:
                 mostrarCatalogo();
                break;
             case 1:
                 agregarLibro();
                break;
             case 2:
                 editarLibro();
                break;
             case 3:
                 cambiarEstadoLibro();
                break;
             case 4:
                break;
             default:
                 throw new AssertionError();
         } 
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
      
      private Genero generarGeneroLibro() 
{
        return Genero.values()[((int)(Math.random() * 7))]; // obtiene los valores de la lista y genera un genero del enum con el indice del 1-7 aleatorio
    }
        private void precargarDatos(){
            String linea;
            String rutaArchivo = "src/sistemagestionbiblioteca/libros.txt";
            int cantidadPorGenerar = ((int)(Math.random() * 50));
            int lineasLeidas = 0;
            try {
                BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
                while (lineasLeidas < cantidadPorGenerar) {
                    linea = br.readLine();
                    if (linea == null) {
                        break;
                    }
                    String[] camposLibro = linea.split(",");
                    String titulo = camposLibro[0];
                    String autor = camposLibro[1];
                    String editorial = camposLibro[2];
                    listLibros.add(new Libro(generarCodigoLibro(),titulo,autor,generarGeneroLibro(), editorial, generarAnnioLibro(), generarEstadoLibro(), 0));
                    lineasLeidas++;
                }
            } catch (Exception e) {
                System.out.println("No se pudo generar los libros");
            }
        }
 
 
}
