package com.zza.jpaa.common;

import com.zza.jpaa.annotion.IgnoreSecurity;
import com.zza.jpaa.entity.dto.UserInfo;
import com.zza.jpaa.exception.BizException;
import com.zza.jpaa.services.UserService;
import com.zza.jpaa.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

@Slf4j
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    // @Resource
    // private  UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String requestPath = request.getRequestURI();
        log.debug("Method: " + method.getName() + ", IgnoreSecurity: " + method.isAnnotationPresent(IgnoreSecurity.class));
        log.debug("requestPath: " + requestPath);
        if (requestPath.contains("/v2/api-docs") || requestPath.contains("/swagger") || requestPath.contains("/configuration/ui")) {
            return true;
        }
        if (requestPath.contains("/error")) {
            return true;
        }
        if (method.isAnnotationPresent(IgnoreSecurity.class)) {
            return true;
        }
        String token = request.getHeader("Authorization");
        log.debug("token: " + token);
        if (StringUtils.isEmpty(token)) {
            throw new BizException(50008,"token不存在");
        }
        if (JwtUtil.expireAtDate(token).before(new Date())){
            throw new BizException(50012,"token已过期");
        }
        UserInfo userInfo = JwtUtil.getUserByToken(token);

        log.info("user: {}" ,userInfo);
        request.setAttribute("currentUser", userInfo);
        return true;
    }

}
