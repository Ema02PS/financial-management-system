package modelo;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String cedula;
    private String nombre;
    private String provincia;
    private String canton;
    private String distrito;
    public Prestamos prestamos = new Prestamos();

    public Cliente() {
    }

    public Cliente(String cedula, String nombre, String provincia, String canton, String distrito) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
    }

    @XmlTransient
    public Prestamos getPrestamos() {
        return prestamos;
    }

    @XmlAttribute
    public String getCedula() {
        return cedula;
    }

    public List<Prestamo> getListPrestamos(){return prestamos.getPrestamos();}

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    @Override
    public String toString() {
        return
                "Cliente:"+nombre+'\n'+
                        "Cedula: "+cedula+'\n'+
                        "Provincia: "+provincia+'\n'+
                        "Canton: "+canton+'\n'+
                        "Distrito: "+distrito+'\n'+
                        "Prestamos: "+prestamos.getPrestamos().size()+'\n'+'\n';
    }

    public String toStringPrestamos(){
        String Informacion="";
        for(Prestamo prestamo : getPrestamos().getPrestamos()){
            Informacion += prestamo.toString();
        }
        return "\n\n"+"Cliente: " + nombre + '\t'+ "Cedula: " + cedula + '\n'+ Informacion;
    }

    public String toStringCuentas() {
        String Informacion="";
        for(Prestamo prestamo : getPrestamos().getPrestamos()){
            Informacion += prestamo.toStringCuentas();
        }
        return "\n\n"+"Cliente: " + nombre + '\t'+ "Cedula: " + cedula + '\n'+ Informacion;
    }
}