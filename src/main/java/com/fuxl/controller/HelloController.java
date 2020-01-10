package com.fuxl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "helloController")
@RestController
public class HelloController {

    @ApiOperation(value = "return hello")
//    @RequestMapping()
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * ModelMap调到页面
     *
     * @param modelMap
     * @return
     */
    @ApiImplicitParam(paramType = "path", dataType = "ModelMap", name = "modelMap", value = "return helloPage", required = true)
    @ApiOperation(value = "return page", notes = "返回页面")
//    @RequestMapping("/helloModelMap")
    @GetMapping("/helloModelMap")
    public String helloModelMap(ModelMap modelMap) {
        return "hello";
    }
}
