package com.haohaohu.springbootdemo;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author haohao(ronghao3508 gmail.com) on 2024/3/5 15:14
 * @version v1.0
 */
public class dta {

    public static JSONObject avsTrigger() {
        JSONObject result = new JSONObject();
        result.put("type", "avs_trigger");
        result.put("module", "ALG_REQUEST_MESSAGE");
        JSONObject body = new JSONObject();
        body.put("trigger_result", 1);
        body.put("trigger_type", 8);
        result.put("body", body);
        return result;
    }


    public static JSONObject sendData485(int source, int type) {
        String data = "";
        if (type == 0) {
            data = "A601018181";
        } else if (type == 1) {
            data = "A601018080";
        }
        if (StrUtil.isBlank(data)) {
            return null;
        }
        return sendData485(source, getSerialData1(data));
    }

    /**
     * @param source
     * @param data
     * @return
     */
    public static JSONObject sendData485(int source, String data) {
        JSONObject result = new JSONObject();
        result.put("type", "evs_send_rs485");
        result.put("module", "EVS_BUS_REQUEST");
        JSONObject body = new JSONObject();
        body.put("source", source);
        body.put("rs485_data", data);
        body.put("data_len", data.length());
        result.put("body", body);
        return result;
    }

    public static String getSerialData1(String cameraStr) {
        int len = cameraStr.length();
        if (len % 2 == 1) {
            cameraStr += " ";
            len += 1;
        }
        byte[] bs = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bs[i / 2] = (byte) ((Character.digit(cameraStr.charAt(i), 16) << 4) + Character.digit(cameraStr.charAt(i + 1), 16));
        }
        String data = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(bs);
        return data;
    }

    public static JSONObject build(JSONObject... body) {
        JSONObject result = new JSONObject();
        result.put("state", 200);
        result.put("err_msg", "all done");
        JSONArray jsonArray = new JSONArray();
        for (JSONObject jsonObject : body) {
            jsonArray.add(jsonObject);
        }
        result.put("body", jsonArray);
        return result;
    }

    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) {
                sbu.append((int) chars[i]);
            } else {
                sbu.append((int) chars[i]);
            }
        }
        return sbu.toString();
    }

    public static String asciiToString(String value) {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }

    public static String getSerialData(String cameraStr) {
        int len = cameraStr.length();
        if (len % 2 == 1) {
            cameraStr += " ";
            len += 1;
        }
        byte[] bs = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bs[i / 2] = (byte) ((Character.digit(cameraStr.charAt(i), 16) << 4) + Character.digit(cameraStr.charAt(i + 1), 16));
        }
        return new String(bs);
    }

    public static String convertHexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        JSONObject jsonObject = sendData485(0, 1);
        System.out.println(jsonObject.toJSONString());

        JSONObject jsonObject1 = avsTrigger();
        JSONObject build = build(jsonObject);
        System.out.println(build.toJSONString());


        //ArrayList arrayList = new ArrayList();
        //arrayList.add("{\"err_msg\":\"all done\",\"state\":200,\"body\":[{\"module\":\"EVS_BUS_REQUEST\",\"type\":\"evs_send_rs485\",\"body\":{\"data_len\":12,\"source\":0,\"rs485_data\":\"pgEBgIA=\"}}]}");
        //messageMap.put("sn", arrayList);

        byte[] a601018181s = HexUtil.decodeHex("A601018181");
        System.out.println(StrUtil.str(a601018181s, StandardCharsets.UTF_8));


        String s = HexUtil.encodeHexStr("A601018181");
        System.out.println(s);
        System.out.println("-------------------");


        String s1 = convertHexToString("A601018181");
        System.out.println(s1);
        String s2 = HexUtil.encodeHexStr(s1);
        System.out.println(s2);

        System.out.println("-------------------");
        s1 = getSerialData1("A601018181");
        System.out.println(s1);

        s2 = HexUtil.encodeHexStr(org.apache.tomcat.util.codec.binary.Base64.decodeBase64(s1));
        System.out.println(s2);

    }
}
