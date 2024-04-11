package com.haohaohu.springbootdemo.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.emoji.EmojiUtil;
import com.vdurmont.emoji.EmojiParser;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author haohao(ronghao3508 gmail.com) on 2023/8/12 10:53
 * @version v1.0
 */
class StrTool {

    public static boolean containsGarbledText(String str) {
        CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes(StandardCharsets.UTF_8));

        try {
            decoder.decode(buffer);
        } catch (CharacterCodingException e) {
            return true; // 如果解码过程中抛出异常，则说明包含乱码
        }

        return false;
    }

    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public static boolean hasSpecialChars(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //String passIds = "1645364414117302273,1645373311792173057,1648157887857930241,1645374099958153218,1645373607826149377";
        //String ids = "1645374809259364353,1645374717703634945";
        //
        //for (String passId : StrUtil.split(ids, ",")) {
        //    if (passIds.contains(passId)) {
        //        //强制下班
        //        System.out.println("强制下班");
        //    }
        //}
        //System.out.println("11");

        //String data = "1";
        //int length = StrUtil.length(data);
        //System.out.println(data + " length:" + length);
        //
        //data = "1234";
        //length = StrUtil.length(data);
        //System.out.println(data + " length:" + length);
        //
        //data = "123456";
        //length = StrUtil.length(data);
        //System.out.println(data + " length:" + length);
        //
        //data = "中";
        //length = StrUtil.length(data);
        //System.out.println(data + " length:" + length);
        //
        //data = "中国中国";
        //length = StrUtil.length(data);
        //System.out.println(data + " length:" + length);
        //
        //String dataFormTemplate = "{\"CombinateBroadcast\":{\"enabled\":true,\"volume\":1,\"ctrlMode\":\"cameraAndplatform\",\"vehicleBroadcastEnabled\":true,\"allowListBroadcastEnabled\":false,\"blockListBroadcastEnabled\":false,\"temporaryListBroadcastEnabled\":false,\"BroadcastInfoList\":[{\"title\":\"come\",\"BroadcastInfo\":{\"id\":1,\"value\":\"plate\",\"customValue\":\"{}\"}}],\"times\":1,\"broadcastMode\":\"internal\",\"volumeTimeEnabled\":true,\"soundMode\":\"child\",\"broadcastPort\":\"audioPort\",\"laneID\":\"1\"}}";
        //
        //String format = StrUtil.format(dataFormTemplate, "你好欢迎光临");
        //System.out.println(format);
        //
        //String data = "{\"SingleSceneLEDConfigurations\": {\"sid\": 1,\"mode\": \"noVehicle\",\"showFreeEnabled\": true,\"displayTime\": 10,\"vehicleDisplayEnabled\": true,\"allowListDisplayEnabled\": false,\"blockListDisplayEnabled\": false,\"temporaryListDisplayEnabled\": false,\"ctrlMode\": \"platform\",\"LEDConfigurationList\": [{\"LEDConfiguration\": {\"id\": 1,\"enabled\": true,\"ShowInfoList\": [{\"ShowInfo\": {\"id\": 1,\"fontSize\": 16,\"fontColor\": \"red\",\"speedType\": \"medium\",\"displayMode\": \"instant\",\"LineInfoList\": [{\"LineInfo\": {\"id\": 1,\"customValue\": \"1234\"}}]}},{\"ShowInfo\": {\"id\": 2,\"fontSize\": 16,\"fontColor\": \"red\",\"speedType\": \"medium\",\"displayMode\": \"instant\",\"LineInfoList\": [{\"LineInfo\": {\"id\": 1,\"customValue\": \"1231\"}}]}}]}}],\"LedInfo\": {\"communicateMode\": \"serial\",\"networkCtrlInfo\": {\"ipaddr\": \"\",\"portNo\": 10000}}}}";

        //String data = "猆d'有效天数05庄";
        //System.err.println(containsGarbledText(data));

        //String inputString = "粤SABDXD"; // 你要检查的字符串
        //
        //if (isSpecialChar(inputString)) {
        //    System.out.println("字符串包含乱码");
        //} else {
        //    System.out.println("字符串不包含乱码");
        //}

        //String data = "/P4BMAMBAYsEAQIDChEBAzg2OTY2NDA0MjI1MjYyMAYBBGV8xkYKAQUAAAAAAAAj34F+AU8GinxlAAAAgON9RYUCAv9/4X1OhQQE/n/gfU+FBwb+f+J9UIUJCP9/4X1LhQsKAIDkfVCFDQwBgOF9S4UPDgGA4H1QhREQAIDgfUaFFBIBgOF9UIUWFAGA4X1JhRgWAYDjfVCFGhgCgOJ9SYUcGgOA5H1QhR4cAIDgfUyFIB4AgOR9S4UiIACA4H1PhSUiAIDhfUuFJyQCgOJ9S4UpJgCA531NhSsoAIDjfU+FLSoBgOV9T4UvLACA4H1PhTIuA4DjfU6FNDABgN59ToU2MgCA4X1OhTg0AoDefU+FOjb+f+N9TIU8OACA4H1OhT46AIDefUyFQDwAgOF9UoWH";
        //byte[] decode = Base64.decode(data.getBytes(StandardCharsets.UTF_8));
        //data = HexUtil.encodeHexStr(decode).toUpperCase(Locale.ROOT);
        //System.out.println(data);
        //
        //String lengthHex = data.substring(4, 8);
        //int i = HexUtil.hexToInt(lengthHex);
        //System.out.println(i);

        //String data = "猆d'有效天数05庄";
        //data = StrUtil.utf8Str(StrUtil.replace(data, "\u0000", ""));
        //System.out.println(data);
        //LocalDateTime yyyyMMdd = LocalDateTimeUtil.parse("20240105", "yyyyMMdd");
        //System.out.println(yyyyMMdd);

        String data = "\uE40E\n" +
                "                                                                                                                                                                          璀";
        //data = "123223423afasdf";
        data = "猆京A123456\uE40E";
        System.out.println(hasSpecialChars(data));
        String s = EmojiUtil.removeAllEmojis(data);
        System.out.println(s);
    }
}
