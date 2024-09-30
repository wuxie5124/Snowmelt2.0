package UI;

import Model.Coefficient;
import Model.FeatureTableModel;
import Model.MTextField;
import Model.ParamData;
import Tool.PythonUtilities;
import Tool.StringUtilities;
import com.alibaba.fastjson.JSONArray;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Map;

import static Tool.ExcelUtilities.getExcelHeader;

/**
 * 特征设置
 */


public class PanelFeatureSet extends JPanel {
    SnowmeltDialog dialog;
    FeatureTableModel paramTableModel;
    JPanel jPanelSub;
    JButton jButtonRead;
    JButton jButtonOK;
    JButton jButtonCorr;
    JButton jButtonClear;
    JScrollPane jScrollPane;
    private JTable paramTable;
    ArrayList<ParamData> paramData;
    JComboBox<String> coComboBox;
//    JTextField sortTextField;
    MTextField sortTextField;
    public PanelFeatureSet(SnowmeltDialog dialog) {
        this.paramData = dialog.paramData;
        this.dialog = dialog;
        this.paramTableModel = new FeatureTableModel();
        initComponent();
        initRenderAndEditor();
        initLayout();
        initActionListener();
        this.setBorder(BorderFactory.createTitledBorder("特征设置"));
    }

    private void initRenderAndEditor() {
        paramTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            JCheckBox jCheckBox = new JCheckBox();
            private boolean ischecked;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                ischecked = (Boolean) value;
                jCheckBox.setSelected(ischecked);
                return jCheckBox;
            }

            @Override
            public Object getCellEditorValue() {
                return jCheckBox.isSelected();
            }

            @Override
            public boolean isCellEditable(EventObject anEvent) {
                return true;
            }

        });
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox jCheckBox = new JCheckBox();
                jCheckBox.setSelected((Boolean) value);
                int height = table.getRowHeight();
                jCheckBox.setHorizontalAlignment(0);
                table.setRowHeight(row, height * 2);
                return jCheckBox;
            }
        };
        defaultTableCellRenderer.setHorizontalAlignment(0);
        paramTable.getColumnModel().getColumn(3).setCellRenderer(defaultTableCellRenderer);

        DefaultTableCellRenderer defaultTableCellRenderer1 = new DefaultTableCellRenderer();
        defaultTableCellRenderer1.setHorizontalAlignment(0);
        paramTable.getColumnModel().getColumn(0).setCellRenderer(defaultTableCellRenderer1);
        paramTable.getColumnModel().getColumn(1).setCellRenderer(defaultTableCellRenderer1);
        paramTable.getColumnModel().getColumn(2).setCellRenderer(defaultTableCellRenderer1);


//        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3"});
//        paramTable.getTableHeader().addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                JTableHeader header = (JTableHeader) e.getSource();
//                int column = header.columnAtPoint(e.getPoint());
//                if (column == 2) {
//                    // 设置 JComboBox 的位置并显示
//                    comboBox.setSelectedItem(header.getTable().getColumnModel().getColumn(column).getHeaderValue());
//                    comboBox.setBounds(header.getHeaderRect(column));
//                    comboBox.setVisible(true);
//                    header.add(comboBox);
//
//                    comboBox.requestFocus();
//                    comboBox.addActionListener(event -> {
//                        // 更新列头的值
//                        header.getTable().getColumnModel().getColumn(column).setHeaderValue(comboBox.getSelectedItem());
//                        header.repaint();
//                        comboBox.setVisible(false);
//                    });
//                }
//            }
//        });
    }

    private void initLayout() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        jPanelSub.setLayout(new GridBagLayout());
        this.add(jScrollPane, new MyGridBagConstraints(0, 0, 1, 4, 1, 4).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInset(10, 20, 0, 20));
        this.add(jPanelSub, new MyGridBagConstraints(0, 4, 1, 1, 1, 0).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInset(10, 20, 0, 20));
        jPanelSub.add(jButtonRead, new MyGridBagConstraints(0, 0, 1, 1, 1, 0.5).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setInset(0,0,10,0));
        jPanelSub.add(jButtonCorr, new MyGridBagConstraints(1, 0, 1, 1, 1, 0.5).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER).setInset(0,0,10,0));
//        jPanelSub.add(jButtonOK, new myGridBagConstraints(1, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        jPanelSub.add(jButtonClear, new MyGridBagConstraints(2, 0, 1, 1, 1, 0.5).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        jPanelSub.add(coComboBox, new MyGridBagConstraints(0, 1, 1, 1, 1, 0.5).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        jPanelSub.add(sortTextField, new MyGridBagConstraints(1, 1, 1, 1, 1, 0.5).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GridBagConstraints.CENTER));
    }

    private void initActionListener() {
        jButtonRead.addActionListener(actionListenerPage3);
        jButtonCorr.addActionListener(actionListenerPage3);
        jButtonOK.addActionListener(actionListenerPage3);
        jButtonClear.addActionListener(actionListenerPage3);
        coComboBox.addActionListener(comBoboxAction);
        sortTextField.addBtAcListener(sortCorAction);
    }

    private void initComponent() {
        this.jButtonRead = new JButton("读取特征数据");
        this.jButtonRead.setPreferredSize(new Dimension(120, 25));
        this.jButtonOK = new JButton("确定");
        this.jButtonOK.setPreferredSize(new Dimension(120, 25));
        this.jButtonCorr = new JButton("计算相关性");
        this.jButtonCorr.setPreferredSize(new Dimension(120, 25));
        this.jButtonClear = new JButton("清除数据");
        this.jButtonClear.setPreferredSize(new Dimension(120, 25));
        this.coComboBox = new JComboBox<>();
        this.coComboBox.setPreferredSize(new Dimension(120, 25));
        this.coComboBox.addItem("PEARSON");
        this.coComboBox.addItem("SPEARMAN");
        this.coComboBox.addItem("KENDALL");
        this.coComboBox.addItem("MIC");
        this.sortTextField = new MTextField();
        this.sortTextField.setPreferredSize(new Dimension(120,25));
        this.sortTextField.setButtonName("设置");
        this.sortTextField.setLabelName("筛选值");
        this.jPanelSub = new JPanel();
        this.paramTable = new JTable();
        this.jScrollPane = new JScrollPane();
        this.paramTableModel.setModelData(paramData);
        this.paramTable.setModel(paramTableModel);
        this.paramTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.paramTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        this.paramTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        this.jScrollPane.setViewportView(paramTable);
        this.jScrollPane.setPreferredSize(new Dimension(300, 300));
    }
    ActionListener actionListenerPage3 = e -> {
        if (e.getSource() == jButtonRead) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("*.xls,*.xlsx", "xls", "xlsx"));
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String[] excelHeader = getExcelHeader(selectedFile.getPath());
                this.dialog.excelFilePath = selectedFile.getPath();
                this.paramData.clear();
                for (int i = 0; i < excelHeader.length; i++) {
                    if (i == excelHeader.length - 1 && excelHeader[i].equals("Level")) {
                        continue;
                    }
                    this.paramData.add(new ParamData(FeatureTableModel.CHOOSE, excelHeader[i]));
                }
                this.paramTableModel.fireTableDataChanged();
                this.dialog.panelConsole.addText("已读取特征数据!");
            }
        } else if (e.getSource() == jButtonOK) {
//            this.checkedParams = this.paramTableModel.getCheckedParams();
//            snowmeltDialog.setCheckedParams(this.checkedParams);
        } else if (e.getSource() == jButtonClear) {
            this.paramData.clear();
            this.paramTableModel.fireTableDataChanged();
            this.dialog.panelConsole.addText("已清除特征数据!");
        } else if (e.getSource() == jButtonCorr) {
            if (!StringUtilities.isEmptyOrNull(this.dialog.excelFilePath)) {
                Map map = PythonUtilities.runCoefficient(this.dialog.excelFilePath);
                for (ParamData paramDatum : this.paramData) {
                    String paramName = paramDatum.getParamName();
                    if (((JSONArray) map.get(paramName)).get(0) != null) {
                        paramDatum.setPearson(((BigDecimal) ((JSONArray) map.get(paramName)).get(0)).doubleValue());
                    }
                    if (((JSONArray) map.get(paramName)).get(1) != null) {
                        paramDatum.setSpearman(((BigDecimal) ((JSONArray) map.get(paramName)).get(1)).doubleValue());
                    }
                    if (((JSONArray) map.get(paramName)).get(2) != null) {
                        paramDatum.setKendall(((BigDecimal) ((JSONArray) map.get(paramName)).get(2)).doubleValue());
                    }
                    if (((JSONArray) map.get(paramName)).get(3) != null) {
                        paramDatum.setMic(((BigDecimal) ((JSONArray) map.get(paramName)).get(3)).doubleValue());
                    }
                }
                this.paramTableModel.fireTableDataChanged();
            }
        }
    };
    ActionListener comBoboxAction = e -> {
        String selectedItem = (String) coComboBox.getSelectedItem();
        if (selectedItem.equals("PEARSON")) {
            paramTableModel.setCoe(Coefficient.PEARSON);
        } else if (selectedItem.equals("SPEARMAN")) {
            paramTableModel.setCoe(Coefficient.SPEARMAN);
        } else if (selectedItem.equals("KENDALL")) {
            paramTableModel.setCoe(Coefficient.KENDALL);
        } else if (selectedItem.equals("MIC")) {
            paramTableModel.setCoe(Coefficient.MIC);
        }
    };

    ActionListener sortCorAction = e->{
        String text = sortTextField.getText();
        if (!StringUtilities.isEmptyOrNull(text)){
            Double aDouble = Double.valueOf(text);
            for (ParamData paramDatum : paramData) {
                paramDatum.setCheck(paramDatum.getCorr(paramTableModel.getCoe()) == null ? false:paramDatum.getCorr(paramTableModel.getCoe()) >= aDouble);
            }
            paramTableModel.fireTableDataChanged();
        }
    };
}

