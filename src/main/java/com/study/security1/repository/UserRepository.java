package com.study.security1.repository;

import com.study.security1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD 함수를 JpaRepository 들고있음
// @Repository 라는 어노테이션이 없어도 ioc 가능. 이유는 JpaRepository 를 상속했기 때문
public interface UserRepository extends JpaRepository<User, Integer> {




}
