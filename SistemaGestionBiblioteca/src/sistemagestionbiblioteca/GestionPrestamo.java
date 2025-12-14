/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestionbiblioteca;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Emily
 */
public class GestionPrestamo {
    private List<Prestamo> listPrestamos= new ArrayList<>();
    private GestionSocio gestionSocio;
    private Catalogo catalogo;
    long MONTO_MULTA = 10; //MONTO MULTA??
    
    public GestionPrestamo(GestionSocio gestionSocio, Catalogo catalogo) {
        this.gestionSocio = gestionSocio; //recibe la instancia de gestionSocio creada en el Main
        this.catalogo = catalogo; //recibe la instancia de Catalogo creada en el Main
    }    
    
    public void mostrarMenuPrestamo(){
       String[] opciones = new String[4];
        opciones[0] = "Registrar Préstamo";
        opciones[1] = "Registrar Devolución";
        opciones[2] = "Consultar Préstamo por ID";
        opciones[3] = "Salir";
        Integer opcion = obtenerOpcion(opciones, "Menu Prestamo", "Prestamos");
        switch (opcion) {
             case 0:
                 RegistrarPrestamo();
                 mostrarMenuPrestamo();
                break;
             case 1:
                 RegistrarDevolucion();
                 mostrarMenuPrestamo();
                break;
             case 2:
                 ConsultarPrestamo();
                 mostrarMenuPrestamo();
                break;
             case 3:
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

    private void RegistrarPrestamo() {
        //Validar limite
        if (listPrestamos.size() >= 50) {
            JOptionPane.showMessageDialog(null, "Límite máximo de préstamos del sistema alcanzado.");
        }
        String idSocio =JOptionPane.showInputDialog("Ingrese el id (codigo del socio)");
        if (idSocio == null) {
            return;
        }
        
        List<Socio> listSocios = gestionSocio.getListSocios();
        Socio socio = null;
        for (int i = 0; i < listSocios.size(); i++) {
            if (listSocios.get(i).getIdSocio().equalsIgnoreCase(idSocio)) {
                socio = listSocios.get(i);
            }
        }
        if (socio == null) {
            String[] opciones = new String[2];
            opciones[0] = "Ingresar otro ID";
            opciones[1] = "Cancelar Préstamo";
            Integer opcion = obtenerOpcion(opciones, "El socio con ID " + idSocio + " no existe.", "Socio no existe");
            if (opcion == 0) {
                RegistrarPrestamo();
            }
            return;
        }
        if (!socio.getEstadoSocio().equals(EstadoSocio.ACTIVO)) {
            JOptionPane.showMessageDialog(null, "El socio " +socio.getNombreCompleto()+ " no está ACTIVO/n"
                    + "(Estado: "+ socio.getEstadoSocio().name() + "). No puede realizar préstamos.");
            return;
        }
        if (socio.getCantidadLibrosPrestadosActual() >= 3) {
            JOptionPane.showMessageDialog(null, "El socio " +socio.getNombreCompleto()+ " ya tiene el maximo de libros prestados (3).");
            return;
        }
        
        Libro libroAPrestar = obtenerLibroAPrestar();
        if (libroAPrestar == null) { // mensaje q no se encontro
            RegistrarPrestamo();
            return;
        }
        if (!libroAPrestar.getEstadoLibro().equals(EstadoLibro.DISPONIBLE)) {
            JOptionPane.showMessageDialog(null, "El Libro " + libroAPrestar.getTitulo() + " no esta disponible \nEstado: " + libroAPrestar.getEstadoLibro().name());
            return;
        }
        Prestamo prestamo = new Prestamo();
        prestamo.setLibro(libroAPrestar);
        prestamo.setSocio(socio);
        prestamo.setEstadoPrestamo(EstadoPrestamo.ACTIVO);
        LocalDate fechaPrestamo = LocalDate.now(); //Se obtiene la fecha actual del sistema
        prestamo.setFechaPrestamo(fechaPrestamo.toString()); //Se toma la fecha actual del sistema
        prestamo.setFechaDevolucionEstimada(fechaPrestamo.plusDays(15).toString()); //Se agrega 15 dias a la fecha actual
        prestamo.setIdPrestamo(listPrestamos.size() + 1000); //Id secuencial tamano de la lista mas 1000
        listPrestamos.add(prestamo);
        socio.setCantidadLibrosPrestadosActual(socio.getCantidadLibrosPrestadosActual()+1);
        JOptionPane.showMessageDialog(null, "El prestamo fue agregado exitosamente \n"
                + "Socio: " + prestamo.getSocio().getNombreCompleto() + "\n"
                + "Libro: " + prestamo.getLibro().getTitulo() + "\n" 
                + "Id Prestamo: " + prestamo.getIdPrestamo());
    }

    private Libro obtenerLibroAPrestar() {
        String isbn =JOptionPane.showInputDialog("Ingrese el isbn (codigo del libro)");
        Libro libroAPrestar = null;
        List<Libro> listLibros = catalogo.getListLibros();
        for (Libro libro : listLibros) {
            if (libro.getIsbn().equalsIgnoreCase(isbn)) {
                libroAPrestar = libro;
                break;
            }
        }
        if (libroAPrestar == null) { // mensaje q no se encontro
            String[] opcionesLibro = new String[2];
            opcionesLibro[0] = "Ingresar otro Isbn";
            opcionesLibro[1] = "Cancelar Préstamo";
            Integer opcion = obtenerOpcion(opcionesLibro, "El libro con ISBN " + isbn + " no existe", "Libro no existe");
            if (opcion == 0) {
                obtenerLibroAPrestar();
            }
            return null;
        }
        return libroAPrestar;
    }

    private void RegistrarDevolucion() {
        Integer idPrestamo =obtenerInteger("Ingrese el id del prestamo a devolver");
        Prestamo prestamoADevolver = null;
        for (Prestamo prestamo : listPrestamos) {
            if (prestamo.getIdPrestamo() == idPrestamo) {
                prestamoADevolver = prestamo;
            }
        }
        if (prestamoADevolver == null) {
            String[] opciones = new String[2];
            opciones[0] = "Ingresar otro ID";
            opciones[1] = "Cancelar Devolucion";
            Integer opcion = obtenerOpcion(opciones, "El prestamo con ID " + idPrestamo + " no existe.", "Prestamo no existe");
            if (opcion == 0) {
                RegistrarDevolucion();
            }
            return;
        }
        if (!prestamoADevolver.getEstadoPrestamo().equals(EstadoPrestamo.ACTIVO)) {
            JOptionPane.showMessageDialog(null, "El prestamo " +prestamoADevolver.getIdPrestamo()+ " no está ACTIVO/n"
                    + "(Estado: "+ prestamoADevolver.getEstadoPrestamo().name() + "). No se puede devolver.");
            return;
        }
        LocalDate devolucionEstimada = LocalDate.parse(prestamoADevolver.getFechaDevolucionEstimada());
        String mensaje = "";
        if (LocalDate.now().isAfter(devolucionEstimada)) {
            //MULTA
            long diasMulta = ChronoUnit.DAYS.between(devolucionEstimada, LocalDate.now());
            prestamoADevolver.setMultaGeneradaEstePrestamo(diasMulta*MONTO_MULTA);
        prestamoADevolver.setEstadoPrestamo(EstadoPrestamo.ATRASADO);
        prestamoADevolver.setFechaDevolucionReal(LocalDate.now().toString());
        } else {
        prestamoADevolver.setFechaDevolucionReal(LocalDate.now().toString());
            prestamoADevolver.setEstadoPrestamo(EstadoPrestamo.FINALIZADO);
        }
        
        JOptionPane.showMessageDialog(null, mensaje + "\n" + "\n" 
                + "Id Prestamo: " + prestamoADevolver.getIdPrestamo() + "\n"
                + "Socio: " + prestamoADevolver.getSocio().getNombreCompleto() + "\n"
                + "Libro: " + prestamoADevolver.getLibro().getTitulo() + "\n" 
                + "Estado Prestamo: " + prestamoADevolver.getEstadoPrestamo().name() + "\n" 
                + "Multa: " + prestamoADevolver.getMultaGeneradaEstePrestamo());
    }
    
    public static Integer obtenerInteger(String mensaje){
        try {
            return Integer.valueOf(JOptionPane.showInputDialog(mensaje));
        } catch (Exception e) {
            return obtenerInteger(mensaje);
        }
    }

    private void ConsultarPrestamo() {
        Integer idPrestamo =obtenerInteger("Ingrese el id del prestamo a consultar");
        Prestamo prestamoAConsultar = null;
        for (Prestamo prestamo : listPrestamos) {
            if (prestamo.getIdPrestamo() == idPrestamo) {
                prestamoAConsultar = prestamo;
            }
        }
        if (prestamoAConsultar == null) {
            String[] opciones = new String[2];
            opciones[0] = "Ingresar otro ID";
            opciones[1] = "Cancelar Consulta";
            Integer opcion = obtenerOpcion(opciones, "El prestamo con ID " + idPrestamo + " no existe.", "Prestamo no existe");
            if (opcion == 0) {
                ConsultarPrestamo();
            }
            return;
        }
        JOptionPane.showMessageDialog(null,
                "Préstamo ID: " + prestamoAConsultar.getIdPrestamo() + "\n"
                + "Socio: " + prestamoAConsultar.getSocio().getNombreCompleto() + "( " + prestamoAConsultar.getSocio().getIdSocio() + ")\n"
                + "Libro: " + prestamoAConsultar.getLibro().getTitulo() + "( " + prestamoAConsultar.getLibro().getIsbn() +"\n" 
                + "Fecha Préstamo: " + prestamoAConsultar.getFechaPrestamo() + "\n" 
                + "Devolución Estimada: " + prestamoAConsultar.getFechaDevolucionEstimada() + "\n" 
                + "Fecha Devolución Real: " + prestamoAConsultar.getFechaDevolucionReal() + "\n" 
                + "Estado: " + prestamoAConsultar.getEstadoPrestamo().name() + "\n" 
                + "Multa generada: " + prestamoAConsultar.getMultaGeneradaEstePrestamo());
    }
}
