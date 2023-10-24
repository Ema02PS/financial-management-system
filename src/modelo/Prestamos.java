package modelo;

import jakarta.xml.bind.annotation.XmlElement;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Prestamos {

    private List<Prestamo> prestamos;

    public Prestamos(){
        prestamos = new ArrayList<Prestamo>();
    }

    public Prestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public void add(Prestamo prestamo){prestamos.add(prestamo);}

    public void AnadirPrestamo(int monto, int plaso,String ID){
        Prestamo prestamo = new Prestamo(monto,plaso,ID);
        prestamos.add(prestamo);
    }

    @Override
    public String toString() {
        return "Prestamos{" +
                "prestamos=" + prestamos +
                '}';
    }

    public boolean yaExisteID(String ID){
        for(Prestamo prestamo : getPrestamos()){
            if(prestamo.getID().equals(ID)){
                return true;
            }
        }
        return false;
    }

    public Prestamo BuscaPrestamo_x_ID(String ID){
        for(Prestamo prestamo : getPrestamos()){
            if(prestamo.getID().equals(ID)){
                return prestamo;
            }
        }
        return null;
    }

    public void Eliminar_Prestamo(String ID){
        if(BuscaPrestamo_x_ID(ID).getMonto()==0){
            prestamos.remove(BuscaPrestamo_x_ID(ID));
        }
    }

    String Prestamos(){
        return '\t'+"Prestamos"+"\n\n"+
                prestamos;

    }
}
