package UI;

import ML.MachineLearn;
import ML.Params.MLParam;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ParamAndValueTableModel extends DefaultTableModel {
    private String[] colunmHeaders = new String[]{"参数", "数值"};

    private ArrayList<MLParam> paramDatas;

//    public void setParamDatas(ArrayList<MLParam> paramDatas) {
//        this.paramDatas = paramDatas;
//    }

    public ParamAndValueTableModel(MachineLearn paramDatas) {
        this.paramDatas = paramDatas.getMLParamList();
    }


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
        switch (column) {
            case 0:
                paramDatas.get(row).setName();
                break;
            case 1:
                paramDatas.get(row).setCurrentValue((String) aValue);
                break;
            default:
                super.setValueAt(aValue, row, column);
        }
        this.fireTableCellUpdated(row, column);
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return paramDatas.get(row).getParamName();
            case 1:
                return paramDatas.get(row).getCurrentValue();
            default:
                return super.getValueAt(row, column);
        }
    }

//    private void addValue() {
//        MLAndParam mlAndParam = paramHashMaps.get(ActiveMLWay);
//        ArrayList<ParamAndValue> param = mlAndParam.getParam();
//        this.listParams = new ArrayList<>();
//
//        for (ParamAndValue paramAndValue : param) {
//            this.listParams.add(new ParamData(paramAndValue.getParamName(), paramAndValue.getParamValue().get(0)));
//        }
//    }

}
