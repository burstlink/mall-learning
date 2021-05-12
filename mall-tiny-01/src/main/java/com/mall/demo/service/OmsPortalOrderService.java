package com.mall.demo.service;

import com.mall.demo.common.api.CommonResult;
import com.mall.demo.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * 前台订单管理Service
 */
public interface OmsPortalOrderService {
    /**
     * 根据提交信息生成订单
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    @Transactional
    void cancelOrder(Long orderId);
}
