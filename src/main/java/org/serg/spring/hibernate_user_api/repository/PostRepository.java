package org.serg.spring.hibernate_user_api.repository;

import org.serg.spring.hibernate_user_api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE length(p.text) < :length")
    List<Post> findPostsByTextLengthLessThan(@Param("length") int length);
}
