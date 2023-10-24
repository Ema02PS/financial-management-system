package modelo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "clientes")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Clientes {

    private List<Cliente> clientes = new ArrayList<Cliente>();

    public Clientes(){};

    @XmlElement(name = "cliente")
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void add(Cliente cliente){clientes.add(cliente);}

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public boolean yaExisteID(String ID){
        for(Cliente cliente : getClientes()){
            if(cliente.getCedula().equals(ID)){
                return true;
            }
        }
        return false;
    }

    public Cliente BuscaCliente_x_ID(String ID){
        for(Cliente cliente : getClientes()){
            if(cliente.getCedula().equals(ID)){
                return cliente;
            }
        }
        return null;
    }

    @Override
    public String toString() {
         String Info = "Clientes"+"\n\n";
        for(Cliente cliente : getClientes()){
            Info += cliente.toString();
        }
        return Info;
    }

    public String Prestamos(){
        String Informacion="Prestamos"+"\n\n";

        for(Cliente cliente : getClientes()){
        Informacion += cliente.toStringPrestamos();
        }
        return Informacion;
    }

    public String toStringPagos() {
        String Informacion="Cuentas"+"\n\n";
        for(Cliente cliente : getClientes()){
            Informacion += cliente.toStringCuentas();
        }
        return Informacion;
    }
}
