package com.scuthku.idiotswithlove.itforum.services;

import com.scuthku.idiotswithlove.itforum.dto.PostListDTO;
import com.scuthku.idiotswithlove.itforum.dto.UserDTO;
import com.scuthku.idiotswithlove.itforum.entities.FavoritePost;
import com.scuthku.idiotswithlove.itforum.entities.Post;
import com.scuthku.idiotswithlove.itforum.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    public Page<Post> findByUserId(UserDTO userDTO, Pageable pageable);

    public Page<Post> findByModuleIn(List<String> moduleList, Pageable pageable);

    public Page<Post> findByLabelIn(List<String> labelList, Pageable pageable);

    public Post findById(Integer postId);

    //Page<Post> findAll(UserDTO userDTO);

    public Page<Post> findAll(Pageable pageable);

    public Post save(Post post);

    public List<Post> findTop10ByOrderByLikeNum();

    public PostListDTO convertToDTO(Post post);

    public boolean setFavoritePost(FavoritePost favoritePost);

    public Integer deleteFavoritePost(FavoritePost favoritePost);

    public List<PostListDTO> getFavorPosts(Integer userId, Pageable pageable);
}
