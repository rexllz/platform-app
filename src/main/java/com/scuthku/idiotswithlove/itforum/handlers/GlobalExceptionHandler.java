package com.scuthku.idiotswithlove.itforum.handlers;

import com.scuthku.idiotswithlove.itforum.enums.ControllerEnum;
import com.scuthku.idiotswithlove.itforum.handlers.exceptions.ControllerException;
import com.scuthku.idiotswithlove.itforum.handlers.exceptions.PostException;
import com.scuthku.idiotswithlove.itforum.interceptors.annotations.LoginRequriement;
import com.scuthku.idiotswithlove.itforum.utils.ResultVOUtil;
import com.scuthku.idiotswithlove.itforum.vo.ResultVO;
import jdk.net.SocketFlow;
import org.hibernate.query.criteria.internal.expression.ConcatExpression;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  /*  *//**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     *//*
    @InitBinder
    public void initBinder(WebDataBinder binder) {

    }

    *//**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     *//*
    @ModelAttribute
    public void addAttributes(Model model){

    }
*/
    /**
     * 全局异常捕捉处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ControllerException.class)
    public ResultVO errorHandler(ControllerException e, HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(400);
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}