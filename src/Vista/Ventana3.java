package Vista;

import modelo.TableModel;
import modelo.Cliente;
import modelo.Prestamo;
import modelo.TableModelPrestamo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.PortUnreachableException;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Ventana3 extends JFrame {

    String clienteSeleccionado;

    public String getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(String clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public JPanel panel1 = new JPanel(new BorderLayout());
    public JPanel panel2 = new JPanel(new BorderLayout());
    public JPanel panel3 = new JPanel(new BorderLayout());

    public JPanel Info = new JPanel(new GridLayout(2,2));
    public JPanel Tabla = new JPanel();
    public JPanel Botones = new JPanel();

    JLabel Informacion = new JLabel();
    JLabel label_ID = new JLabel("                                                                                                   ID");
    JLabel label_Cuota = new JLabel("                                                                                             Cuota");
    JLabel label_Monto = new JLabel("                                                                                             Monto");
    JLabel label_Plaso = new JLabel("                                                                             Plazo en meses");

    public JTextField espacio_Cedula = new JTextField();
    public JTextField espacio_ID = new JTextField();
    public JTextField espacio_Cuota = new JTextField();
    public JTextField espacio_Monto = new JTextField();
    public JTextField espcio_Plaso = new JTextField();

    public JTable tabla_clientes = new JTable();
    public JTable tabla_prestamos = new JTable();

    JButton Regresar = new JButton("Regresar");
    JButton Pagar = new JButton("Pagar");
    JButton Agregar = new JButton("Agregar");

    public Ventana3() throws HeadlessException {
        setSize(700, 500);
        //panel1.setLayout();
        setTitle("Sistema de prestamos");
        setVisible(false);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        CrearContenidoVentana();
    }

    public void agregarListener(ActionListener action) {
        Regresar.addActionListener(action);
        Pagar.addActionListener(action);
        Agregar.addActionListener(action);
    }

    public void CrearContenidoVentana() {

        getContentPane().removeAll();

        //LIMPIAR
        Info.remove(label_ID);
        Info.remove(label_Monto);
        Info.remove(label_Cuota);
        Info.remove(label_Plaso);
        Info.remove(espacio_ID);
        Info.remove(espacio_Monto);
        Info.remove(espacio_Cuota);
        Info.remove(espcio_Plaso);
        Botones.remove(Regresar);
        Botones.remove(Pagar);
        Botones.remove(Agregar);
        espacio_Cedula.setText("");

        Informacion.setText("Escriba la cédula de un cliente para pagar o agregar un préstamo");

        Regresar.setActionCommand("20");
        Pagar.setActionCommand("21");
        Agregar.setActionCommand("22");

        Info.add(Informacion);
        Info.add(espacio_Cedula);
        Botones.add(Regresar);
        Botones.add(Pagar);
        Botones.add(Agregar);

        panel1.add(Info,BorderLayout.NORTH);
        panel1.add(Tabla,BorderLayout.CENTER);
        panel1.add(Botones,BorderLayout.SOUTH);

        validate();
        repaint();
        getContentPane().add(panel1);

    }

    public void VistaPagar() {

        getContentPane().removeAll();

        //LIMPIAR
        Info.remove(Informacion);
        Info.remove(espacio_ID);
        Info.remove(espacio_Cedula);
        Botones.remove(Regresar);
        Botones.remove(Pagar);
        Botones.remove(Agregar);
        espacio_ID.setText("");
        espacio_Monto.setText("");

        Regresar.setActionCommand("31");
        Pagar.setActionCommand("32");

        Info.setLayout(new GridLayout(2,2));
        Info.add(label_ID);
        Info.add(espacio_ID);
        Info.add(label_Monto);
        Info.add(espacio_Monto);
        Botones.add(Regresar);
        Botones.add(Pagar);

        panel2.add(Info,BorderLayout.NORTH);
        panel2.add(Tabla,BorderLayout.CENTER);
        panel2.add(Botones,BorderLayout.SOUTH);

        validate();
        repaint();
        getContentPane().add(panel2);
        getContentPane().repaint();

    }

    public void VistaAgregar(){
        getContentPane().removeAll();

        //LIMPIAR
        Info.remove(Informacion);
        Info.remove(espacio_ID);
        Info.remove(espacio_Cedula);
        Botones.remove(Regresar);
        Botones.remove(Pagar);
        Botones.remove(Agregar);
        espacio_ID.setText("");
        espacio_Monto.setText("");
        espcio_Plaso.setText("");

        Regresar.setActionCommand("41");
        Agregar.setActionCommand("42");

        Info.setLayout(new GridLayout(3,3));
        Info.add(label_ID);
        Info.add(espacio_ID);
        Info.add(label_Monto);
        Info.add(espacio_Monto);
        Info.add(label_Plaso);
        Info.add(espcio_Plaso);

        Botones.add(Regresar);
        Botones.add(Agregar);

        panel3.add(Info,BorderLayout.NORTH);
        panel3.add(Tabla,BorderLayout.CENTER);
        panel3.add(Botones,BorderLayout.SOUTH);

        validate();
        repaint();
        getContentPane().add(panel3);
        getContentPane().repaint();
    }

    public void meterTablaClientes(List<Cliente> lista) {
        tabla_clientes.setModel(new TableModel(lista));
        tabla_clientes.setBounds(30,40,200,200);
        JScrollPane sp1 = new JScrollPane(tabla_clientes);

        if (Tabla.getComponentCount() > 0)
        {
            Tabla.remove(0);
        }
        Tabla.add(sp1);
        Tabla.validate();
        Tabla.repaint();
    }

    public void meterTablaPrestamos(List<Prestamo> lista) {
        tabla_clientes.setModel(new TableModelPrestamo(lista));
        tabla_clientes.setBounds(30,40,200,200);
        JScrollPane sp1 = new JScrollPane(tabla_clientes);

        if (Tabla.getComponentCount() > 0)
        {
            Tabla.remove(0);
        }
        Tabla.add(sp1);
        Tabla.validate();
        Tabla.repaint();
    }
}