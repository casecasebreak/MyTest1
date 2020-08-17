package com.qjl.iot.modules.sys.controller;

import com.qjl.iot.common.utils.PageUtils;
import com.qjl.iot.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("sys/test")
public class SysTestController {

    /**
     * 列表
     */
    @RequestMapping("/test1")
    public R list(@RequestParam Map<String, Object> params){

        return R.ok().put("page", "这是一个docker测试数据");
    }
}
