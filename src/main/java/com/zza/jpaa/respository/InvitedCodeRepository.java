package com.zza.jpaa.respository;

import com.zza.jpaa.entity.InviteCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface InvitedCodeRepository extends JpaRepository<InviteCode, Serializable> {

    Optional<InviteCode> findByUserNameAndStatus(String userName, Integer status);

    @Modifying
    @Transactional
    @Query(value = "update invited_code set status = 1 where code = :code and user_name = :account",nativeQuery = true)
    void usingInvitedCode(String code,String account);


}
