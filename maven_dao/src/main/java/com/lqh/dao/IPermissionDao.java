package com.lqh.dao;

import com.lqh.domain.Permission;

import java.util.List;

public interface IPermissionDao {

    List<Permission> findAllPermission();

    void savePermission(Permission permission);

    List<Permission> findPermissionById(Integer id);


}
