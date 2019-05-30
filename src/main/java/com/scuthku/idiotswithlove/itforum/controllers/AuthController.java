package com.scuthku.idiotswithlove.itforum.controllers;

import com.scuthku.idiotswithlove.itforum.utils.ResultVOUtil;
import com.scuthku.idiotswithlove.itforum.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.scuthku.idiotswithlove.itforum.dto.UserDTO;
import com.scuthku.idiotswithlove.itforum.entities.Token;
import com.scuthku.idiotswithlove.itforum.interceptors.annotations.LoginRequriement;
import com.scuthku.idiotswithlove.itforum.services.TokenService;
import com.scuthku.idiotswithlove.itforum.services.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @ResponseBody
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public String weclomeToken() {
        return "welcome token url";
    }

    @ResponseBody
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResultVO getToken(@RequestBody UserDTO userDTO) {
        UserDTO userDTOResult = userService.vertify(userDTO);
        String tokenDTO = tokenService.createToken(userDTOResult); //return

        return ResultVOUtil.success(tokenDTO);
    }
    @ResponseBody
    @RequestMapping(value="/token", method = RequestMethod.DELETE)
    public ResultVO deleteToken(@RequestHeader(value = "token", defaultValue = "1-1") String token) {
        tokenService.deleteToken(token);
        return ResultVOUtil.success();
    }
}
