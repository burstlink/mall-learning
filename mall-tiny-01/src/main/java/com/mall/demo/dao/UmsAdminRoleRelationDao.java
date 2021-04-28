package com.mall.demo.dao;

import com.mall.demo.mbg.model.UmsPermission;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * h后台用户与角色管理自定义dao
 */
public interface UmsAdminRoleRelationDao {
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
