package com.lqh.dao;

import com.lqh.domain.SysRole;

import java.util.List;

public interface ISysRoleDao {

    List<SysRole> findAllRole();

    void saveRole(SysRole role);

    List<SysRole> findRolesByUserId(Integer id);

    SysRole findRoleById(Integer id);

    void deletePermissionFromRole(Integer roleId);

    void addPermissionToRole(Integer rId, Integer pid);
}
