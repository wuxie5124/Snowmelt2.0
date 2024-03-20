package Model;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class DataChooseTableModel extends DefaultTableModel {
    private String[] colunmHeaders = new String[]{"序号", "特征名", "影像"};

    public static final boolean CHOOSE = true;

    public static final boolean UNCHOOSE = false;

    private ArrayList<ParamAndTiff> paramAndTiffs;

    public DataChooseTableModel() {

    }
    public void setModelData(ArrayList<ParamAndTiff> paramAndTiffs){
        this.paramAndTiffs = paramAndTiffs;
    }

    public int getRowCount() {
        return paramAndTiffs == null ? 0 : paramAndTiffs.size();
    }

    public int getColumnCount() {
        return colunmHeaders.length;
    }

    public String getColumnName(int column) {
        return colunmHeaders[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return paramAndTiffs.get(rowIndex).getParamName();
            case 2:
                return paramAndTiffs.get(rowIndex).getTiffName();
            default:
                return super.getValueAt(rowIndex, columnIndex);
        }
    }


}
