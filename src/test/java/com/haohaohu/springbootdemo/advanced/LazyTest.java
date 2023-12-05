package com.haohaohu.springbootdemo.advanced;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.haohaohu.springbootdemo.model.TestModel;
import com.haohaohu.springbootdemo.util.OptionalBean;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author haohao(ronghao3508 gmail.com) on 2023/7/12 9:04
 * @version v1.0
 */
public class LazyTest extends TestCase {

    public static void main(String[] args) {
        //TestModel testModel = TestModel.Format.create();


        //TestModel testModel = TestModel.builder().build();
        //String s = testModel.getLazyStr().get();
        //System.out.println(JSON.toJSONString(testModel));

        //TestModel testModel = null;
        //String s = Opt.ofNullable(testModel).map(TestModel::getContent).orElse("1");
        //System.out.println(s);
        //String stringLazy = OptionalBean.ofNullable(testModel).getBean(TestModel::getContent).orElse("1");
        //System.out.println(stringLazy);

        //long time = DateUtil.beginOfYear(new Date()).getTime();
        //System.out.println(time);

        //String stacktraceStr = "1";
        //String[] errorIgnoreList = new String[]{};
        ////errorIgnoreList[0] = "";
        ////errorIgnoreList[1] = "1";
        //
        //if (StrUtil.containsAny(stacktraceStr, errorIgnoreList)) {
        //    System.out.println("1");
        //    return;
        //}
        //System.out.println("0");

        //TestModel testModel = TestModel.builder().build();
        //String data = JSON.toJSONString(testModel);
        //System.out.println(data);

        //List<String> list = StrUtil.split(null, ",");
        //System.out.println(CollUtil.contains(list, "1"));
        String jsonString = JSON.toJSONString(null);
        System.out.println(jsonString);
    }
}
