package com.zza.jpaa.services.impl;

import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.Order;
import com.zza.jpaa.entity.vo.OrderVo;
import com.zza.jpaa.entity.vo.PageSearchVo;
import com.zza.jpaa.exception.BizException;
import com.zza.jpaa.respository.OrderRepository;
import com.zza.jpaa.services.OrderService;
import com.zza.jpaa.util.IdCardUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderRepository orderRepository;

    @Override
    public void saveOrders(OrderVo orderVo) {
        if (orderVo.getType().length <= 0) {
            throw new BizException("请输入正确的预约类型");
        }
        if (IdCardUtil.isValidate18Idcard(orderVo.getIdcard())){
            throw new BizException("请输入正确的身份信息");
        }
        List<Order> ordersByUser = orderRepository.findOrdersByUser(orderVo.getIdcard());
        Map<Integer,Object> types = new HashMap<>();
        ordersByUser.forEach((o)->{types.put(o.getType(),new Object());});
        List<Order> orders = new ArrayList<>(2 * orderVo.getType().length);
        for (int i : orderVo.getType()) {
            if (!types.containsKey(i)) {
                orders.add(Order.builder()
                        .name(orderVo.getName())
                        .phone(orderVo.getPhone())
                        .idcard(orderVo.getIdcard())
                        .type(i)
                        .status(0)
                        .createTime(new Date())
                        .build());
            }
        }
        orderRepository.saveAll(orders);
    }

    @Override
    public ResultData getList(PageSearchVo pageSearchVo) {
        List<Order> allList = orderRepository.findAll();
        if (StringUtils.isEmpty(pageSearchVo.getFilter())){
            String f = pageSearchVo.getFilter();
            allList = allList.stream().filter((o)->
                    o.getId().contains(f) ||
                    o.getType().toString().equals(f) ||
                    o.getIdcard().contains(f) ||
                    o.getName().contains(f) ||
                    o.getPhone().contains(f) ||
                    o.getStatus().toString().equals(f) ||
                    o.getCreateTime().toString().equals(f))
                    .collect(Collectors.toList());
        }
        allList = allList.subList(Math.max(0,pageSearchVo.getPageSize()*(pageSearchVo.getPageIndex()-1)),
                Math.min(allList.size(),pageSearchVo.getPageSize()*pageSearchVo.getPageIndex()));

        return ResultData.success("操作成功",allList);

    }

    @Override
    public ResultData getTop() {
        List<Order> topList = orderRepository.findTopByCreateTime();
        return ResultData.success("查询成功",topList);
    }
}
