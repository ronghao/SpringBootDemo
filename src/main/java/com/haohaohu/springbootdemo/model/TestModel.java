package com.haohaohu.springbootdemo.model;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.haohaohu.springbootdemo.advanced.Lazy;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author haohao(ronghao3508 gmail.com) on 2023/7/12 9:05
 * @version v1.0
 */
@Data
@Builder
public class TestModel {
    private Integer type;
    private String content;
    private int typeInt;
    private boolean aBoolean;
    //@JSONField(serialize = false)//不序列化
    @JSONField(serializeUsing = Lazy.LazyFormatSerializer.class)
    private Lazy<String> lazyStr = Lazy.of(() -> {
        return "11";
    });

    @JSONField(serializeUsing = Lazy.LazyFormatSerializer.class)
    private Lazy<TestModel1> lazyTestModel1 = Lazy.of(() -> {
        TestModel1 testModel1 = new TestModel1();
        testModel1.setType(1);
        //testModel1.setContent("2222");
        testModel1.setContent(content);
        return testModel1;
    });

    public class Format1 {
        boolean isCurrent() {
            return type != null && type > 0;
        }
    }


    //public static class Format {
    //    public static TestModel create() {
    //        TestModel testModel = new TestModel();
    //        testModel.setType(0);
    //        testModel.setContent("content1");
    //        testModel.setTypeInt(0);
    //        testModel.setABoolean(false);
    //        return testModel;
    //    }
    //}

    public static void main(String[] args) {
        Date date = new Date(Long.MAX_VALUE);
        System.out.println(date);

        String jsonString = JSON.toJSONString(null);
        if (StrUtil.isNotBlank(jsonString)) {
            System.out.println(jsonString);
        } else {
            System.out.println("11");
        }
    }
}
