package com.example.jobdemo.util;

/**
 * @Author LYX
 * @Date 2022/9/27 16:45
 */
/**
 * 百度坐标（BD09）、国测局坐标（火星坐标，GCJ02）、和WGS84坐标系之间的转换的工具
 *
 * 参考 https://github.com/wandergis/coordtransform 实现的Java版本
 * @author geosmart
 */
public class CoordinateTransformUtil {
    static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
    // π
    static double pi = 3.1415926535897932384626;
    // 长半轴
    static double a = 6378245.0;
    // 扁率
    static double ee = 0.00669342162296594323;

    /**
     * 百度坐标系(BD-09)转WGS坐标
     *
     * @param lng 百度坐标纬度
     * @param lat 百度坐标经度
     * @return WGS84坐标数组
     */
    public static double[] bd09towgs84(double lng, double lat) {
        double[] gcj = bd09togcj02(lng, lat);
        double[] wgs84 = gcj02towgs84(gcj[0], gcj[1]);
        return wgs84;
    }

    /**
     * WGS坐标转百度坐标系(BD-09)
     *
     * @param lng WGS84坐标系的经度
     * @param lat WGS84坐标系的纬度
     * @return 百度坐标数组
     */
    public static double[] wgs84tobd09(double lng, double lat) {
        double[] gcj = wgs84togcj02(lng, lat);
        double[] bd09 = gcj02tobd09(gcj[0], gcj[1]);
        return bd09;
    }

    /**
     * 火星坐标系(GCJ-02)转百度坐标系(BD-09)
     *
     * 谷歌、高德——>百度
     * @param lng 火星坐标经度
     * @param lat 火星坐标纬度
     * @return 百度坐标数组
     */
    public static double[] gcj02tobd09(double lng, double lat) {
        double z = Math.sqrt(lng * lng + lat * lat) + 0.00002 * Math.sin(lat * x_pi);
        double theta = Math.atan2(lat, lng) + 0.000003 * Math.cos(lng * x_pi);
        double bd_lng = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        return new double[] { bd_lng, bd_lat };
    }

    /**
     * 百度坐标系(BD-09)转火星坐标系(GCJ-02)
     *
     * 百度——>谷歌、高德
     * @param bd_lon 百度坐标纬度
     * @param bd_lat 百度坐标经度
     * @return 火星坐标数组
     */
    public static double[] bd09togcj02(double bd_lon, double bd_lat) {
        double x = bd_lon - 0.0065;
        double y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double gg_lng = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);
        return new double[] { gg_lng, gg_lat };
    }

    /**
     * WGS84转GCJ02(火星坐标系)
     *
     * @param lng WGS84坐标系的经度
     * @param lat WGS84坐标系的纬度
     * @return 火星坐标数组
     */
    public static double[] wgs84togcj02(double lng, double lat) {
        if (out_of_china(lng, lat)) {
            return new double[] { lng, lat };
        }
        double dlat = transformlat(lng - 105.0, lat - 35.0);
        double dlng = transformlng(lng - 105.0, lat - 35.0);
        double radlat = lat / 180.0 * pi;
        double magic = Math.sin(radlat);
        magic = 1 - ee * magic * magic;
        double sqrtmagic = Math.sqrt(magic);
        dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * pi);
        dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * pi);
        double mglat = lat + dlat;
        double mglng = lng + dlng;
        return new double[] { mglng, mglat };
    }

    /**
     * GCJ02(火星坐标系)转GPS84
     *
     * @param lng 火星坐标系的经度
     * @param lat 火星坐标系纬度
     * @return WGS84坐标数组
     */
    public static double[] gcj02towgs84(double lng, double lat) {
        if (out_of_china(lng, lat)) {
            return new double[] { lng, lat };
        }
        double dlat = transformlat(lng - 105.0, lat - 35.0);
        double dlng = transformlng(lng - 105.0, lat - 35.0);
        double radlat = lat / 180.0 * pi;
        double magic = Math.sin(radlat);
        magic = 1 - ee * magic * magic;
        double sqrtmagic = Math.sqrt(magic);
        dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * pi);
        dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * pi);
        double mglat = lat + dlat;
        double mglng = lng + dlng;
        return new double[] { lng * 2 - mglng, lat * 2 - mglat };
    }

    /**
     * 纬度转换
     *
     * @param lng
     * @param lat
     * @return
     */
    public static double transformlat(double lng, double lat) {
        double ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * pi) + 20.0 * Math.sin(2.0 * lng * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lat * pi) + 40.0 * Math.sin(lat / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(lat / 12.0 * pi) + 320 * Math.sin(lat * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    /**
     * 经度转换
     *
     * @param lng
     * @param lat
     * @return
     */
    public static double transformlng(double lng, double lat) {
        double ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * pi) + 20.0 * Math.sin(2.0 * lng * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lng * pi) + 40.0 * Math.sin(lng / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(lng / 12.0 * pi) + 300.0 * Math.sin(lng / 30.0 * pi)) * 2.0 / 3.0;
        return ret;
    }

    /**
     * 判断是否在国内，不在国内不做偏移
     *
     * @param lng
     * @param lat
     * @return
     */
    public static boolean out_of_china(double lng, double lat) {
        if (lng < 72.004 || lng > 137.8347) {
            return true;
        } else if (lat < 0.8293 || lat > 55.8271) {
            return true;
        }
        return false;
    }

    /**
     * 84转2000
     * @param longitude 经度
     * @param latitude 纬度
     * @return 输出值为经度（x），纬度（y）
     */
    public static double[] WGS84ToCGS2000(double longitude, double latitude)
    {
        double[] output = new double[2];
        double longitude1,latitude1, longitude0, X0,Y0, xval,yval;
        //NN曲率半径，测量学里面用N表示
        //M为子午线弧长，测量学里用大X表示
        //fai为底点纬度，由子午弧长反算公式得到，测量学里用Bf表示
        //R为底点所对的曲率半径，测量学里用Nf表示
        double a,f, e2,ee, NN, T,C,A, M, iPI;
        iPI = 0.0174532925199433; //3.1415926535898/180.0;
        a=6378137.0; f=1/298.257222101; //CGCS2000坐标系参数
        //a=6378137.0; f=1/298.2572236; //wgs84坐标系参数
        longitude0 = 117;//中央子午线 根据实际进行配置
        longitude0 = longitude0 * iPI ;//中央子午线转换为弧度
        longitude1 = longitude * iPI ; //经度转换为弧度
        latitude1 = latitude * iPI ; //纬度转换为弧度
        e2=2*f-f*f;
        ee=e2*(1.0-e2);
        NN=a/Math.sqrt(1.0-e2*Math.sin(latitude1)*Math.sin(latitude1));
        T=Math.tan(latitude1)*Math.tan(latitude1);
        C=ee*Math.cos(latitude1)*Math.cos(latitude1);
        A=(longitude1-longitude0)*Math.cos(latitude1);
        M=a*((1-e2/4-3*e2*e2/64-5*e2*e2*e2/256)*latitude1-(3*e2/8+3*e2*e2/32+45*e2*e2
                *e2/1024)*Math.sin(2*latitude1)
                +(15*e2*e2/256+45*e2*e2*e2/1024)*Math.sin(4*latitude1)-(35*e2*e2*e2/3072)*Math.sin(6*latitude1));
        xval = NN*(A+(1-T+C)*A*A*A/6+(5-18*T+T*T+72*C-58*ee)*A*A*A*A*A/120);
        yval = M+NN*Math.tan(latitude1)*(A*A/2+(5-T+9*C+4*C*C)*A*A*A*A/24
                +(61-58*T+T*T+600*C-330*ee)*A*A*A*A*A*A/720);
        X0 = 500000L;
        Y0 = 0;
        xval = xval+X0; yval = yval+Y0;

        //转换为投影
        output[0] = xval;
        output[1] = yval;

        return output;
    }

    private static double formatby6(double num) {
        String result = String.format("%.6f", num);
        return Double.valueOf(result);
    }

    /**
     * 2000转84
     * @param X 纬度
     * @param Y 经度
     * @return 输出result的顺序为result[0]纬度（y），result[1]经度
     */
    public static double [] CGS2000ToWGS84(double X, double Y) {
        double L0 = 117;//中央子午线需根据实际情况设置
        double lat ,lon;
        Y-=500000;
        double []  result  = new double[2];
        double iPI = 0.0174532925199433;//pi/180
        double a = 6378137.0; //长半轴 m
        double b = 6356752.31414; //短半轴 m
        double f = 1/298.257222101;//扁率 a-b/a
        double e = 0.0818191910428; //第一偏心率 Math.sqrt(5)
        double ee = Math.sqrt(a*a-b*b)/b; //第二偏心率
        double bf = 0; //底点纬度
        double a0 = 1+(3*e*e/4) + (45*e*e*e*e/64) + (175*e*e*e*e*e*e/256) + (11025*e*e*e*e*e*e*e*e/16384) + (43659*e*e*e*e*e*e*e*e*e*e/65536);
        double b0 = X/(a*(1-e*e)*a0);
        double c1 = 3*e*e/8 +3*e*e*e*e/16 + 213*e*e*e*e*e*e/2048 + 255*e*e*e*e*e*e*e*e/4096;
        double c2 = 21*e*e*e*e/256 + 21*e*e*e*e*e*e/256 + 533*e*e*e*e*e*e*e*e/8192;
        double c3 = 151*e*e*e*e*e*e*e*e/6144 + 151*e*e*e*e*e*e*e*e/4096;
        double c4 = 1097*e*e*e*e*e*e*e*e/131072;
        bf = b0 + c1*Math.sin(2*b0) + c2*Math.sin(4*b0) +c3*Math.sin(6*b0) + c4*Math.sin(8*b0); // bf =b0+c1*sin2b0 + c2*sin4b0 + c3*sin6b0 +c4*sin8b0 +...
        double tf = Math.tan(bf);
        double n2 = ee*ee*Math.cos(bf)*Math.cos(bf); //第二偏心率平方成bf余弦平方
        double c = a*a/b;
        double v=Math.sqrt(1+ ee*ee*Math.cos(bf)*Math.cos(bf));
        double mf = c/(v*v*v); //子午圈半径
        double nf = c/v;//卯酉圈半径

        //纬度计算
        lat=bf-(tf/(2*mf)*Y)*(Y/nf) * (1-1/12*(5+3*tf*tf+n2-9*n2*tf*tf)*(Y*Y/(nf*nf))+1/360*(61+90*tf*tf+45*tf*tf*tf*tf)*(Y*Y*Y*Y/(nf*nf*nf*nf)));
        //经度偏差
        lon=1/(nf*Math.cos(bf))*Y -(1/(6*nf*nf*nf*Math.cos(bf)))*(1+2*tf*tf +n2)*Y*Y*Y + (1/(120*nf*nf*nf*nf*nf*Math.cos(bf)))*(5+28*tf*tf+24*tf*tf*tf*tf)*Y*Y*Y*Y*Y;
        result[0] =formatby6(lat/iPI);
        result[1] =formatby6(L0+lon/iPI);
        return result;
    }
}
