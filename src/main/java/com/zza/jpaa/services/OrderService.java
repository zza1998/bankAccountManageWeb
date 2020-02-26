package com.zza.jpaa.services;

import com.zza.jpaa.common.ResultData;
import com.zza.jpaa.entity.vo.OrderVo;
import com.zza.jpaa.entity.vo.PageSearchVo;

public interface OrderService {
    void saveOrders(OrderVo orderVo);

    ResultData getList(PageSearchVo pageSearchVo);

    ResultData getTop();
}
