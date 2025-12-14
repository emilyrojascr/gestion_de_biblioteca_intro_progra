/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestionbiblioteca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import static sistemagestionbiblioteca.Catalogo.obtenerOpcion;

/**
 *
 * @author Emily
 */
public class GestionSocio {
    private List<Socio> listSocios= new ArrayList<>();

    public List<Socio> getListSocios() {
        return listSocios;
    }
    
    public void registrarSocio(){
        if (listSocios.size()>=30) {
            System.out.println("Limite maximo de Socios (30) alcanzado");
            return;
        }
        String idSocio=generarCodigoSocio();
        String nombreSocio = generarNombre();
        Socio socio = new Socio(idSocio, nombreSocio, new Date().toString(), EstadoSocio.ACTIVO, 0.0, 0);
        listSocios.add(socio);
        JOptionPane.showMessageDialog(null, "Socio registrado correctamente." + "\n" +
                "ID Socio: " + socio.getIdSocio() + "\n" + 
                "Nombre: " + socio.getNombreCompleto() + "\n" +
                "Fecha Registro: " + socio.getFechaRegistro() + "\n" +
                "Estado: " + socio.getEstadoSocio() + "\n" + 
                "Multas acumuladas: " + socio.getMultasAcumuladas() + "\n" +
                "Libros prestados actualmente: " + socio.getCantidadLibrosPrestadosActual());

    }
    
    private String generarCodigoSocio() 
{
        return "SOC-"+((int)(Math.random() * 500)+1);
    }
    
    private String generarNombre(){
            String nombreSocio = "";
            String rutaArchivo = "src/sistemagestionbiblioteca/socios.txt";
            int numeroAleatorio = ((int)(Math.random() * 30));
            int lineasLeidas = 0;
            try {
                BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
                
                for (int i = 0; i < numeroAleatorio; i++) {
                    nombreSocio = br.readLine();
                    if (lineasLeidas == numeroAleatorio) {
                        break;
                    }
                    if (nombreSocio == null) {
                        break;
                    }
                    lineasLeidas++;
                }
            } catch (Exception e) {
                System.out.println("No se pudo generar nombre del socio");
            }
            return nombreSocio;
        }
    
    public void consultarSocio(){
     String idSocio =obtenerString("Ingrese el id (codigo del socio)");
        if (idSocio == null) {
            return;
        }
     Socio socioConsultado = null;
        for (int i = 0; i < listSocios.size(); i++) {
            if (listSocios.get(i).getIdSocio().equalsIgnoreCase(idSocio)) {
                socioConsultado = listSocios.get(i);
            }
        }
        if (socioConsultado == null) {
            consultarSocio();
        }
        JOptionPane.showMessageDialog(null, 
                "ID Socio: " + socioConsultado.getIdSocio() + "\n" + 
                "Nombre: " + socioConsultado.getNombreCompleto() + "\n" +
                "Fecha Registro: " + socioConsultado.getFechaRegistro() + "\n" +
                "Estado: " + socioConsultado.getEstadoSocio() + "\n" + 
                "Multas acumuladas: " + socioConsultado.getMultasAcumuladas() + "\n" +
                "Libros prestados actualmente: " + socioConsultado.getCantidadLibrosPrestadosActual());
    }
    
    public void actualizarSocio(){
        String idSocio =obtenerString("Ingrese el id (codigo del socio)");
        if (idSocio == null) {
            return;
        }
        Socio socio = null;
        for (int i = 0; i < listSocios.size(); i++) {
            if (listSocios.get(i).getIdSocio().equalsIgnoreCase(idSocio)) {
                socio = listSocios.get(i);
            }
        }
        if (socio == null) {
            actualizarSocio();
        }
        String[] opciones = new String[4];
        opciones[0] = EstadoSocio.ACTIVO.name();
        opciones[1] = EstadoSocio.SUSPENDIDO.name();
        opciones[2] = EstadoSocio.INACTIVO.name();
        opciones[3] = "Cancelar";
        Integer opcion = obtenerOpcion(opciones, "El estado actual del socio es " + socio.getEstadoSocio()+ "\n Seleccione el estado por el que desea cambiarlo", "Error");
        if (opcion == 3) {
            //RETORNAR AL MAIN MENU
        }
        EstadoSocio estadoSeleccionado = EstadoSocio.INACTIVO;
        if (opcion == 0 ) {
            estadoSeleccionado = EstadoSocio.ACTIVO;
        }
        if (opcion == 1 ) {
            estadoSeleccionado = EstadoSocio.SUSPENDIDO;
        }
        for (int i = 0; i < listSocios.size(); i++) {
            if (listSocios.get(i).getIdSocio().equalsIgnoreCase(idSocio)) {
                listSocios.get(i).setEstadoSocio(estadoSeleccionado);
            }
        }
        JOptionPane.showMessageDialog(null, 
                "Estado del socio: " + socio.getNombreCompleto() + " ( " + socio.getIdSocio() + ") cambiado a " + estadoSeleccionado);
    }
    
    public void gestionarMultas() {
        String idSocio =obtenerString("Ingrese el id (codigo del socio)");
        if (idSocio == null) {
            return;
        }
        Socio socio = null;
        for (int i = 0; i < listSocios.size(); i++) {
            if (listSocios.get(i).getIdSocio().equalsIgnoreCase(idSocio)) {
                socio = listSocios.get(i);
            }
        }
        if (socio == null) {
            gestionarMultas();
        }
        String[] opciones = new String[3];
        opciones[0] = "Pagar totalidad";
        opciones[1] = "Pagar monto parcial";
        opciones[2] = "Cancelar";
        Integer opcion = obtenerOpcion(opciones, "Socio: " +socio.getNombreCompleto()+"\nLas multas acumuladas son: " + socio.getMultasAcumuladas(), "Error");
        if (opcion == 2) {
            //RETORNAR AL MAIN MENU
        }
        if (opcion == 0) {
            for (int i = 0; i < listSocios.size(); i++) {
                if (listSocios.get(i).getIdSocio().equalsIgnoreCase(idSocio)) {
                    listSocios.get(i).setMultasAcumuladas(0.0);
                    listSocios.get(i).setEstadoSocio(EstadoSocio.ACTIVO);
                    
                }
            }
            JOptionPane.showMessageDialog(null,"Todas las multas han sido pagadas el nuevo saldo es 0");
        }
        if (opcion == 1) {
            Double montoParcial = obtenerDouble("Ingrese la suma que desea abonar");
            if (montoParcial > socio.getMultasAcumuladas()) {
                JOptionPane.showMessageDialog(null,"Monto no puede superar la totalidad de las multas");
                gestionarMultas();
            } else {
                for (int i = 0; i < listSocios.size(); i++) {
                    if (listSocios.get(i).getIdSocio().equalsIgnoreCase(idSocio)) {
                        Double saldo = listSocios.get(i).getMultasAcumuladas() - montoParcial;
                        listSocios.get(i).setMultasAcumuladas(saldo);
                        JOptionPane.showMessageDialog(null,"Pago parcial realizado con exito, el saldo restante es de " + saldo);
                    }
            }
            }
            
        }
        
    }
    
    public String obtenerString(String mensaje){
        return JOptionPane.showInputDialog(mensaje);
    }
    
    public static Double obtenerDouble(String mensaje){
        return Double.valueOf(JOptionPane.showInputDialog(mensaje));
    }
    
    public int cantidadSocios(){
        return listSocios.size();
    }
    public int sociosActivos(){
        int sociosActivos = 0;
        for (int i = 0; i < listSocios.size(); i++) {
            if (listSocios.get(i).getEstadoSocio().equals(EstadoSocio.ACTIVO)) {
                sociosActivos++;
            }
        }
        return sociosActivos;
    }
    public int sociosMorosos(){
        int morosos = 0;
        for (int i = 0; i < listSocios.size(); i++) {
            if (listSocios.get(i).getEstadoSocio().equals(EstadoSocio.MOROSO)) {
                morosos++;
            }
        }
        return morosos;
    }
    
    public void mostrarMenuSocio(){
       String[] opciones = new String[5];
        opciones[0] = "Registrar Nuevo Socio";
        opciones[1] = "Consultar Datos Socio";
        opciones[2] = "Actualizar informacion de Socio";
        opciones[3] = "Gestionar Multas Socio";
        opciones[4] = "Salir";
        Integer opcion = obtenerOpcion(opciones, "Menu Socio", "Socio");
        switch (opcion) {
             case 0:
                 registrarSocio();
                 mostrarMenuSocio();
                break;
             case 1:
                 consultarSocio();
                 mostrarMenuSocio();
                break;
             case 2:
                 actualizarSocio();
                 mostrarMenuSocio();
                break;
             case 3:
                 gestionarMultas();
                 mostrarMenuSocio();
                break;
             case 4:
                break;
             default:
                 throw new AssertionError();
         } 
    }
}
