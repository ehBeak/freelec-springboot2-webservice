package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Entity 클래스와 기본 Entity Repository는 함께 위치해야한다.(도메인 패키지에서 함께 관리하자)
 * -> 기본 Repository 없이는 제대로 역할을 할 수가 없다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
