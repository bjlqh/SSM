package com.lqh.service.impl;

import com.lqh.dao.IPermissionDao;
import com.lqh.dao.ISysRoleDao;
import com.lqh.domain.SysRole;
import com.lqh.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleImpl implements ISysRoleService {
    @Autowired
    private ISysRoleDao iSysRoleDao;
    
    @Override
    public List<SysRole> findAllRole() {
        return iSysRoleDao.findAllRole();
    }

    @Override
    public void saveRole(SysRole role) {
        iSysRoleDao.saveRole(role);
    }

    @Override
    public SysRole findRoleById(Integer id) {
        return iSysRoleDao.findRoleById(id);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] ids) {
        //1.清空当前角色的权限
        iSysRoleDao.deletePermissionFromRole(roleId);
        //2.添加新的权限
        if (ids != null && ids.length > 0) {
            for (Integer pid : ids) {
                iSysRoleDao.addPermissionToRole(roleId, pid);
            }
        }
    }

}
