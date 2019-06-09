package com.lqh.controller;

import com.lqh.domain.SysLog;
import com.lqh.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Component
public class LogControllerUtils {
    @Autowired
    private ISysLogService iLogService;
    @Autowired
    private HttpServletRequest request;
    private SysLog log;

    @Pointcut(value = "execution(* com.lqh.controller.*.*(..))")
    public void pointCut() {
    }

    //前置通知获取、存储日志信息
    @Before(value = "pointCut()")
    public void executeBefore(JoinPoint joinPoint) {
        log = new SysLog();
        //1.获取当前时间作为访问时间储存
        log.setVisitTime(new Date());
        //2.获取登录成功的用户名
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        log.setUsername(user.getUsername());
        //3.获取ip值,需要通过httpServletRequest对象
        log.setIp(request.getRemoteAddr());
        //4.获取正在访问的类名+方法名
        Class targetClass = joinPoint.getTarget().getClass();
        String className = targetClass.getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.setMethod(className + "·" + methodName);
    }

    //后置通知保存日志
    @AfterReturning(value = "pointCut()")
    public void executeAfter() {
        iLogService.saveLog(log);
    }
}
