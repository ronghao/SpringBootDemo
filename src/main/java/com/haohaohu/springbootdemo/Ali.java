package com.haohaohu.springbootdemo;

import cn.hutool.core.util.URLUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayOpenMiniQrcodeBindModel;
import com.alipay.api.request.AlipayOpenMiniQrcodeBindRequest;
import com.alipay.api.response.AlipayOpenMiniQrcodeBindResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author haohao(ronghao3508 gmail.com) on 2024/2/22 18:24
 * @version v1.0
 */
public class Ali {


    public static void send(String id1) throws AlipayApiException {
        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDcXi5S+1YYJcjWnpuN3Pumfwr7tuEjIgJ/klFxAahTpm9pfvg7NyAcR0boI95Mbq1TukNyJg0rgsXSVnMMVzsu1+bxHzcupnREM3HelHZNta2vOF1gnrvbuXoQw8Wi0LYAF1QcZYW6H6Tp1g+Hwr//5rNQnSskokdEjPTdOSTnu8UXwJ5+t/ciC7bW2qzgKJRl96lUDMzcqksKHYZCs2TSgmd8XgX7IHwKJ8IvL9Iuj3t6x6wGVjWCrFyHLSUH3n+enZY0YSXTv6HetTNf59gtUfYaL99BjaPFSPh87QBBp1uaaUzcudU+1Z8mYCztn0++jUWx0s1R5Lo2K2SRrmdDAgMBAAECggEAXehkpUgl8DRp2cvNTi8VrmYFMC9G0cn0dpjLy/SA3uTWJYbOEa30KRjWxqZCXR/tDPkbNNGyWxBnbLMaY+/ocJXg/G/o/CBC+61QoSFzKLp7QWgrorkCJShtNCe5WSDH7lICrfc1fZNum5d1Fs2PNGYDlB7l+76L0FKhJoTrjTRU3IpY38yOg1tdfzh2S7ARXttx0AM98dq/WkeQQ4mGT5P6E52ZdMxnZ4S54+ZJYovoWLOo85Asy+LueoRpNvqmPzmLZcBA7P0EEiPUkE3whPIc0GHMOx/ghyAYX+SQjiSumdZnJRvjTwCH9oKxwE4nYthSZ+drBnOCSr6sQliFgQKBgQDxBEP7AGrQUeB5h7BzjRxLDZSw+wODuKibT82IRXxO1YHBdmoyU4UN0e7qb1WJ1faE9cQG6YeDslKd3BnaUWRoj5XPZ/sNhoBhp+ikHBZTSOm0Ezig27YDptvoKpmzKUNTYFxh2K0rjbj+L2NNomGxz1KMfqXKrr8DZ7AoTGf6iwKBgQDqEUtPb5XWqacWo87gME3MZftrhQ/nfWQo00FNc7POQpK1pYZL5XH1szv7rRQE8mX4mpw7hbv95QhTQthMlp2bn+9oD4lz6wB4LHXSCC1Sj3cgyQNyo1Rm7fLAY/P3WJTBCRewUYhxRgND/UUZ7noxBzYg9jh/GaoSC/9jRgm1KQKBgQC5i1QeZsrUR0QtqzU/L9IpQXJH8WZaQI2ky4HqN7wkIKA85MGKrtuOsSBh4IYUj+CxqVZZD+1giKarmqdYeDJ5gJiPTHhsiDBLfe1OccUKJuar1+RPifWMyCchi7I5yDlpdZSJSzq9feR94tNrD+hv77CYMmPGLlMk9u0ZiQC/UwKBgGPgv08FIV35BJhvi640Tm5Cshb+L3wrEFsvUfU3wujnlWy3Vef63zblpVnOT/WQbZy4dKQde8tqOLQ2mM+NkXE/vMudnZx+EbQ13Iyj+pr5Ju8xdywqr7BAJs6bmBXakZcRRy9wixUyt+ACKcNX9IIJPrqv9gmeLOkIf5Cf6TKJAoGBAKaOSe2mu6AYUsrEO+GfSM4NZrEdpmgr8fuCUrxB5Op9NxO7ye8rcqYKNokUU+fDYLqmjHPmIC6RIdVffkDJxHHrQBP8lLF/SXZ6Z1Hd2JtMAu8ndBXlup1ELap57Fxfv9PizlJYX6/9cjJgQtkLtGlRgNxLi9tr5eZKEayzGf4h";
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoBjsVTqEE/+fyiirUPs8rVUv82XfLC76yZwP1oUVhsKA2UeJ2jU81K9h3ZMx4bW3RUi009r+TFBS/vkfsqp04MTjb4tGeJmCzB8wwLMRi+WAV/2Z6rLEouO8/gtznKg2xQ9Vtp2FLTh2HT0BDdurG/2TUWXwmSVJ3mMJqoQwOVxH2Ums3KUBgzXGNh9sIL190YCGCopGvbqOLAsSIoJPey4v3Px/yYU9hLjYqAJ65cX3ofcmvDr8vnFsnAHfHi5+fDKctHzhcxOstdzqZzGASl5OuaEpP6nGB/wUKUqFrkPiMesPxjG6lJF6eUmjAx6qWhYnQ7zVRqTMy63di9wX9QIDAQAB";
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi.alipay.com/gateway.do");
        alipayConfig.setAppId("2021004134662060");
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF-8");
        alipayConfig.setSignType("RSA2");
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        AlipayOpenMiniQrcodeBindRequest request = new AlipayOpenMiniQrcodeBindRequest();
        AlipayOpenMiniQrcodeBindModel model = new AlipayOpenMiniQrcodeBindModel();
        model.setRouteUrl(id1);
        model.setMode("EXACT");
        model.setPageRedirection("pages/index/index");
        request.setBizModel(model);
        AlipayOpenMiniQrcodeBindResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
            // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
            // String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
            // System.out.println(diagnosisUrl);
        }
    }

    public static void main(String[] args) throws AlipayApiException, UnsupportedEncodingException {
        //for (int i = 100; i < 200; i++) {
        //    try {
        //        send(i + "");
        //    } catch (Exception e) {
        //        e.printStackTrace();
        //    }
        //}
        //String str = "https://park.zhenbokeji.com/yunapi/qr/16974370194";
        //send(str);
        //str = "https://park.zhenbokeji.com/yunapi/qr/16974368648";
        //send(str);
        //str = "https://yun.weboo.club/yunapi/qr/16974367706";
        //send(str);
        //str = "https://yun.weboo.club/yunapi/qr/16974367002";
        //send(str);
        //str = "https://yun.weboo.club/yunapi/qr/16974366594";
        //send(str);
        //str = "https://yun.weboo.club/yunapi/qr/16974189955";
        //send(str);
        //str = "https://yun.weboo.club/yunapi/qr/16785223945";
        //send(str);
        //str = "https://yun.weboo.club/yunapi/qr/16785223933";
        //send(str);
        //str = "https://yun.weboo.club/yunapi/qr/16785223922";
        //send(str);

        //str = "https://park.zhenbokeji.com/yunapi/qr/16906134980";
        //send(str);
        //str = "https://park.zhenbokeji.com/yunapi/qr/16906134980";
        //send(str);
        //str = "https://park.zhenbokeji.com/yunapi/qr/16906134980";
        //send(str);
        //str = "https://park.zhenbokeji.com/yunapi/qr/16906134980";
        //send(str);


        //String s = URLUtil.encodeQuery("alipays://platformapi/startapp?appId=2021004134662060&page=/pagesP/pages/index/index?qr_code=1111&type=online");
        //System.out.println(s);

        //String encode = "pagesP/pages/index/index?qr_code=16730841636&type=online";
        //String url11 = URLUtil.encodeQuery("alipays://platformapi/startapp?appId=2021004134662060&page=" + encode);
        //String url111 = ("https://ulink.alipay.com/?scheme=" + URLEncoder.encode(url11, "UTF-8"));
        //System.out.println(url111);

        String code = "16639145942";
        //logger.info("跳转支付宝小程序支付页，code:{}", code);
        //String encode = "pagesP/pages/index/index?qr_code=" + code + "&type=online";
        //String s = URLUtil.encodeQuery("alipays://platformapi/startapp?appId=2021004134662060&page=" + encode);
        //System.out.println("https://ds.alipay.com/?scheme=" + URLEncoder.encode(s, "UTF-8"));


        String encode = "";
        //if (CompanyFormat.isDuoWei(CompanyFormat.getCompany())) {
        //
        //} else {
        //    encode = "qrCode=https://" + configController.getHostUrl() + "/yunapi/qr/" + code;
        //}
        encode = "qrCode=https://park.dwyunparking.com/yunapi/qr/" + code;
        String encode1 = URLUtil.encodeQuery(encode);
        String s = URLUtil.encodeQuery("alipays://platformapi/startapp?appId=2021004134662060&page=pages/index/index&query=" + encode1);
        System.out.println("https://ds.alipay.com/?scheme=" + URLEncoder.encode(s, "UTF-8"));
        //response.sendRedirect("https://ds.alipay.com/?scheme=" + URLEncoder.encode(s, "UTF-8"));


        String encode111 = "qrCode=https://park.dwyunparking.com/yunapi/qr/" + code;
        String encode1111 = URLEncoder.encode(encode111);
        String s1111 = URLEncoder.encode("alipays://platformapi/startapp?appId=2021004134662060&page=pages/index/index&query=" + encode1111);
        String url = "https://ds.alipay.com/?scheme=" + s1111;
        System.out.println(url);


        String data = URLUtil.decode("alipays%253A%252F%252Fplatformapi%252Fstartapp%253FappId%3D2021004134662060%26page%3Dpages%252Findex%252Findex%26query%3DqrCode%3Dhttps%25253A%25252F%25252Fpark.dwyunparking.com%25252Fyunapi%25252Fqr%25252F16639145942", "UTF-8");
        System.out.println(data);
        String decode = URLUtil.decode(data);
        System.out.println(decode);

        String data1 = "qrCode=https%3A%2F%2Fpark.dwyunparking.com%2Fyunapi%2Fqr%2F16639145942";
        String decode1 = URLDecoder.decode(data1, "UTF-8");
        System.out.println(decode1);


    }
}
