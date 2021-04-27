package com.mall.demo.service;

import com.mall.demo.common.api.CommonResult;

/**
 * 管理会员Service
 */
public interface UmsMemberService {
    /**
     * 生成验证码
     */
    CommonResult generationAutoCode(String telephone);

    /**
     * 判断验证码和手机号是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);
}
