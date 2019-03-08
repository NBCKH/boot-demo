package com.boot.demo.utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpUtil {
    private static String OperID = CommonUtil.getProperty("guodu_OperID");
    private static String OperPass = CommonUtil.getProperty("guodu_OperPass");
    private static String URL = CommonUtil.getProperty("guodu_smsUrl");

    /**
     * 用户自扩展的号码。若扩展请填写号码，若不扩展请填写"", 注意！通道号+国都用户身份号+AppendID总长不能超过20位。
     * 否则将发送失败。具体号码定义，请参见国都资信通平台接口文档.
     */
    private static String AppendID = "";
    /**
     * 如果为定时消息。请填写，格式为yyyyMMddhhmmss 若为实时消息，请填"";
     */
    private static String SendTime = "";
    /**
     * 消息有效期  应该大于SendTime，最好不要填写，国都默认消息有效期为SendTime+3。如果填写错误容易导致消息过有效期无法发送。
     */
     private static String ValidTime = "";
    /**
     * 内容类型:15为短短信,8为长短信;国都服务端将会自动识别短信长短，所以发送时填写8即可。若填写15 长短信将无法发送。
     */
    private static String Content_type = "8";

    /**
     * post方式 发送消息
     *
     * @param Content     发送内容文字    长度最好不要超过500个字符
     * @param mobile     需要发送的手机号字符串
     * @return rec_string   国都返回的XML格式的串
     * @catch Exception
     */
    public static String postSendMsg(String Content, String mobile)
    {
        // 将内容用URLEncoder编一次GBK
        String EncoderContent = "";
        try {
            EncoderContent = URLEncoder.encode(Content, "GBK");
            System.out.println(EncoderContent);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 消息参数
        String str = "OperID=" + OperID + "&OperPass=" + OperPass + "&SendTime=" + SendTime + "&ValidTime=" + ValidTime + "&AppendID=" + AppendID +
                "&DesMobile=" + mobile.trim() + "&Content=" + EncoderContent;

        System.out.println("发送的内容为："+str);
        // 使用post方式发送消息
        String response = postURL(str, URL);

        // 返回响应
        return response;
    }

    /**
     * get方式 发送消息，与post格式完全相同，仅调用发送方法不同this.getURL(str, URL);
     *
     * @param Content     发送内容文字   长度最好不要超过500个字符。
     * @param mobile     需要发送的手机号字符串
     * @return rec_string   国都返回的XML格式的串
     * @catch Exception
     */
    public static String getSendMsg(String Content,String mobile)
    {
        /*将内容用URLEncoder编一次GBK*/
        String EncoderContent = "";
        try {
            EncoderContent = URLEncoder.encode(Content, "GBK");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /*消息参数*/
        String str = "OperID=" + OperID + "&OperPass=" + OperPass + "&SendTime=" + SendTime + "&ValidTime=" + ValidTime + "&AppendID=" + AppendID +
                "&DesMobile=" + mobile.trim() + "&Content=" + EncoderContent + "&ContentType=" + Content_type;

        System.out.println("发送的内容为：" + str);
        /*使用get方式发送消息*/
        String response = getURL(str, URL);

        /*返回响应*/
        return response;
    }


    /**
     * post方式 发送url串
     *
     * @param commString   需要发送的url参数串
     * @param address      需要发送的url地址
     * @return rec_string  国都返回的XML格式的串
     * @catch Exception
     */
    private static String postURL(String commString, String address) {
        String rec_string = "";
        URL url = null;
        HttpURLConnection urlConn = null;
        try {
            /*得到url地址的URL类*/
            url = new URL(address);
            /*获得打开需要发送的url连接*/
            urlConn = (HttpURLConnection) url.openConnection();

             /*设置连接超时时间*/
            urlConn.setConnectTimeout(30000);
            /*设置读取响应超时时间*/
            urlConn.setReadTimeout(30000);
            /*设置post发送方式*/
            urlConn.setRequestMethod("POST");
            /*发送commString*/
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);
            OutputStream out = urlConn.getOutputStream();
            out.write(commString.getBytes());
            out.flush();
            out.close();
            /*发送完毕 获取返回流，解析流数据*/
            BufferedReader rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "GBK"));
            StringBuffer sb = new StringBuffer();
            int ch;
            while ((ch = rd.read()) > -1) {
                sb.append((char) ch);
            }
            rec_string = sb.toString().trim();
            /*解析完毕关闭输入流*/
            rd.close();
        } catch (Exception e) {
            /*异常处理*/
            rec_string = "-107";
            System.out.println(e);
        } finally {
            if (urlConn != null) {
                /*关闭URL连接*/
                urlConn.disconnect();
            }
        }

        /*返回响应内容*/
        return rec_string;
    }


    /**
     * get方式 发送url串,与post方式代码基本相同，仅发送方式不同
     *
     * @param commString  需要发送的url参数串
     * @param address     需要发送的url地址
     * @return rec_string 国都返回的XML格式的串
     * @catch Exception
     */
    private static String getURL(String commString, String address) {
        String rec_string = "";
        URL url = null;
        HttpURLConnection urlConn = null;
        try {
            /*得到url地址的URL类*/
            url = new URL(address+"?"+commString);
            /*获得打开需要发送的url连接*/
            urlConn = (HttpURLConnection) url.openConnection();
            /*设置连接超时时间*/
            urlConn.setConnectTimeout(30000);
            /*设置读取响应超时时间*/
            urlConn.setReadTimeout(30000);
            /*设置post发送方式*/
            urlConn.setRequestMethod("GET");
            /*发送commString*/
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.connect();

            /*发送完毕 获取返回流，解析流数据*/
            BufferedReader rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "GBK"));
            StringBuffer sb = new StringBuffer();
            int ch;
            while ((ch = rd.read()) > -1) {
                sb.append((char) ch);
            }
            rec_string = sb.toString().trim();
            /*解析完毕关闭输入流*/
            rd.close();
        } catch (Exception e) {
            /*异常处理*/
            rec_string = "-107";
            System.out.println(e);
        } finally {
            if (urlConn != null) {
                /*关闭URL连接*/
                urlConn.disconnect();
            }
        }

        /*返回响应内容*/
        return rec_string;
    }
}
