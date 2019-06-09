package com.lqh.service.impl;

import com.lqh.dao.IPermissionDao;
import com.lqh.domain.Permission;
import com.lqh.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class PermissionImpl implements IPermissionService {
    @Autowired
    private IPermissionDao iPermissionDao;

    @Override
    public List<Permission> findAllPermission() {
        return iPermissionDao.findAllPermission();
    }

    @Override
    public void savePermission(Permission permission) {
        iPermissionDao.savePermission(permission);
    }

}
