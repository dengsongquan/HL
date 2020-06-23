package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

public interface NewsService {
    /*
     * 新闻列表
     * */
    JSONObject listNews(JSONObject jsonObject);


    //添加新闻
    JSONObject addNews(JSONObject jsonObject);

    /*
     * 修改新闻
     * */
    JSONObject updateNews(JSONObject jsonObject);

}


