package com.haohaohu.springbootdemo.advanced;

import com.alibaba.fastjson.JSON;
import com.haohaohu.springbootdemo.model.TestModel;
import junit.framework.TestCase;

/**
 * @author haohao(ronghao3508 gmail.com) on 2023/7/12 9:04
 * @version v1.0
 */
public class LazyTest extends TestCase {

    public static void main(String[] args) {
        TestModel testModel = TestModel.Format.create();
        System.out.println(JSON.toJSONString(testModel));
    }
}
