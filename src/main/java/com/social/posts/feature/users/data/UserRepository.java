package com.social.posts.feature.users.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserDto, Integer> {

    @Query("SELECT u FROM users u LEFT JOIN FETCH u.posts WHERE u.id = :userId")
    Optional<UserDto> findByIdWithPosts(@Param("userId") Integer userId);

}
