package com.scuthku.idiotswithlove.itforum.services.impls;

import com.scuthku.idiotswithlove.itforum.dao.PostRepository;
import com.scuthku.idiotswithlove.itforum.dao.ReplyRepository;
import com.scuthku.idiotswithlove.itforum.dao.UserRepository;
import com.scuthku.idiotswithlove.itforum.dto.ReplyDTO;
import com.scuthku.idiotswithlove.itforum.entities.Reply;
import com.scuthku.idiotswithlove.itforum.enums.ControllerEnum;
import com.scuthku.idiotswithlove.itforum.handlers.exceptions.ControllerException;
import com.scuthku.idiotswithlove.itforum.services.ReplyService;
import javafx.geometry.Pos;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Page<Reply> findByPostId(Integer postId, Pageable pageable) {
        return  repository.findByPostIdEquals(postId, pageable);
    }

    @Override
    public Page<Reply> findByUserId(Integer userId, Pageable pageable) {
        return repository.findByUserIdEquals(userId, pageable);
    }

    @Override
    public Page<Reply> findByReplyWho(Integer replyWho, Pageable pageable) {
        return repository.findByReplyWhoEquals(replyWho, pageable);
    }

    @Override
    public Page<Reply> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Reply findById(Integer replyId) {
        return repository.findById(replyId).get();
    }

    @Override
    public Reply save(Reply reply) {
        return repository.save(reply);
    }

    @Override
    public ReplyDTO convertToDTO(Reply reply) {

        ReplyDTO replyDTO = new ReplyDTO();
        BeanUtils.copyProperties(reply,replyDTO);
        String authorNick = userRepository.findById(reply.getUserId()).get().getUsername();
        replyDTO.setAuthorNick(authorNick);
        if (reply.getReplyWho()==0)
        {
            replyDTO.setReplyWhoNick("author");
            String whoNick = userRepository.findById(postRepository.findById(reply.getPostId()).get().getUserId()).get().getUsername();
            replyDTO.setReplyWhoNick(whoNick);
            return replyDTO;
        }
        List<Reply> replyList = repository.findByPostIdAndAndFloor(reply.getPostId(), reply.getReplyWho());
        if (replyList.isEmpty()) throw new ControllerException(ControllerEnum.NO_SUCH_REPLY);
        String whoNick = userRepository.findById(replyList.get(0).getUserId()).get().getUsername();
        replyDTO.setReplyWhoNick(whoNick);
        return replyDTO;
    }
}

