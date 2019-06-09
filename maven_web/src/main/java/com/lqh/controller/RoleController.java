package com.lqh.controller;

import com.lqh.domain.Permission;
import com.lqh.domain.SysRole;
import com.lqh.service.IPermissionService;
import com.lqh.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/role")
@RolesAllowed("ROLE_ADMIN1")
public class RoleController {
    @Autowired
    private ISysRoleService iSysRoleService;
    @Autowired
    private IPermissionService iPermissionService;

    @RequestMapping("/findAllRole")
    public String findAllRole(Model model) {
        List<SysRole> roleList = iSysRoleService.findAllRole();
        model.addAttribute("roleList", roleList);
        return "role/roleList";
    }

    @RequestMapping("/addRole")
    public String addRole() {
        return "role/roleAdd";
    }

    @RequestMapping("/saveRole")
    public String saveRole(SysRole role) {
        iSysRoleService.saveRole(role);
        return "redirect:/role/findAllRole";
    }

    @RequestMapping("/findRoleById")
    public String findRoleById(Integer id, Model model) {
        //1.当前角色信息
        SysRole role = iSysRoleService.findRoleById(id);
        model.addAttribute("role", role);
        //2.当前角色的权限
        List<Permission> permissions = role.getPermissions();
        if (permissions != null && permissions.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Permission permission : permissions) {
                String permissionName = permission.getPermissionName();
                sb.append(permissionName + ",");
            }
            model.addAttribute("pStr", sb.toString());
        }
        //3.数据库的所有权限
        List<Permission> pList = iPermissionService.findAllPermission();
        model.addAttribute("pList", pList);
        return "role/managerRolePermission";
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(Integer roleId, Integer[] ids) {
        iSysRoleService.addPermissionToRole(roleId,ids);
        return "redirect:/role/findAllRole";
    }
}
