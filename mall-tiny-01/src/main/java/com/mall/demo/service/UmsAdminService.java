package com.mall.demo.service;

import com.mall.demo.mbg.model.UmsAdmin;
import com.mall.demo.mbg.model.UmsPermission;

import java.util.List;

/**
 * 后台管理员Service
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     * @param username 用户名
     * @return 后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册管理员
     * @param umsAdminParam 管理员参数
     * @return 管理员对象
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 登录用户
     * @param username 用户名
     * @param password 密码
     * @return 返回token
     */
    String login(String username,  String password);

    /**
     * 获取用户所有权
     * @param adminId 用户id
     * @return 用户权限列表
     */
    List<UmsPermission> getPermissionList(Long adminId);
}
