/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestionbiblioteca;

/**
 *
 * @author eduar
 */
public class Socio {
 
    private String idSocio;
    private String nombreCompleto;
    private String fechaRegistro;
    private EstadoSocio estadoSocio;
    private double multasAcumuladas;
    private int cantidadLibrosPrestadosActual;

    
    //getters de la clase Socio
    
    public String getIdSocio() {
        return idSocio;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public EstadoSocio getEstadoSocio() {
        return estadoSocio;
    }

    public double getMultasAcumuladas() {
        return multasAcumuladas;
    }

    public int getCantidadLibrosPrestadosActual() {
        return cantidadLibrosPrestadosActual;
    }

    
    //setters de la clase Socio
    
    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setEstadoSocio(EstadoSocio estadoSocio) {
        this.estadoSocio = estadoSocio;
    }

    public void setMultasAcumuladas(double multasAcumuladas) {
        this.multasAcumuladas = multasAcumuladas;
    }

    public void setCantidadLibrosPrestadosActual(int cantidadLibrosPrestadosActual) {
        this.cantidadLibrosPrestadosActual = cantidadLibrosPrestadosActual;
    }
}

//enum para el estado del socio

enum EstadoSocio {
    ACTIVO,
    INACTIVO,
    SUSPENDIDO
}
