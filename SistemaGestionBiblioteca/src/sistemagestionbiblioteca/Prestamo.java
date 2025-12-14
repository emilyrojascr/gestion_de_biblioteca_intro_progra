/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestionbiblioteca;

/**
 *
 * @author eduar
 */


//calse Prestamo y sus atributos

public class Prestamo {
    
    private int idPrestamo;
    private Socio socio;
    private Libro libro;
    private String fechaPrestamo;
    private String fechaDevolucionEstimada;
    private String fechaDevolucionReal;
    private EstadoPrestamo estadoPrestamo;
    private double multaGeneradaEstePrestamo;



    //getters para la clase prestamo

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public Socio getSocio() {
        return socio;
    }

    public Libro getLibro() {
        return libro;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public String getFechaDevolucionEstimada() {
        return fechaDevolucionEstimada;
    }

    public String getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public EstadoPrestamo getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public double getMultaGeneradaEstePrestamo() {
        return multaGeneradaEstePrestamo;
    }

    
    
    //setters para la clase prestamo
    
    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucionEstimada(String fechaDevolucionEstimada) {
        this.fechaDevolucionEstimada = fechaDevolucionEstimada;
    }

    public void setFechaDevolucionReal(String fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }

    public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    public void setMultaGeneradaEstePrestamo(double multaGeneradaEstePrestamo) {
        this.multaGeneradaEstePrestamo = multaGeneradaEstePrestamo;
    }
}

//enum para el estado del prestamo

enum EstadoPrestamo {
    ACTIVO,
    FINALIZADO,
    ATRASADO
}
