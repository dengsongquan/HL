package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.ImgDao;
import com.heeexy.example.service.ImgService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgServiceImpl implements ImgService {
    @Autowired
    private ImgDao imgDao;

    @Override
    public JSONObject listImg(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = imgDao.countImg(jsonObject);
        List<JSONObject> list = imgDao.listImgs(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }
}
