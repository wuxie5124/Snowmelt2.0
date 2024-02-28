package UI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.EventObject;

import static Tool.ExcelUtilities.getExcelHeader;

public class PanelFeatureSet extends JPanel {
    private SnowmeltDialog snowmeltDialog;
    FeatureTableModel paramTableModel;
    JPanel jPanelSub;
    JButton jButtonRead;
    JButton jButtonOK;
    JScrollPane jScrollPane;
    private JTable paramTable;
    ArrayList<ParamData> paramData;


    public PanelFeatureSet(SnowmeltDialog snowmeltDialog, ArrayList<ParamData> paramData) {
        this.paramData = paramData;
        this.snowmeltDialog = snowmeltDialog;
        this.paramTableModel = new FeatureTableModel();
        initComponent();
        initRenderAndEditor();
        initLayout();
        initActionListener();
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

        paramTable.getColumnModel().getColumn(2).setCellRenderer(defaultTableCellRenderer);
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
        this.add(jPanelSub, new myGridBagConstraints(0, 4, 1, 1, 1, 1).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.CENTER).setInset(10, 20, 20, 20));
        jPanelSub.add(jButtonRead, new myGridBagConstraints(0, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));
        jPanelSub.add(jButtonOK, new myGridBagConstraints(1, 0, 1, 1, 1, 1).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.CENTER));

    }

    private void initActionListener() {
        jButtonRead.addActionListener(actionListenerPage3);
        jButtonOK.addActionListener(actionListenerPage3);
    }

    private void initComponent() {
        jButtonRead = new JButton("读取");
        jButtonOK = new JButton("确定");
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
            FileChoose fileChoose = new FileChoose();
            fileChoose.setFileFilter(new FileNameExtensionFilter("*.xls,*.xlsx","xls","xlsx"));
            if (fileChoose.getSTATE() == ChooseState.OK) {
                File selectedFile = fileChoose.getSelectedFile();
                String[] excelHeader = getExcelHeader(selectedFile.getPath());
                this.snowmeltDialog.excelFilePath = selectedFile.getPath();
                this.paramData.clear();
                for (int i = 0; i < excelHeader.length; i++) {
                   if(i == excelHeader.length-1 && excelHeader[i].equals("Level")){
                       continue;
                   }
                   this.paramData.add(new ParamData(FeatureTableModel.CHOOSE, excelHeader[i]));
                }
                this.paramTableModel.fireTableDataChanged();
            }
        } else if (e.getSource() == jButtonOK) {
//            this.checkedParams = this.paramTableModel.getCheckedParams();
//            snowmeltDialog.setCheckedParams(this.checkedParams);
        }
    };
}
