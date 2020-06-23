package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.NewsDao;
import com.heeexy.example.service.NewsService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public JSONObject listNews(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = newsDao.countNews(jsonObject);
        List<JSONObject> list = newsDao.listNews(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addNews(JSONObject jsonObject) {
        newsDao.addNews(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateNews(JSONObject jsonObject) {
        newsDao.updateNews(jsonObject);
        return CommonUtil.successJson();
    }
}
