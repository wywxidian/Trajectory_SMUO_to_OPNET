import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.math.BigDecimal;
import java.util.Arrays;
class NumberUtils {
    /**
     * 默认除法运算精度
     */
    private static final int DEF_DIV_SCALE = 10;
    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    /**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }
    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1,double v2){
        return div(v1,v2,DEF_DIV_SCALE);
    }
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    /**
     * 提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的类型转换(Float)
     * @param v 需要被转换的数字
     * @return 返回转换结果
     */
    public static float convertsToFloat(double v){
        BigDecimal b = new BigDecimal(v);
        return b.floatValue();
    }

    /**
     * 提供精确的类型转换(Int)不进行四舍五入
     * @param v 需要被转换的数字
     * @return 返回转换结果
     */
    public static int convertsToInt(double v){
        BigDecimal b = new BigDecimal(v);
        return b.intValue();
    }

    /**
     * 提供精确的类型转换(Long)
     * @param v 需要被转换的数字
     * @return 返回转换结果
     */
    public static long convertsToLong(double v){
        BigDecimal b = new BigDecimal(v);
        return b.longValue();
    }

    /**
     * 返回两个数中大的一个值
     * @param v1 需要被对比的第一个数
     * @param v2 需要被对比的第二个数
     * @return 返回两个数中大的一个值
     */
    public static double returnMax(double v1,double v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.max(b2).doubleValue();
    }

    /**
     * 返回两个数中小的一个值
     * @param v1 需要被对比的第一个数
     * @param v2 需要被对比的第二个数
     * @return 返回两个数中小的一个值
     */
    public static double returnMin(double v1,double v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.min(b2).doubleValue();
    }

    /**
     * 精确对比两个数字
     * @param v1 需要被对比的第一个数
     * @param v2 需要被对比的第二个数
     * @return 如果两个数一样则返回0，如果第一个数比第二个数大则返回1，反之返回-1
     */

    public static int compareTo(double v1,double v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.compareTo(b2);
    }

}
class Dot {
    Integer serial;//车辆序号
    Double x;//X坐标
    Double Y;//Y坐标
    Integer altitude;//海拔
    double  Rate;
    String step = "0h0m0.0s";//步长
    String wait_time; //时间间隔
    public void setx(Double x) {
        this.x = x ;
    }
    public void setSerial(Integer serial) {this.serial = serial;}
    public void setY(Double Y) {
        this.Y = Y;
    }
    public void setAltitude(int altitude) { this.altitude = altitude; }
    public void setRate(Double Rate) {
        this.Rate = Rate;
    }
    public void setwait_time(String wait_time) { this.wait_time = wait_time; }
    public Integer getSerial() {
        return serial;
    }
    public Double getx() {
        return x;
    }
    public Double getY() {
        return Y;
    }
    public Integer getaltitude() { return altitude; }
    public Double getRate() {
        return Rate;
    }
    public String getwait_time() {
        return wait_time;
    }
    public String getstep() { return step; }
}
class ReadFile2Txt{
    public static String getTenScale(double v1){
        BigDecimal bg = new BigDecimal(v1);
        String s = bg.setScale(10, BigDecimal.ROUND_HALF_UP).toString();
        return s;
    }

    public static double getTwtn(String v1,double v2){
        BigDecimal b1 = new BigDecimal(v1);

        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        System.out.println(b2);
        System.out.println(b1.add(b2).doubleValue());
        return b1.add(b2).doubleValue();
    }
    public static String getTwtn1 (String v1){
        //BigDecimal b1 = new BigDecimal(v1);
        //  BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return v1;
    }
    public static double getTwtnWait (String v1,double v2){
        BigDecimal b1 = new BigDecimal(v1);
        //  System.out.println(b1);
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
}
public class SUMO_To_OPNET_Trj {
    //遍历文件夹下所有文件
    public static void getAllFileName(String path, ArrayList<String> listFileName) {
        File file = new File(path);
        File[] files = file.listFiles();
        String[] names = file.list();
        if (names != null) {
            String[] completNames = new String[names.length];
            for (int i = 0; i < names.length; i++) {
                completNames[i] = names[i];
            }
            listFileName.addAll(Arrays.asList(completNames));
        }
    }
    public static void main(String[] args) throws IOException {
        ArrayList<String> listFileName = new ArrayList<String>();
        getAllFileName("D:\\Users\\Administrator\\IdeaProjects\\SUMO_To_OPNET_Trajectory\\LF", listFileName);
        int index = 0;
        for (String name : listFileName) {
            String infilename = "D:\\Users\\Administrator\\IdeaProjects\\SUMO_To_OPNET_Trajectory\\LF\\" + name;
            System.out.println(infilename);
            HashMap<Integer, List<Dot>> Map = new HashMap<Integer, List<Dot>>();
            FileInputStream input = new FileInputStream(infilename);
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader br = new BufferedReader(isr);
            String data = null;
            byte[] bytes = null;
            while ((data = br.readLine()) != null) {
                String txt = data;
                String[] les = txt.split(",");
                Dot dot = null;
                if (txt.startsWith("#")) {
                    continue;
                } else {
                    if (les.length <= 4) {
                        continue;
                    } else {

                        dot = new Dot();
                        dot.setSerial(Integer.valueOf(les[0].trim()));
                        dot.setx(Double.valueOf(les[2].trim()));
                        dot.setY(Double.valueOf(les[3].trim()));
                        dot.setAltitude(0);
                        dot.setRate(Double.valueOf(les[4].trim()));
                        List<Dot> dotList = Map.get(dot.getSerial());
                        if (dotList == null) {
                            dotList = new ArrayList<Dot>();
                            dotList.add(dot);

                            Map.put(dot.getSerial(), dotList);
                        } else {
                            dotList.add(dot);
                        }

                    }
                }


            }
            Iterator<Entry<Integer, List<Dot>>> it = Map.entrySet().iterator();
            while (it.hasNext()) {
                Entry<Integer, List<Dot>> entry = it.next();
                String outfilename = "D:\\Users\\Administrator\\IdeaProjects\\SUMO_To_OPNET_Trajectory\\L_10m_s";
                FileOutputStream outFile = new FileOutputStream(outfilename + "\\" + "10ms_2_Real_SUMO_" + index + ".trj");
                System.out.println(outfilename + "\\" + "10ms_2_Real_SUMO_" + index + ".trj");
                index ++;
                List<Dot> dList1 = entry.getValue();
                StringBuffer bp = null;
                bp = new StringBuffer();
                bp.append("Version: 2");
                bp.append("\r\n");
                bp.append("Position_Unit: Meters");
                bp.append("\r\n");
                bp.append("Altitude_Unit: Meters");
                bp.append("\r\n");
                bp.append("Coordinate_Method: absolute");
                bp.append("\r\n");
                bp.append("Altitude_Method: absolute");
                bp.append("\r\n");
                bp.append("locale: C");
                bp.append("\r\n");
                bp.append("Coordinate_Count: " + dList1.size());
                bp.append("\r\n");
                bp.append("# X Position       ,Y Position        ,Altitude            ,Traverse Time               ,Wait Time");
                bp.append("\r\n");
                String msg1 = bp.toString();

                bytes = msg1.getBytes();
                outFile.write(bytes, 0, bytes.length);
                // outFile.close();
                List<Dot> dList = entry.getValue();

                StringBuffer bf = null;
                for (int i = 0; i < dList.size(); i++) {
                    Dot dot = dList.get(i);

                    if (i == 0) {
                        dot.setwait_time("0h0m0.0");

                    } else {
                        Dot lastdot = dList.get(i - 1);
                        Double d1 = NumberUtils.sub(dot.getx(), lastdot.getx());
                        Double d2 = NumberUtils.sub(dot.getY(), lastdot.getY());
                        Double d13 = NumberUtils.mul(d1, d1);
                        Double d23 = NumberUtils.mul(d2, d2);
                        Double d4 = NumberUtils.add(d13, d23);
                        Double rt = Math.sqrt(d4);
                        Double time=0.0;
                        if(rt==0)
                            continue;
                        else  time = NumberUtils.div(rt,10.001, 20);  // 车辆的速度可以设定
                        dot.setwait_time("0h0m" + time.toString());
                    }
                    bf = new StringBuffer();
                    bf.append(ReadFile2Txt.getTenScale(dot.getx()));
                    bf.append("    ");
                    bf.append(",");

                    bf.append(ReadFile2Txt.getTenScale(dot.getY()));
                    bf.append("   ");
                    bf.append(",");

                    bf.append(dot.getaltitude());
                    bf.append("                   ");
                    bf.append(",");

                    bf.append(dot.getwait_time());
                    bf.append("s");
                    bf.append("                    ");
                    bf.append(",");

                    bf.append(dot.getstep() + "");
                    bf.append("   ");
                    bf.append("\r\n");
                    String msg = bf.toString();

                    bytes = msg.getBytes();
                    outFile.write(bytes, 0, bytes.length);
                }
                outFile.close();
            }
            input.close();
            System.out.println("=========================>Done");
        }
    }
}
