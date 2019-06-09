package com.lqh.service;

import com.github.pagehelper.PageInfo;
import com.lqh.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ISysUserService extends UserDetailsService {

    PageInfo<SysUser> findAllUser(Integer pageNum, Integer pageSize);

    void saveUser(SysUser sysUser);

    SysUser findUserById(Integer id);

    void addRoleToUser(Integer userId, Integer[] ids);
}
