package controlador;

import Vista.Ventana;
import Vista.Ventana2;
import Vista.Ventana3;
import com.itextpdf.io.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import modelo.Cliente;
import modelo.Clientes;
import modelo.JAXBParser;
import modelo.Prestamo;
import org.glassfish.jaxb.core.v2.model.core.ID;
import xmlEncoder.XMLEncodeDecode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.NavigableMap;

public class Controlador {

    Ventana principal;
    Ventana2 ventana2;
    Ventana3 ventana3;

    JAXBParser parser = new JAXBParser();
    Clientes clientes;

    public Controlador() {
        this.clientes = (Clientes) parser.unmarshall(new Clientes(),"clientes.xml");
        principal = new Ventana();
        ventana2 = new Ventana2();
        ventana3 = new Ventana3();
        ListenerBotones action = new ListenerBotones();
        principal.agregarListener(action);
        ventana2.agregarListener(action);
        ventana3.agregarListener(action);
    }

    private class ListenerBotones implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Estas simplemente son variables auxiliares
            String Cedula="";
            String Nombre="";
            String Provincia="";
            String Canton="";
            String Distrito="";
            String ID="";

            int Monto=0;
            int Plaso=0;

            int valor = Integer.parseInt(e.getActionCommand());
            switch(valor) {

                case 1: //Abre la ventana del buscador
                    Cedula = principal.espacioCedula.getText();

                    //VERIFICACIONES
                    if(!VerificarNumero(Cedula)){break;}
                    if(!clientes.yaExisteID(Cedula)){JOptionPane.showMessageDialog(null,"Este ID no existe","Advertencia",JOptionPane.INFORMATION_MESSAGE); break;}

                    principal.dispose();
                    ventana2.setVisible(true);
                    ventana2.ImprimirInfoCliente(clientes.BuscaCliente_x_ID(Cedula));
                    break;

                case 2: //ABRIR LA VENTANA DE PRESTAMOS
                    ventana3.setClienteSeleccionado(Cedula);
                    ventana3.meterTablaClientes(clientes.getClientes());
                    principal.dispose();
                    ventana3.setVisible(true);
                    break;

                case 3: //GUARDAR UN CLIENTE
                    Cedula = principal.espacioCedula.getText();
                    Nombre = principal.espacioNombre.getText();
                    Provincia = principal.Provincias.getSelectedItem().toString();
                    Canton = principal.Cantones.getSelectedItem().toString();
                    Distrito = principal.Distritos.getSelectedItem().toString();

                    //VERIFICACIONES
                    if(Nombre.isBlank() || Cedula.isBlank()){JOptionPane.showMessageDialog(null,"Falta información, la información esta incompleta","Advertencia",JOptionPane.INFORMATION_MESSAGE);break;}
                    if(!VerificarNumero(Cedula)){break;}
                    if(clientes.yaExisteID(Cedula)){JOptionPane.showMessageDialog(null,"Ya existe una persona con este ID","Advertencia",JOptionPane.INFORMATION_MESSAGE);break;}

                    Cliente cliente = new Cliente(Cedula,Nombre,Provincia,Canton,Distrito);
                    clientes.add(cliente);
                    JOptionPane.showMessageDialog(null,"Cliente guardado con éxito","Anuncio",JOptionPane.INFORMATION_MESSAGE);

                    parser.marshall(clientes,"clientes.xml");
                    CreaPDFClientes();
                    break;
                case 4: //ACTUALIZAR CANTONES
                    principal.insertarComboCantones(principal.retornaCantones(principal.getProvincias().getSelectedItem().toString()));
                    principal.insertarComboDistritos(principal.retornaDistritos(principal.getCantones().getSelectedItem().toString()));
                    break;
                case 5: //ACTUALIZAR DISTRITOS
                    if (principal.Cantones.getSelectedItem() != null)
                        principal.insertarComboDistritos(principal.retornaDistritos(principal.getCantones().getSelectedItem().toString()));
                    break;
                case 10: //REGRESA DE LA VENTANA DE BÚSQUEDA A LA PRINCIPAL
                    ventana2.dispose();
                    principal.setVisible(true);
                    break;
                case 20: //REGRESA DE LA VENTANA DE PRESTAMOS A LA PRINCIPAL
                    ventana3.dispose();
                    principal.setVisible(true);
                    break;
                case 21: //CAMBIA AL PANEL DE PAGAR PRÉSTAMO
                    Cedula = ventana3.espacio_Cedula.getText();
                    //VERIFICACIONES
                    if(Cedula.isBlank()){JOptionPane.showMessageDialog(null,"Por favor, ingrese una cédula de algún cliente","Advertencia",JOptionPane.INFORMATION_MESSAGE);break;}
                    if(!VerificarNumero(Cedula)){break;}
                    if(!clientes.yaExisteID(Cedula)){JOptionPane.showMessageDialog(null,"Esta cédula no existe","Advertencia",JOptionPane.INFORMATION_MESSAGE); break;}

                    ventana3.meterTablaPrestamos(clientes.BuscaCliente_x_ID(Cedula).getPrestamos().getPrestamos());
                    ventana3.VistaPagar();
                    ventana3.validate();
                    ventana3.repaint();
                    break;
                case 22: //CAMBIA AL PANEL DE AGREGAR PRÉSTAMO
                    Cedula = ventana3.espacio_Cedula.getText();
                    //VERIFICACIONES
                    if(Cedula.isBlank()){JOptionPane.showMessageDialog(null,"Por favor, ingrese una cedula de algun cliente","Advertencia",JOptionPane.INFORMATION_MESSAGE);break;}
                    if(!VerificarNumero(Cedula)){break;}
                    if(!clientes.yaExisteID(Cedula)){JOptionPane.showMessageDialog(null,"Esta cedula no existe","Advertencia",JOptionPane.INFORMATION_MESSAGE); break;}

                    ventana3.meterTablaPrestamos(clientes.BuscaCliente_x_ID(Cedula).getPrestamos().getPrestamos());
                    ventana3.VistaAgregar();
                    ventana3.validate();
                    ventana3.repaint();
                    break;
                case 31: //REGRESA DEL PANEL DE PAGAR PRESTAMOS A LA VENTANA DE PRESTAMOS
                    ventana3.meterTablaClientes(clientes.getClientes());
                    ventana3.CrearContenidoVentana();
                    ventana3.validate();
                    ventana3.repaint();
                    break;
                case 32: //PAGAR UN PRÉSTAMO
                    Cedula = ventana3.espacio_Cedula.getText();
                    ID = ventana3.espacio_ID.getText();
                    String Montoaux = ventana3.espacio_Monto.getText();
                    if(ID.isBlank() || Montoaux.isBlank()){JOptionPane.showMessageDialog(null,"Falta información, la información esta incompleta","Advertencia",JOptionPane.INFORMATION_MESSAGE);break;}
                    Monto = Integer.parseInt(ventana3.espacio_Monto.getText());
                    if(!VerificarNumero(ID)){break;}
                    if(!VerificarNumero(Montoaux)){break;}
                    if(!clientes.BuscaCliente_x_ID(Cedula).getPrestamos().yaExisteID(ID)){JOptionPane.showMessageDialog(null,"Este ID no existe","Advertencia",JOptionPane.INFORMATION_MESSAGE); break;}

                    clientes.BuscaCliente_x_ID(Cedula).getPrestamos().BuscaPrestamo_x_ID(ID).PagarPrestamo(Monto);
                    clientes.BuscaCliente_x_ID(Cedula).getPrestamos().Eliminar_Prestamo(ID);
                    ventana3.meterTablaPrestamos(clientes.BuscaCliente_x_ID(Cedula).getPrestamos().getPrestamos());
                    ventana3.validate();
                    ventana3.repaint();

                    parser.marshall(clientes,"clientes.xml");
                    CreaPDFClientes();
                    CreaPDFPrestamos();
                    CreaPDFPagosPrestamos();
                    break;
                case 41: //REGRESA DEL PANEL DE AGREGAR PRÉSTAMO A LA VENTANA DE PRESTAMOS
                    ventana3.meterTablaClientes(clientes.getClientes());
                    ventana3.CrearContenidoVentana();
                    ventana3.validate();
                    ventana2.repaint();
                    break;
                case 42: //AGREGAR UN PRÉSTAMO
                    Cedula = ventana3.espacio_Cedula.getText();
                    ID = ventana3.espacio_ID.getText();
                    String Montoaux2 = ventana3.espacio_Monto.getText();
                    String Plazoaux = ventana3.espcio_Plaso.getText();
                    if(ID.isBlank() || Montoaux2.isBlank() || Plazoaux.isBlank()){JOptionPane.showMessageDialog(null,"Falta informacion, la informacion esta incompleta","Advertencia",JOptionPane.INFORMATION_MESSAGE);break;}

                    Monto = Integer.parseInt(ventana3.espacio_Monto.getText());
                    Plaso = Integer.parseInt(ventana3.espcio_Plaso.getText());

                    if(!VerificarNumero(ID)){break;}
                    if(!VerificarNumero(Montoaux2)){break;}
                    if(!VerificarNumero(Plazoaux)){break;}
                    if(clientes.BuscaCliente_x_ID(Cedula).getPrestamos().yaExisteID(ID)){JOptionPane.showMessageDialog(null,"Este ID ya existe","Advertencia",JOptionPane.INFORMATION_MESSAGE); break;}
                    Prestamo prestamo = new Prestamo(Monto,Plaso,ID);
                    clientes.BuscaCliente_x_ID(Cedula).getPrestamos().add(prestamo);
                    ventana3.meterTablaPrestamos(clientes.BuscaCliente_x_ID(Cedula).getPrestamos().getPrestamos());

                    parser.marshall(clientes,"clientes.xml");

                    CreaPDFClientes();
                    CreaPDFPrestamos();
                    break;
            }
        }
    }

    public boolean VerificarNumero(String ID){
        try {
            int numero = Integer.parseInt(ID);
            return true;
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Por favor, introduzca un número","Advertencia",JOptionPane.INFORMATION_MESSAGE);
        return false;
        }
    }

    public void CreaPDFClientes() throws IOException{
        try {
            PdfWriter pdfWriter = new PdfWriter("ClientesPDF.pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            document.add(new Paragraph(clientes.toString()));
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void CreaPDFPrestamos() throws IOException{
        try {
            PdfWriter pdfWriter = new PdfWriter("PrestamosPDF.pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            document.add(new Paragraph(clientes.Prestamos()));
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void CreaPDFPagosPrestamos() throws IOException{
        try {
            PdfWriter pdfWriter = new PdfWriter("PagosPDF.pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            document.add(new Paragraph(clientes.toStringPagos()));
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
