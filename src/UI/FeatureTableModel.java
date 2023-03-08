package UI;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FeatureTableModel extends DefaultTableModel {
    private String[] colunmHeaders = new String[]{"序号", "特征名", "选择"};

    public static final boolean CHOOSE = true;

    public static final boolean UNCHOOSE = false;

    private ArrayList<ParamData> paramDatas = new ArrayList<>();

    public FeatureTableModel(ArrayList<ParamData> paramDatas) {
        this.paramDatas = paramDatas;
    }

    public FeatureTableModel() {

    }


    public void setModelData(ArrayList<ParamData> paramDatas){
        this.paramDatas = paramDatas;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 2;
    }

    @Override
    public int getRowCount() {
        return paramDatas == null ? 0 : paramDatas.size();
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
            case 1:
                paramDatas.get(row).setParamName((String) aValue);
                break;
            case 2:
                paramDatas.get(row).setCheck((Boolean) aValue);
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
                return paramDatas.get(rowIndex).getParamName();
            case 2:
                return paramDatas.get(rowIndex).getCheck();
            default:
                return super.getValueAt(rowIndex, columnIndex);
        }
    }
    public ArrayList<String> getCheckedParams(){
        ArrayList<String> Params = new ArrayList<>();
        for (int i = 0; i < paramDatas.size(); i++) {
            if(paramDatas.get(i).getCheck()) Params.add(paramDatas.get(i).getParamName());
        }
        return Params;
    }
}
