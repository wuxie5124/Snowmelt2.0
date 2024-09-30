package UI;

import java.awt.*;

public class MyGridBagConstraints extends GridBagConstraints {

    public MyGridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty) {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        this.weightx = weightx;
        this.weighty = weighty;
        this.fill = this.BOTH;
    }

    public MyGridBagConstraints setFill(int fill) {
        this.fill = fill;
        return this;
    }

    public MyGridBagConstraints setAnchor(int anchor) {
        this.anchor = anchor;
        return this;
    }
    public MyGridBagConstraints setInset(int top, int left, int bottom, int right){
        this.insets = new Insets(top,left,bottom,right);
        return this;
    }
}
