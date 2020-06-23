package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.NewsService;
import com.heeexy.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @RequiresPermissions("news:list")
    @GetMapping("/listNews")
    public JSONObject listNews(HttpServletRequest request) {
        return newsService.listNews(CommonUtil.request2Json(request));
    }

    @RequiresPermissions("news:add")
    @PostMapping("/addNews")
    public JSONObject addNews(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "content");
        CommonUtil.hasAllRequired(requestJson, "file");
        return newsService.addNews(requestJson);
    }

    @RequiresPermissions("news:update")
    @PostMapping("/updateNews")
    public JSONObject updateNews(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "id,content");
        CommonUtil.hasAllRequired(requestJson, "id,file");
        return newsService.updateNews(requestJson);
    }

}
