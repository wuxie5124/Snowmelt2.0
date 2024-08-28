package UI;

import ML.*;
import Model.FirstTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventObject;

import static UI.SnowmeltDialog.MACHINE_LEARNS;

/*
 方法构建
 */
public class PanelMethodConstruct extends JPanel {
    private final ArrayList<MachineLearn> machineLearns;
    //第一步
    JTable table;
    JScrollPane jScrollPane;
    public PanelMethodConstruct(SnowmeltDialog dialog) {
        this.machineLearns = dialog.machineLearns;
        initComponent();
        initLayout();
        this.setBorder(BorderFactory.createTitledBorder("方法构建"));
    }

    private void initComponent() {
//        initData();
        this.table = new JTable();
        this.jScrollPane = new JScrollPane();
//        this.jScrollPane.setPreferredSize(new Dimension(300, 300));
        FirstTableModel firstTableModel = new FirstTableModel(this.machineLearns);
        this.table.setModel(firstTableModel);
        this.jScrollPane.setViewportView(table);
        tableRenderAndEditor();
    }

    private void tableRenderAndEditor() {
        table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                String va = String.valueOf(value);
                if(row == machineLearns.size()-1){
                    va = "第二层方法1";
                }else{
                    va = "第一层方法" + va;
                }
                int height = table.getRowHeight();
                this.setHorizontalAlignment(CENTER);
                table.setRowHeight(row, height * 3);
                return super.getTableCellRendererComponent(table, va, isSelected, hasFocus, row, column);
            }
        });

        table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            JComboBox jComboBox;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                jComboBox = new JComboBox();
                for (MachineLearn machineLearn : MACHINE_LEARNS) {
                    jComboBox.addItem(machineLearn.getMlName());
                }
                jComboBox.setSelectedItem((String) value);
                return jComboBox;
            }
        });
        table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JComboBox()) {
            JComboBox jComboBox;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                jComboBox = new JComboBox();
                for (MachineLearn machineLearn : MACHINE_LEARNS) {
                    jComboBox.addItem(machineLearn.getMlName());
                }
                jComboBox.setSelectedItem(value);
                return jComboBox;
            }

            @Override
            public boolean isCellEditable(EventObject anEvent) {
                return true;
            }

            @Override
            public Object getCellEditorValue() {
                Object selectedItem = jComboBox.getSelectedItem();
                if ("XGBoost".equals(selectedItem)) {
                    return new XGBoost();
                } else if ("KNN".equals(selectedItem)) {
                    return new KNN();
                } else if ("RandomForest".equals(selectedItem)) {
                    return new RF();
                } else if ("SVM".equals(selectedItem)) {
                    return new SVM();
                } else if ("GBDT".equals(selectedItem)) {
                    return new GBDT();
                }
                return null;
            }
        });
    }
    private void initLayout() {
        this.setLayout(new GridBagLayout());
        this.add(jScrollPane , new myGridBagConstraints(0,0,1,1,1,1)
                .setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER));
    }
}
