package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface NewsDao {
    int addNews(JSONObject jsonObject);

    int countNews(JSONObject jsonObject);

    List<JSONObject> listNews(JSONObject jsonObject);

    int updateNews(JSONObject jsonObject);
}
