package modelo;

import com.sun.tools.xjc.model.Model;
import modelo.Cliente;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel {

    private List<Cliente> filas;
    private String column[] = {"Cédula", "Nombre", "Provincia","Canton","Distrito","Prestamos"};

    public TableModel(List<Cliente> filas) {
        this.filas = filas;
    }

    @Override
    public int getRowCount() {
        return filas.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Cliente cliente = filas.get(rowIndex);
        switch(columnIndex) {
            case 0: return cliente.getCedula();
            case 1: return cliente.getNombre();
            case 2: return cliente.getProvincia();
            case 3: return cliente.getCanton();
            case 4: return cliente.getDistrito();
            case 5: return cliente.getPrestamos().getPrestamos().size();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        return column[col];
    }
}