package com.lqh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lqh.dao.ISysRoleDao;
import com.lqh.dao.ISysUserDao;
import com.lqh.domain.Permission;
import com.lqh.domain.SysRole;
import com.lqh.domain.SysUser;
import com.lqh.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserImpl implements ISysUserService {
    @Autowired
    private ISysUserDao iSysUserDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //用户和角色的关系
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.通过传递的username得到数据库的用户信息
        SysUser sysUser = iSysUserDao.findUserByName(username);
        //2.创建list集合初始化用户的权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 查出用户时同时查出当前用户下对应的角色
        List<SysRole> roles = sysUser.getList();
        if (roles != null) {
            for (SysRole role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }

        //3.权限框架的user对象需要三种属性 用户名 密码 用户的权限集合
        User user = new User(sysUser.getUsername(), sysUser.getPassword(),sysUser.getStatus()==1?true:false,true,true,true ,authorities);
        return user;
    }

    @Override
    public PageInfo<SysUser> findAllUser(Integer pageNum, Integer pageSize) {
        List<SysUser> list = iSysUserDao.findAllUser(pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<SysUser> pi = new PageInfo<SysUser>(list);
        return pi;
    }

    @Override
    public void saveUser(SysUser sysUser) {
        String password = passwordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(password);
        iSysUserDao.saveUser(sysUser);
    }

    @Override
    public SysUser findUserById(Integer id) {
        SysUser user = iSysUserDao.findUserById(id);
        return user;
    }

    /*@Override
    public void addRoleToUser(Integer userId, Integer[] ids) {
        //1.先清空用户已有的角色
        iSysUserDao.deleteRoleFromUser(userId);
        //2.再添加用户角色
        if (userId != null && ids.length > 0) {
            for (Integer roleId : ids) {
                iSysUserDao.addRoleToUser(userId, roleId);
            }
        }
    }*/

    @Override
    public void addRoleToUser(Integer userId, Integer[] ids) {
        //1.先清空用户已有的角色
        iSysUserDao.deleteRoleFromUser(userId);
        Map<String, Integer> map = new HashMap<>();

        //2.再添加用户角色
        if (ids != null && ids.length > 0) {
            for (Integer roleId : ids) {
                map.put("roleId",roleId);
                map.put("userId",userId);
                iSysUserDao.addRoleToUser(map);
            }
        }
    }
}
