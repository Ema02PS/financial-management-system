package Main;

import javax.naming.ldap.Control;
import javax.swing.*;
import Vista.*;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import controlador.*;
import modelo.*;
import xmlEncoder.XMLEncodeDecode;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {

            Cliente cliente1 = new Cliente("402510268","Marck Rojas","San Jose","Perez Zeledon","Daniel Flores");
            Cliente cliente2 = new Cliente("118410933","Emanuel Perez","San Jose","Escazu","San Antonio");
            Prestamo prestamo = new Prestamo(70000,12,"123");
            Prestamo prestamo2 = new Prestamo(60000,6,"345");
            cliente1.getPrestamos().add(prestamo);
            cliente2.getPrestamos().add(prestamo2);

            Clientes clientes = new Clientes();
            clientes.add(cliente1);
            clientes.add(cliente2);

            JAXBParser parser = new JAXBParser();
            parser.marshall(clientes,"clientes.xml");

            Controlador controlador = new Controlador(/*clientes*/);
            }
    });
    }
}