package com.csx.csxspringboot.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: easyExcelListener
 * @Description: TODO
 * @author: Cuisx
 * @date: 2021/3/28 18:02
 */
public class easyExcelListener extends AnalysisEventListener {

    List<Object> list = new ArrayList<>();

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        list.add(o);
        System.out.println(JSON.toJSON(o));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("解析Excel完成");
    }
}
