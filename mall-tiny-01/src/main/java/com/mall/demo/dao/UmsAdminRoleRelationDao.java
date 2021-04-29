package com.mall.demo.dao;

import com.mall.demo.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户与角色管理自定义dao
 */
public interface UmsAdminRoleRelationDao {
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
