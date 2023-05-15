package Tool;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtilities {

    public static String getFileNameExtension(File file) {
        if (file == null) {
            return null;
        } else if (file.isDirectory()) {
            return null;
        } else {
            String fileName = file.getName();
            int lastDotIndex = fileName.lastIndexOf(".");
            if (lastDotIndex != -1 && lastDotIndex != fileName.length() - 1) {
                fileName = fileName.substring(lastDotIndex + 1);
            }

            return fileName;
        }
    }

    public static String getFileNameExtension(String filePath) {
        return ExcelUtilities.isNullOrEmpty(filePath) ? null : getFileNameExtension(new File(filePath));
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static String[] getExcelHeader(String filePath) {
        File file = new File(filePath);
        String extension = ExcelUtilities.getFileNameExtension(filePath);
        try {
            if (extension.equals("xlsx")) {
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(filePath));
                XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
                int lastCellNum = sheet.getRow(0).getLastCellNum();
                String[] names = new String[lastCellNum];
                for (int i = 0; i < lastCellNum; i++) {
                    names[i] = sheet.getRow(0).getCell(i).toString();
                }
                return names;
            } else if (extension.equals("xls")) {
                HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(filePath));
                //获取工作簿下sheet的个数
                int sheetNum = hssfWorkbook.getNumberOfSheets();
                HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
                int lastCellNum = sheet.getRow(0).getLastCellNum();
                String[] names = new String[lastCellNum];
                for (int i = 0; i < lastCellNum; i++) {
                    names[i] = sheet.getRow(0).getCell(i).toString();
                }
                return names;
            } else if (extension.equals("csv")) {
                BufferedReader reade = new BufferedReader(new FileReader(filePath));//换成你的文件名
                String line = null;
                if ((line = reade.readLine()) != null) {
                    return line.split(",");
                }
                return null;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String[0];
    }

}
