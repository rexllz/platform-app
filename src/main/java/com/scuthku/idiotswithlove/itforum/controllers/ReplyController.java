package com.scuthku.idiotswithlove.itforum.controllers;

import com.scuthku.idiotswithlove.itforum.dto.ReplyDTO;
import com.scuthku.idiotswithlove.itforum.dto.ReplyPostDTO;
import com.scuthku.idiotswithlove.itforum.entities.Post;
import com.scuthku.idiotswithlove.itforum.entities.Reply;
import com.scuthku.idiotswithlove.itforum.interceptors.annotations.LoginRequriement;
import com.scuthku.idiotswithlove.itforum.services.PostService;
import com.scuthku.idiotswithlove.itforum.services.ReplyService;
import com.scuthku.idiotswithlove.itforum.utils.ResultVOUtil;
import com.scuthku.idiotswithlove.itforum.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private PostService postService;

    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResultVO getReply(@RequestParam("postId") int postID,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Reply> replyPage = replyService.findByPostId(postID,pageRequest);

//        List<String> replyList = Arrays.asList(module);
//        PageRequest pageRequest = PageRequest.of(page,size);
//        Page<Post> postPage = postService.findByModuleIn(moduleList,pageRequest);
        List<Reply> replyList = replyPage.getContent();
        List<ReplyDTO> replyDTOList = new ArrayList<>();

        for (Reply reply :replyList){
            replyDTOList.add(replyService.convertToDTO(reply));
        }
        return ResultVOUtil.success(replyDTOList);
    }

    @ResponseBody
    @LoginRequriement(isRequired = true)
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public ResultVO postReply(@RequestBody ReplyPostDTO replyPostDTO,
                              @RequestHeader(value = "token", defaultValue = "")String token){
        replyPostDTO.setUserId(Integer.valueOf(token.split("-", 2)[0]));
        replyPostDTO.setReplyWho(0);
        Reply reply = new Reply();
        BeanUtils.copyProperties(replyPostDTO,reply);
        Post post = postService.findById(replyPostDTO.getPostId());
        reply.setFloor(post.getReplyNum()+1);
        post.setReplyNum(post.getReplyNum()+1);
        replyService.save(reply);

        return  ResultVOUtil.success();
    }
}

