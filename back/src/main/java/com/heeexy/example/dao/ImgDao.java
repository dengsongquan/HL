package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface ImgDao {
    int countImg(JSONObject jsonObject);

    List<JSONObject> listImgs(JSONObject jsonObject);
}
