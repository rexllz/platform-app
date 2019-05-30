package com.scuthku.idiotswithlove.itforum.dao;

import com.scuthku.idiotswithlove.itforum.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByPostIdIn(List<Integer> postIdList);

    Page<Post> findByUserIdEquals(Integer userId, Pageable pageable);

    Page<Post> findByModuleIn(List<String> moduleList, Pageable pageable);

    Page<Post> findByLabelIn(List<String> labelList, Pageable pageable);

    List<Post> findTop10ByOrderByLikeNum();

}