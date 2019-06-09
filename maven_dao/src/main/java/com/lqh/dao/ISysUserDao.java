package com.lqh.dao;

import com.lqh.domain.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ISysUserDao {

    public SysUser findUserByName(String username);

    List<SysUser> findAllUser(Integer pageNum, Integer pageSize);

    void saveUser(SysUser sysUser);

    SysUser findUserById(Integer id);

    //void addRoleToUser(Integer userId, Integer roleId);
    //@Insert("insert into sys_user_role values(#{userId},#{roleId})")
    //@Options(useGeneratedKeys = true)
    void addRoleToUser(Map<String, Integer> map);

    void deleteRoleFromUser(Integer userId);
}
