package modelo;

import com.sun.tools.xjc.model.Model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModelPrestamo extends AbstractTableModel {

    private List<Prestamo> filas;
    private String column[] = {"ID","Cuota","Capital","Monto","Meses Restantes","Plazo","Préstamo"};

    public TableModelPrestamo(List<Prestamo> filas) {
        this.filas = filas;
    }

    @Override
    public int getRowCount() {
        return filas.size();
    }

    @Override
    public int getColumnCount() {return 7;}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Prestamo prest = filas.get(rowIndex);
        switch(columnIndex) {
            case 0: return prest.getID();
            case 1: return (int) prest.getCuota();
            case 2: return prest.getMonto();
            case 3: return (int) prest.getMontoconIntereses();
            case 4: return prest.getMesesrestantes();
            case 5: return prest.getPlaso();
            case 6: return prest.getMontoOriginal();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        return column[col];
    }
}
