package com.lqh.service;

import com.lqh.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAllPermission();

    void savePermission(Permission permission);

}
