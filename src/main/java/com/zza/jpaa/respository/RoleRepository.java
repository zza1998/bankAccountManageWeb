package com.zza.jpaa.respository;

import com.zza.jpaa.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface RoleRepository extends JpaRepository<Role, Serializable> {
}
