package com.mall.demo.service.impl;

import com.mall.demo.common.api.CommonResult;
import com.mall.demo.component.CancelOrderSender;
import com.mall.demo.dto.OrderParam;
import com.mall.demo.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        LOGGER.info("process generateOrder");
        sendDelayMessageCancelOrder(11L);
        return CommonResult.success(null, "下单成功");
    }

    @Override
    public void cancelOrder(Long orderId) {
        //todo 执行一系类取消订单操作，具体参考mall项目
        LOGGER.info("process cancelOrder orderId:{}",orderId);
    }

    private void sendDelayMessageCancelOrder(Long orderId) {
        //获取订单超时时间，假设为60分钟
        long delayTimes = 30 * 1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }

}
