package com.demo.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @RequestMapping(value = "/testTool", method = RequestMethod.GET)
    public String testTool(ModelMap modelMap, HttpServletRequest request){
        modelMap.put("request", request);
        return "testTool";
    }
}
