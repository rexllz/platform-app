package com.scuthku.idiotswithlove.itforum.controllers;

import com.scuthku.idiotswithlove.itforum.dto.PostDTO;
import com.scuthku.idiotswithlove.itforum.dto.PostListDTO;
import com.scuthku.idiotswithlove.itforum.dto.UserDTO;
import com.scuthku.idiotswithlove.itforum.entities.FavoritePost;
import com.scuthku.idiotswithlove.itforum.entities.Post;
import com.scuthku.idiotswithlove.itforum.entities.User;
import com.scuthku.idiotswithlove.itforum.enums.ControllerEnum;
import com.scuthku.idiotswithlove.itforum.enums.PostEnum;
import com.scuthku.idiotswithlove.itforum.handlers.exceptions.ControllerException;
import com.scuthku.idiotswithlove.itforum.handlers.exceptions.PostException;
import com.scuthku.idiotswithlove.itforum.interceptors.annotations.LoginRequriement;
import com.scuthku.idiotswithlove.itforum.services.PostService;
import com.scuthku.idiotswithlove.itforum.services.UserService;
import com.scuthku.idiotswithlove.itforum.utils.ResultVOUtil;
import com.scuthku.idiotswithlove.itforum.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVO getPostList(@RequestParam(value = "module", defaultValue = "module") String module,
                                @RequestParam(value = "page", defaultValue = "0") Integer page,
                                @RequestParam(value = "size", defaultValue = "10") Integer size){
//        postService.findByModuleIn(module);
        if (size <= 0) throw new ControllerException(ControllerEnum.ZERO_SIZE);
        List<String> moduleList = Arrays.asList(module);
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Post> postPage = postService.findByModuleIn(moduleList,pageRequest);
        List<Post> postList = postPage.getContent();
        List<PostListDTO> postListDTOList = new ArrayList<PostListDTO>();

        for (Post post :postList){
            postListDTOList.add(postService.convertToDTO(post));
        }
        return ResultVOUtil.success(postListDTOList);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultVO getPostDetails(@PathVariable(value = "id") Integer postId){
        Post post = postService.findById(postId);
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post,postDTO);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(post.getUserId());
        postDTO.setAuthorName(userService.findUser(userDTO).getUsername());
        return ResultVOUtil.success(postDTO);
    }

    @ResponseBody
    @LoginRequriement(isRequired = true)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResultVO getPosts(@RequestHeader(value = "token", defaultValue = "") String token,
                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "size", defaultValue = "10") Integer size){
        if (size <= 0) throw new ControllerException(ControllerEnum.ZERO_SIZE);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(Integer.valueOf(token.split("-", 2)[0]));
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Post> postPage = postService.findByUserId(userDTO,pageRequest);
        List<Post> postList = postPage.getContent();
        List<PostListDTO> postListDTOList = new ArrayList<PostListDTO>();

        for (Post post :postList){
            postListDTOList.add(postService.convertToDTO(post));
        }
        return ResultVOUtil.success(postListDTOList);
    }

    @ResponseBody
    @RequestMapping(value = "/top",method = RequestMethod.GET)
    public ResultVO getTopList(){
        List<Post> topList = postService.findTop10ByOrderByLikeNum();

        List<PostListDTO> topListDTOList = new ArrayList<>();
        for (Post post :topList){
            topListDTOList.add(postService.convertToDTO(post));
        }
        return ResultVOUtil.success(topListDTOList);
    }

    @ResponseBody
    @LoginRequriement(isRequired = true)
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResultVO postPost(@RequestHeader(value = "token", defaultValue = "")String token,
                             @RequestBody Post post){
        post.setUserId(Integer.valueOf(token.split("-", 2)[0]));
        post.setLikeNum(0);
        post.setFilePaths("file");
        post.setModule("Module"); //
        if (post.getTitle()==null){
            throw new ControllerException(ControllerEnum.NULL_TITLE);//throw new PostException(PostEnum.NULL_TITLE);
        }
        if (post.getModule().equals(null)){
            throw new ControllerException(ControllerEnum.NULL_MODULE);
        }
        postService.save(post);
        return ResultVOUtil.success();
    }

    @ResponseBody
    @LoginRequriement(isRequired = true)
    @RequestMapping(value = "/favor/{id}",method = RequestMethod.POST)
    public ResultVO setFavoritePost(@PathVariable(value = "id") Integer postId,
                                    @RequestHeader(value = "token", defaultValue = "") String token){
        FavoritePost favoritePost = new FavoritePost();
        favoritePost.setUserId(Integer.valueOf(token.split("-", 2)[0]));
        favoritePost.setPostId(postId);
        return ResultVOUtil.success(postService.setFavoritePost(favoritePost));
    }

    @ResponseBody
    @LoginRequriement(isRequired = true)
    @RequestMapping(value = "/favor/{id}",method = RequestMethod.DELETE)
    public ResultVO deleteFavoritePost(@PathVariable(value = "id") Integer postId,
                                       @RequestHeader(value = "token", defaultValue = "") String token){
        FavoritePost favoritePost = new FavoritePost();
        favoritePost.setUserId(Integer.valueOf(token.split("-", 2)[0]));
        favoritePost.setPostId(postId);
        return ResultVOUtil.success(postService.deleteFavoritePost(favoritePost));
    }

    @ResponseBody
    @LoginRequriement(isRequired = true)
    @RequestMapping(value = "/favor",method = RequestMethod.GET)
    public ResultVO getFavoritePost(@RequestHeader(value = "token", defaultValue = "") String token,
                                    @RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageRequest pageRequest = PageRequest.of(page,size);
        List<PostListDTO> postListDTOList = postService.getFavorPosts(Integer.valueOf(token.split("-", 2)[0]),pageRequest);
        return ResultVOUtil.success(postListDTOList);
    }
}