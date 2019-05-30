package com.scuthku.idiotswithlove.itforum.services.impls;

import com.scuthku.idiotswithlove.itforum.dto.ImageDTO;
import com.scuthku.idiotswithlove.itforum.dto.UserDTO;
import com.scuthku.idiotswithlove.itforum.dao.UserRepository;
import com.scuthku.idiotswithlove.itforum.entities.User;
import com.scuthku.idiotswithlove.itforum.enums.ControllerEnum;
import com.scuthku.idiotswithlove.itforum.enums.UserEnum;
import com.scuthku.idiotswithlove.itforum.handlers.exceptions.ControllerException;
import com.scuthku.idiotswithlove.itforum.handlers.exceptions.UserException;
import com.scuthku.idiotswithlove.itforum.utils.HashUtil;
import com.scuthku.idiotswithlove.itforum.utils.ImageDownloadUtil;
import com.scuthku.idiotswithlove.itforum.utils.SaltUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scuthku.idiotswithlove.itforum.services.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public UserDTO vertify(UserDTO userDTO) {
        List<User> users = userRepository.findByEmailOrUsername(userDTO.getEmail(), userDTO.getUsername());// find by username or email
        if (users.isEmpty()) {
            throw new ControllerException(ControllerEnum.USER_NOT_EXIST);
        }

        if (users.size()>=2) {
            throw new ControllerException(ControllerEnum.NOT_ALLOW_PARAMETERS);
        }

        User user = users.get(0);

        String hash = HashUtil.hash(userDTO.getPassword(), user.getSalt());
        if (!user.getHash().equals(hash)) {
            throw new ControllerException(ControllerEnum.PASSWORD_WRONG);
        }
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
//        return new User(1, "rex", "email", "dragon", "this is a test", "jpg", 666, 12, "hash", "salt", "male" );
    }

    public UserDTO getUser(UserDTO userDTO) {
        return new UserDTO();
    }

    @Override
    @Transactional
    public UserDTO register(UserDTO userDTO) {
        //check username
        List<User> userByUsernames = userRepository.findByUsername(userDTO.getUsername());

        if (!userByUsernames.isEmpty()) {
            throw new ControllerException(ControllerEnum.DUPLICATE_NAME);
        }

        List<User> userByEmails = userRepository.findByEmail(userDTO.getEmail());

        if (!userByEmails.isEmpty()) {
            throw new RuntimeException("s");
            //throw new ControllerException(ControllerEnum.DUPLICATE_EMAIL);
        }

        User user = new User();

        BeanUtils.copyProperties(userDTO, user);
        try {
            user.setSalt(SaltUtil.salt());
        } catch (Exception e) {
            throw new ControllerException(ControllerEnum.SALT_INIT_ERROR);
        }
        user.setHash(HashUtil.hash(userDTO.getPassword(), user.getSalt()));

        defaultAttributes(user);
        userRepository.save(user);

        userDTO.setPassword(null);

        BeanUtils.copyProperties(user, userDTO);

        return userDTO;
    }


    @Override
    public UserDTO findUser(UserDTO userDTO) {

        Optional<User> user = userRepository.findById(userDTO.getUserId());
        if (!user.isPresent()){
            throw new ControllerException(ControllerEnum.USER_NOT_EXIST);
        }
        BeanUtils.copyProperties(user.get(), userDTO);
        return userDTO;
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO userDTO) {

        Optional<User> userById = userRepository.findById(userDTO.getUserId());
        if (!userById.isPresent()){
            throw new ControllerException(ControllerEnum.USER_NOT_EXIST);
        }

        if (!userById.get().getEmail().equals(userDTO.getEmail())){
            List<User> userByEmails = userRepository.findByEmail(userDTO.getEmail());
            if (!userByEmails.isEmpty()){
                throw new ControllerException(ControllerEnum.DUPLICATE_EMAIL);
            }
        }
        userDTO = fillNull(userDTO, userById.get());
        BeanUtils.copyProperties(userDTO , userById.get());
        userRepository.save(userById.get());
        return userDTO;
    }

    public boolean updatePic(ImageDTO imageDTO) {
        if (imageDTO.getUserId() == null || imageDTO.getPicture() == null) {
            throw  new ControllerException(ControllerEnum.NO_PICTURE_FOR_USER);
        }
        userRepository.updateImageById(ImageDownloadUtil.downloadPic(imageDTO), imageDTO.getUserId());
        return true;
    }

    // need to think about
    private UserDTO fillNull(UserDTO userDTO, User oldUser) {
        if (userDTO.getAge()!=null){
            oldUser.setAge(userDTO.getAge());
        }
        if (userDTO.getEmail()!=null){
            oldUser.setEmail(userDTO.getEmail());}
        if (userDTO.getImage()!=null){
            oldUser.setImage(userDTO.getImage());}
        if (userDTO.getSignature()!=null){
            oldUser.setSignature(userDTO.getSignature());}
        if (userDTO.getNickname()!=null){
            oldUser.setNickname(userDTO.getNickname());
        }
        if (userDTO.getGender()!=null){
            oldUser.setGender(userDTO.getGender());
        }
        return userDTO;
    }

    private void defaultAttributes(User user) {
        user.setGender("fm");
        user.setImage("/");
        user.setAge(0);
        user.setXp(0);
    }
/*
    @Override
    public List<UserDTO> findAll() {

        return userRepository.findAll();
    }*/

/*
    @Override
    public UserDTO findByEmail(UserDTO uerDTO) {

        List<User> list = userRepository.findByEmail(email);
        if (list.isEmpty()){
            throw new UserException(UserEnum.USER_NOT_EXIST);
        }
        User user = list.get(0);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
*/


/*    @Override
    public UserDTO findByUsername(UserDTO userDTO) {
        List<User> list = userRepository.findByUsername(username);
        if (list.isEmpty()){
            throw new UserException(UserEnum.USER_NOT_EXIST);
        }
        User user = list.get(0);
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }*/
}
