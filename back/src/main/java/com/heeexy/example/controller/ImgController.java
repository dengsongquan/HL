package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.ImgService;
import com.heeexy.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/images")
public class ImgController {
    @Autowired
    private ImgService imgService;

    @RequiresPermissions("images:list")
    @GetMapping("/listImages")
    public JSONObject listImages(HttpServletRequest request){
        return imgService.listImg(CommonUtil.request2Json(request));
    }
}
