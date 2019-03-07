package com.boot.demo.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 设置微信菜单
 * @author xuguoquan
 * @date 2018/7/31
 */
public class CreateMenuRest {
    /**
     * 第三方用户唯一凭证
     */
    // 测试
//    public static final String appId = "wx286da09517432107";
    /**
     * 第三方用户唯一凭证密钥
     */
    // 测试
//    public static final String appSecret = "d6b0baece5d9fa9410ed4c34f0a48970";

    // ！！！！！！！！！！！！生产
    public static final String appId = "wxdac4223da856eb52";
    public static final String appSecret = "86c9d3e0457c4e6e8274d1062835b22c";
    /**
     * 获取access_token的接口地址（GET） 限200（次/天）
     */
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appId}&secret={appSecret}";
    /**
     * 菜单创建（POST） 限100（次/天）
     */
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

    public static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=";

    // 获取自定义菜单
    @Test
    public void test1(){
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        String access_token = getAccessToken(restTemplate);
        System.out.println("获取到access_token = " + access_token);
        String forObject = restTemplate.getForObject(menu_get_url + access_token, String.class);
        System.out.println("获取到的菜单信息 = " + JSONObject.parseObject(forObject));
    }

    public static String getAccessToken(RestTemplate restTemplate){
        // 调用接口获取access_token
        Map<String, Object> accessTokenParams = new HashMap();
        accessTokenParams.put("appId", appId);
        accessTokenParams.put("appSecret", appSecret);
        String accessTokenStr = restTemplate.getForObject(access_token_url, String.class, accessTokenParams);
        System.out.println("获取access_token,微信服务器相应的数据" + accessTokenStr);
        JSONObject accessTokenObj = JSON.parseObject(accessTokenStr);
        String access_token = accessTokenObj.getString("access_token");
        return access_token;
    }

    /**
     * 创建菜单
     */
    @Test
    public  void test2() {
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        String access_token = getAccessToken(restTemplate);
        System.out.println("access_token = " + access_token);

        // 获取微信服务器IP地址
//        String ip = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token="+access_token, String.class);
//        System.out.println("微信服务器IP地址" + ip);

        // 获取准备好的菜单栏
        JSONObject menu = (JSONObject) getMenu();
        System.out.println("获取设置好的菜单" + menu.toJSONString());

        // 准备发起创建菜单的请求
        String menuUrl = menu_create_url + access_token;
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> entity = new HttpEntity(menu.toString(), headers);
        String createMenuResult = restTemplate.postForObject(menuUrl, entity, String.class);
        JSONObject createMenuResultObj = JSON.parseObject(createMenuResult);
        int result = createMenuResultObj.getInteger("errcode");
        // 判断菜单创建结果
        if (0 == result) {
            System.out.println("菜单创建成功！");
        } else {
            System.out.println("菜单创建失败，错误码：" + result);
        }
        System.out.println(createMenuResult);


    }

    /**
     * 	 封装菜单数据
     */
    public static Object getMenu(){
        // 第一个栏位
        JSONObject bt1 = new JSONObject();
        bt1.put("name", "信用诊断");
        bt1.put("type", "view");
//        bt1.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId
//                + "&redirect_uri=http%3a%2f%2fcreditaccelerator-h5.test.cashbus.com%2fweixin%2foauth2&response_type=code&scope=snsapi_base&state=creditDiagnosis#wechat_redirect");
//        // 生产
        bt1.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId
                + "&redirect_uri=https%3a%2f%2fweixin.jiasuqi88.com%2fweixin%2foauth2&response_type=code&scope=snsapi_base&state=creditDiagnosis#wechat_redirect");

        // 第二个栏位
//        JSONObject bt2 = new JSONObject();
//        bt2.put("name", "贷款超市");
//        bt2.put("type", "view");
////        bt2.put("url", "http://ddhua-web.test.cashbus.com/#/");
//        // 生产
//        bt2.put("url", "https://h5.51ddhua.com/#/?series=wxhxjsq");
//        JSONObject bt2_1 = new JSONObject();
//        bt2_1.put("name", "本周热门");
//        bt2_1.put("type", "view");
//        // 生产
//        bt2_1.put("url", "https://h5.51ddhua.com/#/events/download/toufang1");
        JSONObject bt2_2 = new JSONObject();
        bt2_2.put("name", "免费借款");
        bt2_2.put("type", "view");
        // 生产
        bt2_2.put("url", "https://h5.51ddhua.com/#/?series=wxhxjsq");

        JSONObject bt2_3 = new JSONObject();
        bt2_3.put("name", "好信社区");
        bt2_3.put("type", "view");
        // 生产
        bt2_3.put("url", "https://h5.51ddhua.com/#/community");

        JSONArray bt2Sub_button = new JSONArray();
//        bt2Sub_button.add(bt2_1);
        bt2Sub_button.add(bt2_2);
        bt2Sub_button.add(bt2_3);
        JSONObject bt2 = new JSONObject();
        bt2.put("name", "极速下款");
        bt2.put("sub_button", bt2Sub_button);



        // 第三个栏位(复杂栏位)
        // 3_1
        JSONObject bt3_1 = new JSONObject();
        bt3_1.put("name", "我的报告");
        bt3_1.put("type", "view");
//        bt3_1.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId
//                + "&redirect_uri=http%3a%2f%2fcreditaccelerator-h5.test.cashbus.com%2fweixin%2foauth2&response_type=code&scope=snsapi_base&state=creditHistory#wechat_redirect");
        bt3_1.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId
                + "&redirect_uri=https%3a%2f%2fweixin.jiasuqi88.com%2fweixin%2foauth2&response_type=code&scope=snsapi_base&state=creditHistory#wechat_redirect");

        // 3_2
        JSONObject bt3_2 = new JSONObject();
        bt3_2.put("name", "联系客服");
        bt3_2.put("type", "click");
        bt3_2.put("key", "customerService");
        // 3_3
        JSONObject bt3_3 = new JSONObject();
        bt3_3.put("name", "申请退款");
        bt3_3.put("type", "view");
//        bt3_3.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId
//                + "&redirect_uri=http%3a%2f%2fcreditaccelerator-h5.test.cashbus.com%2fweixin%2foauth2&response_type=code&scope=snsapi_base&state=creditRefund#wechat_redirect");
        bt3_3.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId
                + "&redirect_uri=https%3a%2f%2fweixin.jiasuqi88.com%2fweixin%2foauth2&response_type=code&scope=snsapi_base&state=creditRefund#wechat_redirect");
        // 3_4
        JSONObject bt3_4 = new JSONObject();
        bt3_4.put("name", "下载APP");
        bt3_4.put("type", "view");
        bt3_4.put("url", "https://weixin.jiasuqi88.com/static/appdownload/");
        // 3_5
        JSONObject bt3_5 = new JSONObject();
        bt3_5.put("name", "白条还款");
        bt3_5.put("type", "view");
//        bt3_5.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId
//                + "&redirect_uri=http%3a%2f%2fcreditaccelerator-h5.test.cashbus.com%2fweixin%2foauth2&response_type=code&scope=snsapi_base&state=creditPostPay#wechat_redirect");
        bt3_5.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId
                + "&redirect_uri=https%3a%2f%2fweixin.jiasuqi88.com%2fweixin%2foauth2&response_type=code&scope=snsapi_base&state=creditPostPay#wechat_redirect");

        // 3总
        JSONArray bt3Sub_button = new JSONArray();
        bt3Sub_button.add(bt3_4);
        bt3Sub_button.add(bt3_1);
        bt3Sub_button.add(bt3_5);
        bt3Sub_button.add(bt3_3);
        bt3Sub_button.add(bt3_2);
        JSONObject bt3 = new JSONObject();
        bt3.put("name", "我");
        bt3.put("sub_button", bt3Sub_button);

        // 所有栏位总
        JSONObject total = new JSONObject();
        JSONArray buttonTotal = new JSONArray();
        buttonTotal.add(bt2);
        buttonTotal.add(bt1);
        buttonTotal.add(bt3);
        total.put("button", buttonTotal);
        return total;
    }


}
