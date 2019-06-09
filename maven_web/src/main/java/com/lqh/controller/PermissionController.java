package com.lqh.controller;

import com.lqh.domain.Permission;
import com.lqh.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/permission")
@RolesAllowed("ROLE_ADMIN")
public class PermissionController {
    @Autowired
    private IPermissionService iPermissionService;

    @RequestMapping("/findAllPermission")
    public String findAllPermission(Model model) {
        List<Permission> list = iPermissionService.findAllPermission();
        model.addAttribute("list", list);
        return "permission/permissionList";
    }

    @RequestMapping("/addPermission")
    public String addPermission() {
        return "permission/permissionAdd";
    }

    @RequestMapping("/savePermission")
    public String savePermission(Permission permission) {
        iPermissionService.savePermission(permission);
        return "redirect:/permission/findAllPermission";
    }
}
