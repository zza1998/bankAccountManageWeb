package com.zza.jpaa.respository;

import com.zza.jpaa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {
    User findByName(String name);

    List<User> findUsersByName(String userName);
}
