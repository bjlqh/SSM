package com.lqh.controller;

import com.github.pagehelper.PageInfo;
import com.lqh.domain.SysRole;
import com.lqh.domain.SysUser;
import com.lqh.service.ISysRoleService;
import com.lqh.service.ISysUserService;
import com.lqh.service.impl.SysUserImpl;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
//@Secured("ROLE_ADMIN")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class UserController {
    @Autowired
    private ISysUserService iUserService;
    @Autowired
    private ISysRoleService iSysRoleService;

    @RequestMapping("/findAllUser")
    public String findAllUser(Model model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageInfo<SysUser> pi = iUserService.findAllUser(pageNum, pageSize);
        model.addAttribute("pi", pi);
        return "user/userList";
    }

    @RequestMapping("/addUser")
    public String addUser() {
        return "user/userAdd";
    }

    @RequestMapping("/saveUser")
    public String saveUser(SysUser sysUser) {
        iUserService.saveUser(sysUser);
        return "redirect:/user/findAllUser";
    }

    @RequestMapping("/findUserById")
    public String findUserById(Model model, Integer id) {
        //1.根据id 得到用户信息
        SysUser user = iUserService.findUserById(id);
        model.addAttribute("user", user);
        //2.查询角色列表
        List<SysRole> roleList = iSysRoleService.findAllRole();
        model.addAttribute("roleList", roleList);
        //3.获取用户当中的所有角色信息
        List<SysRole> roles = user.getList();
        if (null != roleList && roleList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (SysRole role : roles) {
                sb.append(role.getRoleName() + ",");
            }
            model.addAttribute("roleStr", sb.toString());
        }
        return "user/managerUserRole";
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(Integer userId, Integer[] ids) {
        iUserService.addRoleToUser(userId, ids);
        return "redirect:/user/findAllUser";
    }

    @RequestMapping("/findUserDetail")
    public String findUserDetail(Integer id, Model model) {
        SysUser user = iUserService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userDetail";
    }

}
