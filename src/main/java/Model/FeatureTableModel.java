package Model;

import javax.print.DocFlavor;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class FeatureTableModel extends DefaultTableModel {
    private String[] colunmHeaders = new String[]{"序号", "特征名", "相关性", "选择"};

    public static final boolean CHOOSE = true;

    public static final boolean UNCHOOSE = false;

    private ArrayList<ParamData> paramDatas = new ArrayList<>();

    private Coefficient coe = Coefficient.PEARSON;

    public FeatureTableModel(ArrayList<ParamData> paramDatas) {
        this.paramDatas = paramDatas;
    }

    public FeatureTableModel() {

    }

    public void setCoe(Coefficient coe) {
        if(coe != this.coe){
            this.coe = coe;
            fireTableDataChanged();
        }
    }

    public Coefficient getCoe() {
        return coe;
    }

    public void setModelData(ArrayList<ParamData> paramDatas) {
        this.paramDatas = paramDatas;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 3;
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
            case 3:
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
                return paramDatas.get(rowIndex).getCorr(coe) ==null?"":String.valueOf(paramDatas.get(rowIndex).getCorr(coe));
            case 3:
                return paramDatas.get(rowIndex).getCheck();
            default:
                return super.getValueAt(rowIndex, columnIndex);
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return Integer.class;
            case 1:
            case 2:
                return String.class;
            case 3:
                return Boolean.class;
            default:
                return super.getColumnClass(columnIndex);
        }
    }

    public ArrayList<String> getCheckedParams() {
        ArrayList<String> Params = new ArrayList<>();
        for (int i = 0; i < paramDatas.size(); i++) {
            if (paramDatas.get(i).getCheck()) Params.add(paramDatas.get(i).getParamName());
        }
        return Params;
    }
}
