package com.zhunism.backendapp.authentication.repository;

import com.zhunism.backendapp.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findFirstByUserName(String userName);
}
