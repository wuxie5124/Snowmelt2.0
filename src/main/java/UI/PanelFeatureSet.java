package UI;

import Model.FeatureTableModel;
import Model.FileChoose;
import Model.ParamData;
import Tool.PythonUtilities;
import Tool.StringUtilities;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Map;

import static Tool.ExcelUtilities.getExcelHeader;

/**
 *
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
                this.setHorizontalAlignment(CENTER);
                table.setRowHeight(row, height * 2);
                return jCheckBox;
            }
        };

        paramTable.getColumnModel().getColumn(3).setCellRenderer(defaultTableCellRenderer);
        DefaultTableCellRenderer defaultTableCellRenderer1 = new DefaultTableCellRenderer();
        defaultTableCellRenderer1.setHorizontalAlignment(0);
        paramTable.getColumnModel().getColumn(1).setCellRenderer(defaultTableCellRenderer1);
        paramTable.getColumnModel().getColumn(0).setCellRenderer(defaultTableCellRenderer1);
    }

    private void initLayout() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        jPanelSub.setLayout(new GridBagLayout());
        this.add(jScrollPane, new myGridBagConstraints(0, 0, 1, 1, 1, 1).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInset(10, 20, 0, 20));
        this.add(jPanelSub, new myGridBagConstraints(0, 4, 1, 1, 1, 1).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInset(10, 20, 0, 20));
        jPanelSub.add(jButtonRead, new myGridBagConstraints(0, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        jPanelSub.add(jButtonCorr, new myGridBagConstraints(1, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
//        jPanelSub.add(jButtonOK, new myGridBagConstraints(1, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        jPanelSub.add(jButtonClear, new myGridBagConstraints(2, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));

    }

    private void initActionListener() {
        jButtonRead.addActionListener(actionListenerPage3);
        jButtonCorr.addActionListener(actionListenerPage3);
        jButtonOK.addActionListener(actionListenerPage3);
        jButtonClear.addActionListener(actionListenerPage3);
    }

    private void initComponent() {
        jButtonRead = new JButton("读取特征数据");
        jButtonRead.setPreferredSize(new Dimension(120,25));
        jButtonOK = new JButton("确定");
        jButtonOK.setPreferredSize(new Dimension(120,25));
        jButtonCorr = new JButton("计算相关性");
        jButtonCorr.setPreferredSize(new Dimension(120,25));
        jButtonClear = new JButton("清除数据");
        jButtonClear.setPreferredSize(new Dimension(120,25));
        jPanelSub = new JPanel();

        paramTable = new JTable();
        jScrollPane = new JScrollPane();

//        String[] param = new String[]{"XJAVHRR_av", "Vegetation", "Variance_c", "Slope", "SD", "Road_densi", "Relative_E", "Particle_s", "Elevation", "Distance_f", "Curve_numb", "Agricultur"};
//        ArrayList<ParamData> paramData = new ArrayList<>();
//        for (int i = 0; i < param.length; i++) {
//            paramData.add(new ParamData(FeatureTableModel.CHOOSE, param[i]));
//        }
        this.paramTableModel.setModelData(paramData);
        paramTable.setModel(paramTableModel);
        paramTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        paramTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        paramTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        jScrollPane.setViewportView(paramTable);
        jScrollPane.setPreferredSize(new Dimension(300, 300));
    }

//    private ArrayList<String> checkedParams;
    ActionListener actionListenerPage3 = e -> {
        if (e.getSource() == jButtonRead) {
            JFileChooser fileChooser = new FileChoose();
            fileChooser.setFileFilter(new FileNameExtensionFilter("*.xls,*.xlsx","xls","xlsx"));
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String[] excelHeader = getExcelHeader(selectedFile.getPath());
                this.dialog.excelFilePath = selectedFile.getPath();
                this.paramData.clear();
                for (int i = 0; i < excelHeader.length; i++) {
                   if(i == excelHeader.length-1 && excelHeader[i].equals("Level")){
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
        }else if (e.getSource() == jButtonClear){
            this.paramData.clear();
            this.paramTableModel.fireTableDataChanged();
            this.dialog.panelConsole.addText("已清除特征数据!");
        }else if (e.getSource() == jButtonCorr){
            if (!StringUtilities.isEmptyOrNull(this.dialog.excelFilePath)){
                Map map = PythonUtilities.runCorr(this.dialog.excelFilePath);
                for (ParamData paramDatum : this.paramData) {
                    String paramName = paramDatum.getParamName();
                    paramDatum.setCorr((Double) map.get(paramName));
                }
                this.paramTableModel.fireTableDataChanged();
            }
        }
    };
}
