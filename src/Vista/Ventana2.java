package Vista;

import modelo.Cliente;
import modelo.Clientes;
import modelo.TableModel;
import modelo.TableModelPrestamo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Ventana2 extends Ventana {

    JPanel espacio_boton;
    JPanel centro;
    JPanel info;

    JPanel espacio_leyenda;
    JPanel espacio_tablacliente;
    JPanel espacio_tablaprestamo;

    JButton BotonRegresar;

    JLabel Leyenda;

    public JTable tabacliente;
    public JTable tablaprestamos;

    public Ventana2() throws HeadlessException {
        panel1.setLayout(new BorderLayout());
        setTitle("Información");
        setSize(500,700);
        setVisible(false);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        CrearContenidoVentana();
    }

    @Override
    public void agregarListener(ActionListener action) {
        BotonRegresar.addActionListener(action);
        BotonRegresar.setActionCommand("10");
    }

    @Override
    public void CrearContenidoVentana() {

        //Tablas
        tabacliente = new JTable();
        tablaprestamos = new JTable();

        //Layouts
        espacio_leyenda = new JPanel();
        espacio_boton = new JPanel(new FlowLayout()); //sirve para acomodar el regresar en el centro
        centro = new JPanel(new FlowLayout()); //sirve para acomodar en el centro
        info = new JPanel(new GridLayout(2,0)); // Es toda la info en si misma
        espacio_tablacliente = new JPanel(new BorderLayout()); //Es la segunda fila del GridLayout, aquí va la tabla con la info del cliente
        espacio_tablaprestamo = new JPanel(new BorderLayout()); //Es la cuarta fila del GridLayout, aquí va la tabla con los prestamos del cliente

        //Textos de la ventana
        Leyenda = new JLabel("Informacion y prestamos del cliente");
        espacio_leyenda.add(Leyenda);

        //Añadiendo Botón
        BotonRegresar = new JButton("Regresar");
        espacio_boton.add(BotonRegresar);

        //Añadiendo Tablas

        espacio_tablacliente.add(tabacliente,BorderLayout.CENTER);
        espacio_tablaprestamo.add(tablaprestamos,BorderLayout.CENTER);

        //Acomodando Layouts
        panel1.add(centro);
        info.add(espacio_tablacliente);
        info.add(espacio_tablaprestamo);
        centro.add(info);
        panel1.add(espacio_leyenda,BorderLayout.NORTH);
        panel1.add(centro,BorderLayout.CENTER);
        panel1.add(espacio_boton,BorderLayout.SOUTH);
        getContentPane().add(panel1);
    }

    public void ImprimirInfoCliente(Cliente cliente){
        //Genera el contenido de la tabla del cliente
        List<Cliente> contenido = new ArrayList<Cliente>();
        contenido.add(cliente);

        //Arma las tablas
        tabacliente.setModel(new TableModel(contenido));
        tablaprestamos.setModel(new TableModelPrestamo(cliente.getPrestamos().getPrestamos()));

        //"Declara" las tablas
        JScrollPane sp1 = new JScrollPane(tabacliente);
        JScrollPane sp2 = new JScrollPane(tablaprestamos);

        //Añade la tabla de clientes al panel espacio tabla cliente
        if(espacio_tablacliente.getComponentCount()>0){
            espacio_tablacliente.remove(0);
        }

        espacio_tablacliente.add(sp1,BorderLayout.CENTER);

        //Añade la tabla de prestamos al panel espacio tabla prestamos
        if(espacio_tablaprestamo.getComponentCount()>0){
            espacio_tablaprestamo.remove(0);
        }

        espacio_tablaprestamo.add(sp2,BorderLayout.CENTER);

        //Refresca las tablas
        espacio_tablacliente.repaint();
        espacio_tablaprestamo.repaint();
        espacio_tablacliente.validate();
        espacio_tablaprestamo.validate();
    }
}