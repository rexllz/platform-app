package com.scuthku.idiotswithlove.itforum.services;

import com.scuthku.idiotswithlove.itforum.dto.ReplyDTO;
import com.scuthku.idiotswithlove.itforum.entities.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReplyService {

    Page<Reply> findByPostId(Integer postId, Pageable pageable);

    Page<Reply> findByUserId(Integer userId, Pageable pageable);

    Page<Reply> findByReplyWho(Integer replyWho, Pageable pageable);

    Page<Reply> findAll(Pageable pageable);

    Reply findById(Integer replyId);

    Reply save(Reply reply);

    ReplyDTO convertToDTO(Reply reply);
}
