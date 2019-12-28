package com.zza.jpaa.respository;

import com.zza.jpaa.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Serializable> {

    List<Message> findAllBySendId(String id);
}
