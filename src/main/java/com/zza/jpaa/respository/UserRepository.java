package com.zza.jpaa.respository;

import com.zza.jpaa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {
    User findByUserName(String username);

    User findByIdCard(String idCard);
}
