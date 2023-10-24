package modelo;

import javax.swing.*;

import static java.lang.Math.*;

public class Prestamo {

    String ID;
    double Cuota;
    double MontoconIntereses; //Préstamo
    int Mesesrestantes;//Plazo
    int MontoOriginal; // Monto aux
    int MontoSinIntereses; //Monto
    int Plaso; //Plazo aux

    public Prestamo() {
    }

    public Prestamo(int monto, int plaso, String ID) {
        this.ID = ID;
        MontoOriginal = monto;
        Plaso = plaso;
        Cuota = calculoCuota(monto,plaso);
        Mesesrestantes = plaso;
        MontoconIntereses = Cuota * plaso;
        MontoSinIntereses = monto;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setCuota(double cuota) {
        Cuota = cuota;
    }

    public double getMontoconIntereses() {
        return MontoconIntereses;
    }

    public void setMontoconIntereses(double montoconIntereses) {
        MontoconIntereses = montoconIntereses;
    }

    public int getMesesrestantes() {
        return Mesesrestantes;
    }

    public void setMesesrestantes(int mesesrestantes) {
        Mesesrestantes = mesesrestantes;
    }

    public int getMontoOriginal() {
        return MontoOriginal;
    }

    public void setMontoOriginal(int montoOriginal) {
        MontoOriginal = montoOriginal;
    }

    public double getCuota() {
        return Cuota;
    }

    public void setCuota(int cuota) {
        Cuota = cuota;
    }

    public int getMonto() {
        return MontoSinIntereses;
    }

    public void setMonto(int monto) {
        MontoSinIntereses = monto;
    }

    public int getPlaso() {
        return Plaso;
    }

    public void setPlaso(int plaso) {
        Plaso = plaso;
    }

    public String getID() {
        return ID;
    }

    @Override
    public String toString() {
        return  "ID: "+ID+'\n'+
                "Plazo: "+Plaso+" meses"+'\n'+
                "Prestamo: "+MontoOriginal+'\n';
    }

    public String toStringCuentas() {
        return  "ID: "+ID+'\n'+
                "Cuota: "+ (int) Cuota+'\n'+
                "Capital: "+MontoOriginal+'\n'+
                "Monto: "+ (int) MontoconIntereses+'\n'+
                "Meses Restantes: "+Mesesrestantes+'\n';
    }

    public void PagarPrestamo(int pago) {

            if (pago < getCuota()) //Si pago menos que la cuota
            {
                JOptionPane.showMessageDialog(null,"No se permite un pago inferior a la cuota preestablecida","Advertencia",JOptionPane.INFORMATION_MESSAGE);
            }
            if (pago == getCuota()) //Si ingresa justo la cantidad que tiene que pagar, o sea la cuota exacta...
            {
                Mesesrestantes--; //Plazo -1

                MontoconIntereses -= Cuota; //Le resto la cuota(exacta que pague) al préstamo total(con intereses)

                MontoSinIntereses -= (abs(MontoSinIntereses * 0.10 - Cuota)); //Está recalculando cuanto le queda le monto a pagar(o sea el dinero tal cual que saco)

                //En ocasiones pueden quedar unos pocos colones al finalizar el pago de un préstamo.
                //Para que el préstamo se pueda pagar correctamente se realiza la verificación.
                //De esta forma no quedan unos pocos colones al terminar de pagar el préstamo.
                if (MontoSinIntereses < 100)
                {
                    MontoconIntereses = 0;
                    MontoSinIntereses = 0;
                    Mesesrestantes = 0;
                    Cuota = 0;
                    Plaso = 0;
                }
            }

            if (pago > Cuota) //Si yo pago más que la cuota...
            {
                Mesesrestantes--;
                MontoconIntereses -= pago; //Le resto lo que pague al préstamo total(con intereses)
                MontoSinIntereses -= (abs(MontoSinIntereses * 0.10 - pago)); //Está recalculando cuanto le queda le monto a pagar(osea el dinero tal cual que saco)
                Cuota = calculoCuota(MontoSinIntereses, Mesesrestantes);  //Recalcula la cuota porque pago mas

                //En ocasiones pueden quedar unos pocos colones al finalizar el pago de un préstamo.
                //Para que el préstamo se pueda pagar correctamente se realiza la verificación.
                //De esta forma no quedan unos pocos colones al terminar de pagar el préstamo.
                if (MontoSinIntereses < 100)
                {
                    MontoconIntereses = 0;
                    MontoSinIntereses = 0;
                    Mesesrestantes = 0;
                    Cuota = 0;
                    Plaso = 0;
                }
            }

            //No es necesario
            if (MontoSinIntereses < 1)
            {
                JOptionPane.showMessageDialog(null,"Felicidades! A terminado de pagar su préstamo, no le gustaría sacar otro?","Felicidades",JOptionPane.INFORMATION_MESSAGE);
            }

    }

    double calculoCuota(double M, int P)
    {
        double C;
        C = round(M * 0.10 / (1 - (pow((1 + 0.10), -P))));

        return C;
    }
}