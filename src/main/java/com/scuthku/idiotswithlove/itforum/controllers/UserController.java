package com.scuthku.idiotswithlove.itforum.controllers;

import com.scuthku.idiotswithlove.itforum.dto.ImageDTO;
import com.scuthku.idiotswithlove.itforum.dto.UserDTO;
import com.scuthku.idiotswithlove.itforum.interceptors.annotations.LoginRequriement;
import com.scuthku.idiotswithlove.itforum.services.UserService;
import com.scuthku.idiotswithlove.itforum.services.impls.UserServiceImpl;
import com.scuthku.idiotswithlove.itforum.utils.ResultVOUtil;
import com.scuthku.idiotswithlove.itforum.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @LoginRequriement(isRequired = true)
    @RequestMapping(value="/info",method= RequestMethod.GET)
    public ResultVO getUserInfo(@RequestHeader(value = "token", defaultValue = "") String token) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(Integer.valueOf(token.split("-", 2)[0]));
        UserDTO userDTOResult = userService.findUser(userDTO);
        return ResultVOUtil.success(userDTOResult);
    }

    @ResponseBody
    @LoginRequriement(isRequired = true)
    @RequestMapping(value = "/info", method = RequestMethod.PUT)
    public ResultVO editUser(@RequestBody UserDTO userDTO){
        UserDTO userDTOResult = userService.update(userDTO);
        return ResultVOUtil.success(userDTOResult);
    }

    @ResponseBody
    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    public ResultVO register(@RequestBody UserDTO userDTO) {
        UserDTO userDTOResult = userService.register(userDTO);
        return ResultVOUtil.success(userDTOResult);
    }

    @ResponseBody
    @LoginRequriement(isRequired = true)
    @RequestMapping(value = "/picture", method = RequestMethod.POST)
    public ResultVO updateImage(@RequestBody ImageDTO imageDTO) {
        userService.updatePic(imageDTO);
        return ResultVOUtil.success();
    }
}