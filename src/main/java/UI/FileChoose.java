package UI;

import javax.swing.*;
import java.awt.*;

public class FileChoose extends JFileChooser {
    private ChooseState state;
    public FileChoose() throws HeadlessException {
        super();
        JFrame jFrame = new JFrame();
        state = ChooseState.getEnum(this.showOpenDialog(jFrame));
    }

    public ChooseState getSTATE() {
        return state;
    }
}
