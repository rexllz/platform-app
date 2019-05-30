package com.scuthku.idiotswithlove.itforum.services;

import com.scuthku.idiotswithlove.itforum.dto.ImageDTO;
import com.scuthku.idiotswithlove.itforum.dto.UserDTO;
import com.scuthku.idiotswithlove.itforum.entities.User;

import java.util.List;

public interface UserService {
    public UserDTO vertify(UserDTO userDTO);

    public UserDTO getUser(UserDTO userDTO);

    UserDTO register(UserDTO userDTO);

    UserDTO findUser(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    boolean updatePic(ImageDTO imageDTO);

    //List<UserDTO> findAll();

    //UserDTO findByEmail(UserDTO userDTO);

    //UserDTO findByUsername(UserDTO userDTO);

//    Page<UserDTO> findByAgeIn(List<Integer> ageList, Pageable pageable);
}
