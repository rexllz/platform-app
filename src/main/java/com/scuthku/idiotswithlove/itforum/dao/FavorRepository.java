package com.scuthku.idiotswithlove.itforum.dao;

import com.scuthku.idiotswithlove.itforum.entities.FavoritePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface FavorRepository extends Repository<FavoritePost, Integer> {

    List<FavoritePost> findByUserIdAndPostId(Integer userId, Integer postId);

    Page<FavoritePost> findByUserId(Integer userId, Pageable pageable);

    @Transactional
    Integer deleteByUserIdAndPostId(Integer userId, Integer postId);

    Optional<FavoritePost> save(FavoritePost favoritePost);
}
