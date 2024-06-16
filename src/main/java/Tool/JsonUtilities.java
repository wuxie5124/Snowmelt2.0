package Tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import java.io.*;


public class JsonUtilities {
    public static JSONObject readJsonFile (String filePath){
//        String jsonPath = "C:\\Users\\zhangjunmin\\Desktop\\11\\gridSearchParam.json";
        File file = new File(filePath);
        if (!file.exists()){
            return null;
        }
        String jsonStr = null;
        try {
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file), "Utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
        } catch (IOException e) {
            System.out.print(e);
        }
        JSONObject jsonObject = jsonStr == null? null:JSON.parseObject(jsonStr);
        return jsonObject;
    }
}
