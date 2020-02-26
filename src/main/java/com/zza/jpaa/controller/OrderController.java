package com.zza.jpaa.controller;

import com.zza.jpaa.annotion.IgnoreSecurity;
import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.vo.OrderVo;
import com.zza.jpaa.entity.vo.PageSearchVo;
import com.zza.jpaa.services.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "预约管理模块")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @IgnoreSecurity
    @ApiOperation(value = "预约业务")
    @PostMapping("/save")
    public ResultData saveOrder(@RequestBody OrderVo orderVo){
        orderService.saveOrders(orderVo);
        return ResultData.success("预约成功");
    }

    @ApiOperation(value = "查询订单列表")
    @GetMapping("/list")
    public ResultData orderLists(@RequestBody PageSearchVo pageSearchVo){
        return orderService.getList(pageSearchVo);
    }

    @ApiOperation(value = "获取前10条待处理记录")
    @GetMapping("/top")
    public ResultData topTen(){
        return orderService.getTop();
    }
}
