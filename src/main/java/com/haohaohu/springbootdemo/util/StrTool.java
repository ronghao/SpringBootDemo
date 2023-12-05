package com.haohaohu.springbootdemo.util;

import cn.hutool.core.util.StrUtil;

/**
 * @author haohao(ronghao3508 gmail.com) on 2023/8/12 10:53
 * @version v1.0
 */
class StrTool {
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

        String dataFormTemplate = "{\"CombinateBroadcast\":{\"enabled\":true,\"volume\":1,\"ctrlMode\":\"cameraAndplatform\",\"vehicleBroadcastEnabled\":true,\"allowListBroadcastEnabled\":false,\"blockListBroadcastEnabled\":false,\"temporaryListBroadcastEnabled\":false,\"BroadcastInfoList\":[{\"title\":\"come\",\"BroadcastInfo\":{\"id\":1,\"value\":\"plate\",\"customValue\":\"{}\"}}],\"times\":1,\"broadcastMode\":\"internal\",\"volumeTimeEnabled\":true,\"soundMode\":\"child\",\"broadcastPort\":\"audioPort\",\"laneID\":\"1\"}}";

        String format = StrUtil.format(dataFormTemplate, "你好欢迎光临");
        System.out.println(format);

        String data = "{\"SingleSceneLEDConfigurations\": {\"sid\": 1,\"mode\": \"noVehicle\",\"showFreeEnabled\": true,\"displayTime\": 10,\"vehicleDisplayEnabled\": true,\"allowListDisplayEnabled\": false,\"blockListDisplayEnabled\": false,\"temporaryListDisplayEnabled\": false,\"ctrlMode\": \"platform\",\"LEDConfigurationList\": [{\"LEDConfiguration\": {\"id\": 1,\"enabled\": true,\"ShowInfoList\": [{\"ShowInfo\": {\"id\": 1,\"fontSize\": 16,\"fontColor\": \"red\",\"speedType\": \"medium\",\"displayMode\": \"instant\",\"LineInfoList\": [{\"LineInfo\": {\"id\": 1,\"customValue\": \"1234\"}}]}},{\"ShowInfo\": {\"id\": 2,\"fontSize\": 16,\"fontColor\": \"red\",\"speedType\": \"medium\",\"displayMode\": \"instant\",\"LineInfoList\": [{\"LineInfo\": {\"id\": 1,\"customValue\": \"1231\"}}]}}]}}],\"LedInfo\": {\"communicateMode\": \"serial\",\"networkCtrlInfo\": {\"ipaddr\": \"\",\"portNo\": 10000}}}}";
    }
}
