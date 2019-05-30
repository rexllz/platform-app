package com.scuthku.idiotswithlove.itforum.interceptors;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scuthku.idiotswithlove.itforum.entities.Token;
import com.scuthku.idiotswithlove.itforum.enums.ControllerEnum;
import com.scuthku.idiotswithlove.itforum.handlers.exceptions.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.scuthku.idiotswithlove.itforum.services.TokenService;
import com.scuthku.idiotswithlove.itforum.interceptors.annotations.LoginRequriement;


public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) {


        String tokenDTO = httpServletRequest.getHeader("token");

        // ignore if the annotation did not use on a method.
        if(!(handler instanceof HandlerMethod)) {
            return false;
        }

        Method method = ((HandlerMethod)handler).getMethod();
        boolean isPresent = method.isAnnotationPresent(LoginRequriement.class);

        if(isPresent) {
            LoginRequriement loginRequriement = method.getAnnotation(LoginRequriement.class);
            if (loginRequriement.isRequired()) {

                try {
                    if (!tokenService.verifyToken(tokenDTO)) {
                        return false;//throw new RuntimeException("token wrong");
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }

        return true;
    }
}
