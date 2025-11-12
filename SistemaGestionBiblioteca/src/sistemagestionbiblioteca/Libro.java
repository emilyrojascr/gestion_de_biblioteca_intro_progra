/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestionbiblioteca;

/**
 *
 * @author eduar
 */


//clase de Libro y sus atributos
public class Libro {
  
    private String isbn;
    private String titulo;
    private String autor;
    private Genero genero;
    private String editorial;
    private int anioPublicacion;
    private EstadoLibro estadoLibro;
    private int vecesPrestado;

    
    
    //getters para la clase Libro
    
    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getEditorial() {
        return editorial;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public EstadoLibro getEstadoLibro() {
        return estadoLibro;
    }

    public int getVecesPrestado() {
        return vecesPrestado;
    }

    
    
    //setters para la clase Libro
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public void setEstadoLibro(EstadoLibro estadoLibro) {
        this.estadoLibro = estadoLibro;
    }

    public void setVecesPrestado(int vecesPrestado) {
        this.vecesPrestado = vecesPrestado;
    }
}



//enum para el genero del libro

enum Genero {
    FICCION,
    NO_FICCION,
    MISTERIO,
    CIENCIA,
    HISTORIA,
    BIOGRAFIA,
    FANTASIA
}

//enum para el estado del libro

enum EstadoLibro {
    DISPONIBLE,
    PRESTADO,
    DETERIORADO,
    PERDIDO
}
