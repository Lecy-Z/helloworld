package com.example.hw2.recycler;

import java.util.ArrayList;
import java.util.List;

public class TestDataSet {

    public static List<TestData> getData() {
        List<TestData> result = new ArrayList();
        result.add(new TestData("游戏小助手", "刚刚"));
        result.add(new TestData("抖音小助手", "昨天"));
        result.add(new TestData("系统消息", "12：00"));
        result.add(new TestData("我是豆豆", "11：00"));
        result.add(new TestData("陌生人", "11：00"));
        result.add(new TestData("喂喂喂", "10：00"));
        result.add(new TestData("李", "9：00"));
        result.add(new TestData("tesyyu", "8：00"));
        return result;
    }

}
