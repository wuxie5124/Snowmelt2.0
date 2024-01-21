package ML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonUtilities {
    public void initVaribles(String filePath) {
        try {
            String[] args = new String[]{"D:\\桌面版本\\supermap-idesktopx-11.1.0-1227-8317-99321-win64-bin-zip-chs_2\\support\\MiniConda\\conda_mini\\python.exe", "D:\\桌面版本\\supermap-idesktopx-11.1.0-1227-8317-99321-win64-bin-zip-chs_2\\support\\MiniConda\\conda_mini\\tempAtt.py", filePath};
            Process proc = Runtime.getRuntime().exec(args);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
            String line;
//            while ((line = in.readLine()) != null) this.comboBoxVariableNames.addItem(line);
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
