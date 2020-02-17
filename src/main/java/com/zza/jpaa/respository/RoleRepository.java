package com.zza.jpaa.respository;

import com.zza.jpaa.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface RoleRepository extends JpaRepository<Role, Serializable> {
}
