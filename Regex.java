/**
 * Created by tkxb on 16/11/30.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void main(String[] args) {
        String filePath = "/Users/tkxb/Downloads/weak.txt";
        Pattern p = Pattern.compile("^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)(?![A-Za-z]+$)(?![A-Z\\d]+$)(?![A-Z\\W_]+$)(?![a-z\\d]+$)(?![a-z\\W_]+$)(?![\\d\\W_]+$)\\S{8,30}$");

        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    Matcher m = p.matcher(lineTxt);
                    if (m.find()){
                        System.out.println(lineTxt);
                        WriteFile(lineTxt);
                    }
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }
    private static void WriteFile(String result){
        try{
            File file =new File("/Users/tkxb/Downloads/result.txt");

            //if file doesnt exists, then create it
            if(!file.exists()){
                file.createNewFile();
            }

            //true = append file
            FileWriter writer = new FileWriter(file, true);
            writer.write(result+'\n');
            writer.close();

            System.out.println("Done");

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
