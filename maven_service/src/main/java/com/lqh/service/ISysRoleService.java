package com.lqh.service;

import com.lqh.domain.SysRole;

import java.util.List;

public interface ISysRoleService {

    List<SysRole> findAllRole();

    void saveRole(SysRole role);

    SysRole findRoleById(Integer id);

    void addPermissionToRole(Integer roleId, Integer[] ids);
}
