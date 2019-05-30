package com.scuthku.idiotswithlove.itforum.dao;

import com.scuthku.idiotswithlove.itforum.entities.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Integer> {

//    List<Reply> findByPostIdEquals(Integer postId);

//    List<Reply> findByUserIdEquals(Integer userId);

//    List<Reply> findByReplyWhoEquals(Integer replyWho);

    Page<Reply> findByPostIdEquals(Integer postId, Pageable pageable);

    Page<Reply> findByUserIdEquals(Integer userId, Pageable pageable);

    Page<Reply> findByReplyWhoEquals(Integer replyWho, Pageable pageable);
    List<Reply> findByPostIdAndAndFloor(Integer postId, Integer floor);
}