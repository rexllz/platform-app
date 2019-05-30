package com.scuthku.idiotswithlove.itforum.dao;

import java.util.List;
import java.util.Optional;

import com.scuthku.idiotswithlove.itforum.dto.ImageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import com.scuthku.idiotswithlove.itforum.entities.User;
import org.springframework.transaction.annotation.Transactional;


@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User, Integer> {

    Optional<User> findById(@Nullable Integer id);

    List<User> findByEmailOrUsername(@Nullable String email, @Nullable String username);

    List<User> findByEmail(String email);

    List<User> findByUsername(String username);

    Optional<User> save(User user);


    @Transactional
    @Query(value="UPDATE User x SET x.image=:image WHERE x.userId= :id")
    Optional<User> updateImageById(@Param("image") String image, @Param("id") Integer id);

    Page<User> findByAgeIn(List<Integer> ageList, Pageable pageable);

    List<User> findByEmailEquals(String email);

    List<User> findByUsernameEquals(String username);
}
