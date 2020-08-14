package com.ryl.res.service.impl;

import com.ryl.res.model.vo.ExportModel;
import com.ryl.res.service.IPracticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ryl
 * @description:
 * @date: 2020-08-14 13:38:31
 */
@Service
@Slf4j
public class PracticeService implements IPracticeService {

    @Override
    public List<ExportModel> getModelData() {
        List<ExportModel> list = new ArrayList<>();
        list.add(new ExportModel("zhangsan","18814162564","手表",1,"只","上海东方明珠",200.00f));
        list.add(new ExportModel("lisi","17858954979","牙膏",1,"支","北京天安门广场",33.00f));
        list.add(new ExportModel("lisi","17858954979","牙刷",1,"根","北京天安门广场",15.00f));
        return list;
    }
}
