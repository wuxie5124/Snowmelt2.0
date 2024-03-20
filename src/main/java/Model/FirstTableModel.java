package Model;

import ML.MachineLearn;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class FirstTableModel extends DefaultTableModel {
    private String[] colunmHeaders = new String[]{"方法", "参数"};

    public static final boolean CHOOSE = true;

    public static final boolean UNCHOOSE = false;

    private ArrayList<MachineLearn> machineLearns = new ArrayList<>();

    public FirstTableModel(ArrayList<MachineLearn> machineLearns) {
        this.machineLearns = machineLearns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 1;
    }

    @Override
    public int getRowCount() {
        return machineLearns == null ? 0 : machineLearns.size();
    }

    @Override
    public int getColumnCount() {
        return colunmHeaders.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunmHeaders[column];
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        switch (column) {
//            case 0:
//                machineLearns.get(row).setLevel((int) aValue);
//                break;
            case 1:
                machineLearns.set(row, ((MachineLearn) aValue));
                break;
            default:
                super.setValueAt(aValue, row, column);
        }
        this.fireTableCellUpdated(row, column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return machineLearns.get(rowIndex).getMlName();
            default:
                return super.getValueAt(rowIndex, columnIndex);
        }
    }
}
