package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Entity 클래스와 기본 Entity Repository는 함께 위치해야한다.(도메인 패키지에서 함께 관리하자)
 * -> 기본 Repository 없이는 제대로 역할을 할 수가 없다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    //SpringDataJpa에서 제공하지 않는 메소드는 쿼리로 작성해도 된다.
    @Query("SELECT p FROM Posts  p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
