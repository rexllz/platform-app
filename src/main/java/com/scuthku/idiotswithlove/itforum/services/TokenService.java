package com.scuthku.idiotswithlove.itforum.services;

import com.scuthku.idiotswithlove.itforum.dto.UserDTO;
import com.scuthku.idiotswithlove.itforum.entities.Token;
import com.scuthku.idiotswithlove.itforum.entities.User;

public interface TokenService {
    public String createToken(UserDTO userDTO);
    public boolean deleteToken(String tokenDTO);
    public boolean verifyToken(String tokenDTO);
}
