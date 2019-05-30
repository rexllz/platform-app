package com.scuthku.idiotswithlove.itforum.services.impls;

import com.scuthku.idiotswithlove.itforum.dao.FavorRepository;
import com.scuthku.idiotswithlove.itforum.dao.PostRepository;
import com.scuthku.idiotswithlove.itforum.dao.UserRepository;
import com.scuthku.idiotswithlove.itforum.dto.PostListDTO;
import com.scuthku.idiotswithlove.itforum.dto.UserDTO;
import com.scuthku.idiotswithlove.itforum.entities.FavoritePost;
import com.scuthku.idiotswithlove.itforum.entities.Post;
import com.scuthku.idiotswithlove.itforum.enums.ControllerEnum;
import com.scuthku.idiotswithlove.itforum.handlers.exceptions.ControllerException;
import com.scuthku.idiotswithlove.itforum.services.PostService;
import javafx.geometry.Pos;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavorRepository favorRepository;

    @Override
    public Page<Post> findByUserId(UserDTO userDTO, Pageable pageable) {
        return repository.findByUserIdEquals(userDTO.getUserId(),pageable);
    }

    @Override
    public Page<Post> findByModuleIn(List<String> moduleList, Pageable pageable) {
        return repository.findByModuleIn(moduleList,pageable);
    }

    @Override
    public Page<Post> findByLabelIn(List<String> labelList, Pageable pageable) {
        return repository.findByLabelIn(labelList, pageable);
    }

    @Override
    public Post findById(Integer postId) {
        return repository.findById(postId).get();
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Post save(Post post) {
        post.setLikeNum(0);
        post.setLabel("init");
        return repository.save(post);
    }


    @Override
    public List<Post> findTop10ByOrderByLikeNum() {
        return repository.findTop10ByOrderByLikeNum();
    }

    @Override
    public PostListDTO convertToDTO(Post post) {
        PostListDTO postListDTO = new PostListDTO();
        BeanUtils.copyProperties(post,postListDTO);
//
        String nickname = userRepository.findById(post.getUserId()).get().getNickname();
        postListDTO.setAuthorNickname(nickname);
        return postListDTO;
    }

    @Override
    public boolean setFavoritePost(FavoritePost favoritePost) {
        if (!repository.findById(favoritePost.getPostId()).isPresent()) {
            throw new ControllerException(ControllerEnum.NO_SUCH_POST);
        }

        if (!favorRepository.findByUserIdAndPostId(favoritePost.getUserId(), favoritePost.getPostId()).isEmpty()) {
            throw new ControllerException(ControllerEnum.FAVOR_EXISTED);
        }
        favorRepository.save(favoritePost);
        return true;
    }

    @Override
    public List<PostListDTO> getFavorPosts(Integer userId,Pageable pageable) {
        Page<FavoritePost> favoritePostPage = favorRepository.findByUserId(userId, pageable);
        List<FavoritePost> favoritePostList = favoritePostPage.getContent();
        List<Integer> postIdList = new ArrayList<Integer>();
        if (favoritePostList.isEmpty()) {
            throw new ControllerException(ControllerEnum.NO_SUCH_POST);
        }
        for (FavoritePost favoritePost: favoritePostList) {
            postIdList.add(favoritePost.getPostId());
        }

        List<Post> postList = repository.findByPostIdIn(postIdList);

        List<PostListDTO> postListDTOList= new ArrayList<PostListDTO>();
        if (postList.isEmpty()) {
            throw new ControllerException(ControllerEnum.NO_SUCH_POST);
        }
        for (Post post: postList) {
            postListDTOList.add(convertToDTO(post));
        }
        return postListDTOList;
    }

    @Override
    public Integer deleteFavoritePost(FavoritePost favoritePost) {
        return favorRepository.deleteByUserIdAndPostId(favoritePost.getUserId(), favoritePost.getPostId());
    }
}

