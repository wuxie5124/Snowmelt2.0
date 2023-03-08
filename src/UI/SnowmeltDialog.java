package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class SnowmeltDialog extends JFrame {

    JPanel panelMethodConstruct;
    JPanel panelModelParamsSet;
    JPanel panelFeatureSet;
    JPanel panelCalculate;
    JMenuItem item1;
    JMenuItem item2;
    JMenuItem item3;
    JMenuItem item4;
    JMenuItem item5;
    JMenuItem item6;
    JMenuItem item7;

    public SnowmeltDialog() {
        super("基于STACKING模型融雪洪灾预测模型");
        initComponent();
        initComponentStatus();
        initLayout();
        removeEvent();
        addEvent();
        this.setSize(new Dimension(500,500));
        this.setVisible(true);
    }

    private void initComponent() {

        this.panelMethodConstruct = new JPanel();
        this.panelModelParamsSet = new JPanel();
        this.panelFeatureSet = new JPanel();
        this.panelCalculate = new JPanel();

        this.item1 = new JMenu("预测类型");
        this.item2 = new JMenuItem("方法构建");
        this.item3 = new JMenuItem("模型参数设置");
        this.item4 = new JMenuItem("特征设置");
        this.item5 = new JMenuItem("方法执行");
        this.item6 = new JMenuItem("回归");
        this.item7 = new JMenuItem("分类");

        JMenuBar menuBar = new JMenuBar();//创建菜单栏对象
        JMenu menu1 = new JMenu("融雪洪灾点预测");// 创建菜单对象

        menuBar.add(menu1);
        item1.add(item6);
        item1.add(item7);

        menu1.add(item1);
        menu1.addSeparator();//在菜单项中间添加分界线

        menu1.add(item2);
        menu1.add(item3);
        menu1.add(item4);
        menu1.add(item5);

        //
        item2.setAccelerator(KeyStroke.getKeyStroke('A'));
        item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));//添加热键

    }

    private void initComponentStatus() {

    }

    private void initLayout() {

    }

    private void removeEvent() {

    }

    private void addEvent() {
        item2.addActionListener(menuItemAction);
        item3.addActionListener(menuItemAction);
        item4.addActionListener(menuItemAction);
        item5.addActionListener(menuItemAction);
    }
    ActionListener menuItemAction = e->{
        if(e.getSource() == item1){
            
        } else if(e.getSource() == item2){
            
        } else if(e.getSource() == item3){
            
        } else if(e.getSource() == item4){
            
        }
    };
}
