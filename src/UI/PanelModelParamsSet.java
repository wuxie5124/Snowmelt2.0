package UI;

import ML.MachineLearn;
import ML.Params.MLParam;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;

public class PanelModelParamsSet extends JPanel {

    private ArrayList<MachineLearn> machineLearns;
    private JTable jTable;
    private ParamAndValueTableModel paramTableModel;
    JPanel jPanel1;
    JPanel jPanel2;
    JButton jButtonOK;
    JRadioButton jRadioButton1;

    JRadioButton jRadioButton2;
    JRadioButton jRadioButton3;
    JRadioButton jRadioButton4;
    JRadioButton jRadioButton5;
    JRadioButton jRadioButton6;
    ButtonGroup group;

//    ArrayList<MachineLearn> listParams;

    MachineLearn activeMLWay;

    ArrayList<MachineLearn> tableData;

    public PanelModelParamsSet(ArrayList<MachineLearn> machineLearns) {
        this.machineLearns = machineLearns;
        initComponent();
        initActionListener();
//        initData();
    }


//    private void initData() {
//        if (this.tableData.size() == 0) {
//            this.tableData = new ArrayList<>();
//            for (int i = 0; i < this.paramList.size(); i++) {
//                {
//                    String wayName = paramList.get(i).getWay();
//                    MLAndParam mlAndParam = paramHashMaps.get(wayName);
//                    ArrayList<ParamAndValue> param = mlAndParam.getParam();
//                    ArrayList<ParamData> Params = new ArrayList<>();
//                    for (ParamAndValue paramAndValue : param) {
//                        Params.add(new ParamData(paramAndValue.getParamName(), paramAndValue.getParamValue().get(0)));
//                    }
//                    this.tableData.add(Params);
//                }
//            }
//        } else {
//
//
//        }
//    }

    public void initComponent() {
        this.jPanel1 = new JPanel();
        this.jPanel2 = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();

        this.jButtonOK = new JButton("确定");
        this.add(jPanel1, new myGridBagConstraints(0, 0, 1, 3, 1, 1).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.NORTHWEST));
        this.add(jPanel2, new myGridBagConstraints(1, 0, 1, 3, 1, 1).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.NORTHWEST));
        this.add(jButtonOK, new myGridBagConstraints(0, 3, 2, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        this.jPanel1.setLayout(gridBagLayout);
        this.jPanel2.setLayout(gridBagLayout);
        this.jPanel1.setPreferredSize(new Dimension(200, 350));
        this.jPanel2.setPreferredSize(new Dimension(320, 350));
        this.jPanel1.setBorder(BorderFactory.createTitledBorder("方法"));
        this.jPanel2.setBorder(BorderFactory.createTitledBorder("参数"));
        this.jRadioButton1 = new JRadioButton("第一层方法1");
        this.jRadioButton2 = new JRadioButton("第一层方法2");
        this.jRadioButton3 = new JRadioButton("第一层方法3");
        this.jRadioButton4 = new JRadioButton("第一层方法4");
        this.jRadioButton5 = new JRadioButton("第一层方法5");
        this.jRadioButton6 = new JRadioButton("第二层方法1");
        this.group = new ButtonGroup();
        this.group.add(jRadioButton1);
        this.group.add(jRadioButton2);
        this.group.add(jRadioButton3);
        this.group.add(jRadioButton4);
        this.group.add(jRadioButton5);
        this.group.add(jRadioButton6);

        this.jPanel1.add(jRadioButton1, new myGridBagConstraints(0, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        this.jPanel1.add(jRadioButton2, new myGridBagConstraints(0, 1, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        this.jPanel1.add(jRadioButton3, new myGridBagConstraints(0, 2, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        this.jPanel1.add(jRadioButton4, new myGridBagConstraints(0, 3, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        this.jPanel1.add(jRadioButton5, new myGridBagConstraints(0, 4, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        this.jPanel1.add(jRadioButton6, new myGridBagConstraints(0, 5, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        for (int i = 0; i < machineLearns.size(); i++) {
            jPanel1.add(new JLabel(machineLearns.get(i).getMlName()), new myGridBagConstraints(1, i, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        }
        this.activeMLWay = machineLearns.get(0);
        this.jTable = new JTable();
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(jTable);
        this.paramTableModel = new ParamAndValueTableModel(this.activeMLWay);
        this.jTable.setModel(paramTableModel);
        setEditorAndRender();
        this.jPanel2.add(jScrollPane, new myGridBagConstraints(0, 0, 1, 1, 1, 1).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER));
    }
    private void setEditorAndRender() {
        this.jTable.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            JSpinner jSpinner = new JSpinner();
            SpinnerModel spinnerModel;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                MLParam mlParam = activeMLWay.getMLParamList().get(row);
//                String paramName = mlParam.getParamName();
                String[] values = mlParam.getValues();
//                ParamAndValue param = paramHashMaps.get(ActiveMLWay).getParamByName(paramName);
                spinnerModel = new SpinnerListModel(values);
                jSpinner.setModel(spinnerModel);
                jSpinner.setValue(value);
                return jSpinner;
            }
        });
        this.jTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JTextField()) {
            JSpinner jSpinner = new JSpinner();
            SpinnerModel spinnerModel;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                MLParam mlParam = activeMLWay.getMLParamList().get(row);
//                String paramName = mlParam.getParamName();
                String[] values = mlParam.getValues();
                spinnerModel = new SpinnerListModel(values);
                jSpinner.setModel(spinnerModel);
                jSpinner.setValue(value);
                return jSpinner;
            }
            @Override
            public boolean isCellEditable(EventObject anEvent) {
                return true;
            }

            @Override
            public Object getCellEditorValue() {
                return jSpinner.getValue();
            }
        });

        this.jTable.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                int height = table.getRowHeight();
                this.setHorizontalAlignment(CENTER);
                table.setRowHeight(row, (int) (height * 2.5));
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
    }

    private ActionListener RadioActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jRadioButton1) {
                PanelModelParamsSet.this.activeMLWay = machineLearns.get(0);
            } else if (e.getSource() == jRadioButton2) {
                PanelModelParamsSet.this.activeMLWay = machineLearns.get(1);
            } else if (e.getSource() == jRadioButton3) {
                PanelModelParamsSet.this.activeMLWay = machineLearns.get(2);
            } else if (e.getSource() == jRadioButton4) {
                PanelModelParamsSet.this.activeMLWay = machineLearns.get(3);
            } else if (e.getSource() == jRadioButton5) {
                PanelModelParamsSet.this.activeMLWay = machineLearns.get(4);
            } else if (e.getSource() == jRadioButton6) {
                PanelModelParamsSet.this.activeMLWay = machineLearns.get(5);
            }
            paramTableModel = new ParamAndValueTableModel(PanelModelParamsSet.this.activeMLWay);
            jTable.setModel(paramTableModel);
            setEditorAndRender();
        }
    };

    private void initActionListener() {
        this.jRadioButton1.addActionListener(RadioActionListener);
        this.jRadioButton2.addActionListener(RadioActionListener);
        this.jRadioButton3.addActionListener(RadioActionListener);
        this.jRadioButton4.addActionListener(RadioActionListener);
        this.jRadioButton5.addActionListener(RadioActionListener);
        this.jRadioButton6.addActionListener(RadioActionListener);

        this.jButtonOK.addActionListener(e -> {
            System.out.println("111");
        });
    }

}
