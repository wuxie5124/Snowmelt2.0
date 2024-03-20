package Model;

import ML.*;
import ML.MachineLearn;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
class ParamTableModel extends DefaultTableModel {
    //未启用
    private String[] colunmHeaders = new String[]{"编号", "方法"};

    private MachineLearn[] machineLearns = new MachineLearn[]{new GBDT(),new KNN(),new RF(),new SVM(),new XGBoost()};

    public static final boolean CHOOSE = true;

    public static final boolean UNCHOOSE = false;

    private ArrayList<MachineLearn> paramDatas;

    public ParamTableModel(ArrayList<MachineLearn> paramDatas) {
        this.paramDatas = paramDatas;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 1;
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
        if(column == 1){
            for (MachineLearn machineLearn : machineLearns) {
                if(machineLearn.getMlName() == aValue) paramDatas.set(row,machineLearn) ;
            }
        }
        this.fireTableCellUpdated(row, column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                if(rowIndex == paramDatas.size()-1){
                    return "第二层方法1";
                }else {
                    return "第一次方法" + rowIndex;
                }
            case 1:
                return paramDatas.get(rowIndex).getMlName();
            default:
                return super.getValueAt(rowIndex, columnIndex);
        }
    }
}