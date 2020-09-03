import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SUMO_Preteatment {
    //获取文件存储路径path下的所有子文件
    public static void getAllFileName(String path, ArrayList<String> listFileName) {
        File file = new File(path);
        File[] files = file.listFiles();
        // System.out.println(files[40]);
        String[] names = file.list();
        int x=0;
        if (names != null) {
            String[] completNames = new String[names.length];


            for (int i = 0; i < names.length; i++) {
                completNames[i] = names[i];
                x++;
            }
            //  System.out.println(names.length);
            listFileName.addAll(Arrays.asList(completNames));
        }
    }
    // 去掉字符串s在索引值pos处的字符
    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }
    public static void main(String[] args) {
        ArrayList<String> listFileName = new ArrayList<String>();
        double maxx=0;
        double minx=0;
        double maxy=0;
        double miny=0;
        double X = 0;
        double Y = 0;
        {
            try {
                String SUMO_filename_0 = "D:\\Users\\Administrator\\SUMO_Pretreatment\\SUMO_O\\ns2mobility.tcl"; //SUMO生成的轨迹数据文件
                String SUMO_filename_1 = "D:\\Users\\Administrator\\SUMO_Pretreatment\\SUMO_O\\ns2mobility.txt"; // SUMO轨迹文件第一次处理后存储的位置
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(SUMO_filename_0)));
                String data2 = null;
                String [] strings2 = null;
                BufferedWriter out2 = null;
                System.out.println(SUMO_filename_0);
                while ((data2=bufferedReader2.readLine()) != null){
                    strings2 = data2.split("\\s+");
                    if(strings2.length>5)
                    {
                        String regEx="[^0-9]";
                        Pattern p3 = Pattern.compile(regEx);
                        Matcher m3 = p3.matcher(strings2[3]);
                        File file2 = new File(SUMO_filename_1);
                        out2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(SUMO_filename_1, true)));
                        out2.write(strings2[2]+" "+m3.replaceAll("").trim()+" "+strings2[5]+" "+strings2[6]+" "+removeCharAt(strings2[7],strings2[7].length()-1));
                        out2.write("\n");
                        out2.flush();
                    }
                }
                //将第一次处理后生成的SUMO轨迹文件ns2mobility.txt进行二次处理，分离单个车辆的运动轨迹
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(SUMO_filename_1)));
                String outfilename = "D:\\Users\\Administrator\\SUMO_Pretreatment\\SUMO_M";
                String data = null;
                String [] strings = null;
                BufferedWriter out = null;
                while ((data = bufferedReader.readLine()) != null)
                {
                    strings = data.split(" ");// 以空格为分隔符
                    String a= outfilename+"\\"+strings[1]+".txt";
                    File file = new File(a);
                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(a, true)));
                    X= Double.valueOf(strings[2]).doubleValue();
                    Y= Double.valueOf(strings[3]).doubleValue();
                    if(strings[2].equals("NaN")|| strings[3].equals("NaN") || X<0 || Y<0 ) // 剔除异常数据
                        System.out.println(strings[0]+" "+strings[1]+" "+strings[2]+" "+strings[3]+" "+strings[4]);
                    else
                    {
                        out.write(strings[0]+" "+strings[1]+" "+strings[2]+" "+strings[3]+" "+strings[4]);
                        out.write("\n");
                    }
                    out.flush();
                    out.close();
                }
                bufferedReader.close();
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            getAllFileName("D:\\Users\\Administrator\\SUMO_Pretreatment\\SUMO_M", listFileName);
            System.out.println(listFileName);
            double next1=0;
            boolean ii=true;
            for (String name : listFileName) {
                try {
                    String infilename ="D:\\Users\\Administrator\\SUMO_Pretreatment\\SUMO_M\\"+name;
                    System.out.println(infilename);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(infilename)));
                    String outfilename = "D:\\Users\\Administrator\\SUMO_Pretreatment\\SUMO_F"; // 输出最终SUMO轨迹数据
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(outfilename+"\\"+name));
                    String data = null;
                    String [] strings = null;
                    BufferedReader  bufferedReader1 = new BufferedReader(new InputStreamReader(new FileInputStream(infilename)));
                    String [] strings1 = null;
                    String da = bufferedReader1.readLine();
                    strings1=da.split(" ");
                    String data1 = strings1[0];
                    bufferedReader1.close();
                    double net=0;
                    while ((data = bufferedReader.readLine()) != null) {
                        strings = data.split(" ");
                        if(ii==true)
                        {   minx=Double.valueOf(strings[2]).doubleValue();
                            miny=Double.valueOf(strings[3]).doubleValue();
                            ii=false;
                        }
                        if (Double.valueOf(strings[2]).doubleValue()>=maxx)   // 判断最大值
                            maxx = Double.valueOf(strings[2]).doubleValue();
                        if (Double.valueOf(strings[2]).doubleValue() <= minx)   // 判断最小值
                            minx = Double.valueOf(strings[2]).doubleValue();
                        if (Double.valueOf(strings[3]).doubleValue()>=maxy)   // 判断最大值
                            maxy = Double.valueOf(strings[3]).doubleValue();
                        if (Double.valueOf(strings[3]).doubleValue() <= miny)   // 判断最小值
                            miny = Double.valueOf(strings[3]).doubleValue();

                        // 计算节点从上一时刻到下一时刻的时间间隔
                        double b = Double.valueOf(strings[0].toString());
                        double c= Double.valueOf(data1.toString());
                        net =b-c;
                        outputStreamWriter.write(strings[1]+","+strings[0]+","+strings[2]+","+strings[3]+","+String.valueOf(net));
                        outputStreamWriter.write("\n");
                        outputStreamWriter.flush();
                        data1=strings[0];
                    }
                    bufferedReader.close();
                    outputStreamWriter.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            next1++;
        }
        // 输出车辆于运动的轨迹边界
        System.out.println("x最大值是：" + maxx); // 输出最大值
        System.out.println("x最小值是：" + minx); // 输出最小值
        System.out.println();
        System.out.println("y最大值是：" + maxy); // 输出最大值
        System.out.println("y最小值是：" + miny); // 输出最小值
    }
}
