package com.haohaohu.springbootdemo.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.haohaohu.springbootdemo.advanced.Lazy;
import lombok.Data;

/**
 * @author haohao(ronghao3508 gmail.com) on 2023/7/12 9:05
 * @version v1.0
 */
@Data
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


    public static class Format {
        public static TestModel create() {
            TestModel testModel = new TestModel();
            testModel.setType(0);
            testModel.setContent("content");
            testModel.setTypeInt(0);
            testModel.setABoolean(false);
            testModel.getLazyStr().get();
            return testModel;
        }
    }
}
