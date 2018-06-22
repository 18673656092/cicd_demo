package com.cicd.cicd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhuran on 2018/6/21 0021
 */
@Controller
public class CICD {

    @GetMapping("/index")
    @ResponseBody
    public String cicd() {
        return "i4";
    }
}
